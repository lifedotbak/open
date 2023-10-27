package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysOperLog;
import com.spyker.commons.search.SysOperLogSearch;
import com.spyker.commons.service.SysOperLogService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "操作日志记录", description = "操作日志记录")
@RequestMapping("/sys/sys-oper-log")
@Slf4j
@SaCheckLogin
public class SysOperLogController {

    private final SysOperLogService sysOperLogService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysOperLog>> list_page(SysOperLogSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysOperLog> page = new Page<>(current, size);

        page = sysOperLogService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysOperLog> detail(@RequestParam String id) {
        SysOperLog result = sysOperLogService.get(id);

        return RestResponse.success(result);
    }

}