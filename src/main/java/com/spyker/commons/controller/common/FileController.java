package com.spyker.commons.controller.common;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.spyker.framework.config.PlatformConfigProperties;
import com.spyker.framework.constants.CommonsConstants;
import com.spyker.framework.util.file.ExFileUtils;
import com.spyker.framework.util.file.FileUploadUtils;
import com.spyker.framework.util.http.ServletUtils;
import com.spyker.framework.util.text.ExStringUtils;
import com.spyker.framework.web.response.RestMapResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/** 通用请求处理 */
@Tag(name = "文件上传下载处理", description = "文件上传下载处理")
@RestController
@RequestMapping("/common/file")
@RequiredArgsConstructor
@Slf4j
@SaCheckLogin
public class FileController {

    private static final String FILE_DELIMITER = ",";

    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(
            String fileName,
            boolean delete,
            HttpServletResponse response,
            HttpServletRequest request) {

        if (!ExFileUtils.checkAllowDownload(fileName)) {
            throw new IllegalArgumentException(
                    ExStringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
        }

        try {

            String realFileName =
                    System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = PlatformConfigProperties.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            ExFileUtils.setAttachmentResponseHeader(response, realFileName);
            ExFileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                ExFileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /** 本地资源通用下载 */
    @GetMapping("/download/resource")
    public void resourceDownload(
            String resource, HttpServletRequest request, HttpServletResponse response) {

        if (!ExFileUtils.checkAllowDownload(resource)) {
            throw new IllegalArgumentException(
                    ExStringUtils.format("资源文件({})非法，不允许下载。 ", resource));
        }

        try {
            // 本地资源路径
            String localPath = PlatformConfigProperties.getProfile();
            // 数据库资源地址
            String downloadPath =
                    localPath
                            + StringUtils.substringAfter(
                                    resource, CommonsConstants.RESOURCE_PREFIX);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            ExFileUtils.setAttachmentResponseHeader(response, downloadName);
            ExFileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /** 通用上传请求（单个） */
    @PostMapping("/upload")
    public RestMapResponse uploadFile(MultipartFile file) {
        try {
            // 上传文件路径
            String filePath = PlatformConfigProperties.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = ServletUtils.getUrl() + fileName;
            RestMapResponse ajax = RestMapResponse.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", ExFileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        } catch (Exception e) {
            log.error("error--->{}", e.getMessage());
            return RestMapResponse.error(e.getMessage());
        }
    }

    /** 通用上传请求（多个） */
    @PostMapping("/uploads")
    public RestMapResponse uploadFiles(List<MultipartFile> files) {
        try {
            // 上传文件路径
            String filePath = PlatformConfigProperties.getUploadPath();
            List<String> urls = new ArrayList<>();
            List<String> fileNames = new ArrayList<>();
            List<String> newFileNames = new ArrayList<>();
            List<String> originalFilenames = new ArrayList<>();
            for (MultipartFile file : files) {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file);
                String url = ServletUtils.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(ExFileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            RestMapResponse ajax = RestMapResponse.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMITER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMITER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMITER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMITER));
            return ajax;
        } catch (Exception e) {

            log.error("error--->{}", e.getMessage());
            return RestMapResponse.error(e.getMessage());
        }
    }
}
