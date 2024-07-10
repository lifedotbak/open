package com.spyker.framework.exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.core.util.ObjectUtil;

import com.spyker.framework.exception.entity.ExceptionLog;
import com.spyker.framework.exception.handler.ExceptionLogHandler;
import com.spyker.framework.exception.handler.ExceptionLogUsableHandler;
import com.spyker.framework.exception.wrapper.ContentCachingRequestWrapper;
import com.spyker.framework.response.ResponseCodeEnum;
import com.spyker.framework.response.RestResponse;

import jakarta.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Objects;

/** 系统异常处理 */
@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private static final String REQUEST_BODY_MESSAGE = "requestBodyMessage";

    private final ExceptionLogUsableHandler exceptionChain;

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = SaTokenException.class)
    public RestResponse<?> saTokenExceptionHandler(HttpServletRequest request, SaTokenException e) {

        doLog(request, e);

        return RestResponse.error(-1, e.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = GlobalException.class)
    public RestResponse<?> globalExceptionHandler(HttpServletRequest request, GlobalException e) {

        doLog(request, e);

        return RestResponse.error(-1, e.getMessage());
    }

    /** 处理业务业务异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = BusinessException.class)
    public RestResponse<?> serviceExceptionHandler(
            HttpServletRequest request, BusinessException e) {
        doLog(request, e);

        return RestResponse.error(e.getCode(), e.getMessage());
    }

    /** 统一处理非自定义异常外的所有异常 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public RestResponse<?> exceptionHandler(HttpServletRequest request, Exception e) {
        doLog(request, e);
        return RestResponse.error(-1, e.getMessage());
    }

    /** 兼容Validation校验框架：忽略参数异常处理器 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public RestResponse<?> parameterMissingExceptionHandler(
            HttpServletRequest request, MissingServletRequestParameterException e) {
        doLog(request, e);
        return RestResponse.error(
                ResponseCodeEnum.VALIDATE_FAILED.getCode(),
                "请求参数 " + e.getParameterName() + " 不能为空");
    }

    /** 兼容Validation校验框架：缺少请求体异常处理器 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public RestResponse<?> parameterBodyMissingExceptionHandler(
            HttpServletRequest request, HttpMessageNotReadableException e) {
        doLog(request, e);
        return RestResponse.error(ResponseCodeEnum.VALIDATE_FAILED.getCode(), "参数体不能为空");
    }

    /** 兼容Validation校验框架：参数效验异常处理器 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RestResponse<?> parameterExceptionHandler(
            HttpServletRequest request, MethodArgumentNotValidException e) {
        doLog(request, e);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return RestResponse.error(
                        ResponseCodeEnum.VALIDATE_FAILED.getCode(), fieldError.getDefaultMessage());
            }
        }
        return RestResponse.error(ResponseCodeEnum.VALIDATE_FAILED.getCode(), "请求参数校验异常");
    }

    /** 拦截表单参数校验 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public RestResponse<?> bindExceptionException(HttpServletRequest request, BindException e) {
        doLog(request, e);
        BindingResult bindingResult = e.getBindingResult();
        return RestResponse.error(
                ResponseCodeEnum.VALIDATE_FAILED.getCode(),
                Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    /** 拦截参数类型不正确 */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public RestResponse<?> httpMediaTypeNotSupportedExceptionException(
            HttpServletRequest request, HttpMediaTypeNotSupportedException e) {
        doLog(request, e);
        return RestResponse.error(
                ResponseCodeEnum.VALIDATE_FAILED.getCode(), Objects.requireNonNull(e.getMessage()));
    }

    /** 由于body在接口读取后无法获取，这里把body提前取出放到参数中，在上面处理异常时使用 */
    @ModelAttribute
    public void getBobyInfo(HttpServletRequest request) {
        // 获取requestBody
        try {
            ContentCachingRequestWrapper requestWapper = null;
            if (request instanceof HttpServletRequest) {
                requestWapper = (ContentCachingRequestWrapper) request;

                String body =
                        IOUtils.toString(requestWapper.getBody(), request.getCharacterEncoding());
                request.setAttribute(REQUEST_BODY_MESSAGE, body);
            }
        } catch (Exception e) {

        }
    }

    private void doLog(HttpServletRequest request, Exception e) {

        log.error("捕获到异常：{}", e);
        // 加入数据库日志记录
        StringWriter sw = new StringWriter();

        e.printStackTrace(new PrintWriter(sw, true));

        // 异常的详情
        String expDetail = sw.toString();

        try {
            sw.close();
        } catch (IOException ioException) {
            log.error("异常日志：关闭异常详情Writer异常");
        }

        // 异常的url
        String expUrl = request.getRequestURI();

        // 异常的参数
        Object body = request.getAttribute(REQUEST_BODY_MESSAGE);
        String expParams = ObjectUtil.isNotNull(body) ? body.toString() : "";

        //		String headerLog = "";
        //		Enumeration<String> headerNames = request.getHeaderNames();
        //		Map<String, String> headers = new HashMap<>();
        //		while (headerNames.hasMoreElements()) {
        //			String headerName = headerNames.nextElement();
        //			headers.put(headerName, request.getHeader(headerName));
        //		}
        //		if (!headers.isEmpty()) {
        //			ObjectMapper objectMapper = new ObjectMapper();
        //			try {
        //				headerLog = objectMapper.writeValueAsString(headers);
        //			} catch (JsonProcessingException jsonProcessingException) {
        //				jsonProcessingException.printStackTrace();
        //				log.error("写异常日志objectMapper.writeValueAsString(headers)发生异常,原异常详情： {}", expDetail);
        //			}
        //		}

        // 异常的类型
        String expType = e.getClass().getName();

        // 异常的类名
        StackTraceElement stackTraceElement = e.getStackTrace()[0];
        String expController = stackTraceElement.getClassName();

        // 异常的方法名
        String expMethod = stackTraceElement.getMethodName();

        ExceptionLog exceptionLog = new ExceptionLog();
        exceptionLog.setExpUrl(expUrl);
        exceptionLog.setExpParams(expParams);
        exceptionLog.setExpType(expType);
        exceptionLog.setExpController(expController);
        exceptionLog.setExpMethod(expMethod);
        exceptionLog.setExpDetail(expDetail);

        log.error("exceptionLog ---> {}", exceptionLog);

        ExceptionLogHandler exceptionLogHandler = exceptionChain.getHandler();

        if (null != exceptionLogHandler) {
            exceptionLogHandler.handler(exceptionLog);
        }
    }
}