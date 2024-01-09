package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.SysLogininfor;
import com.spyker.commons.search.SysLogininforSearch;
import com.spyker.commons.service.SysLogininforService;
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
 * 系统访问记录 前端控制器
 *
 * @author CodeGenerator
 * @since 2023-09-28
 */
@RequiredArgsConstructor
@RestController
@Tag(name = "系统访问记录", description = "系统访问记录")
@RequestMapping("/sys/sys-logininfor")
@Slf4j
@SaCheckLogin
public class SysLogininforController {

    private final SysLogininforService sysLogininforService;

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<SysLogininfor>> list_page(SysLogininforSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<SysLogininfor> page = new Page<>(current, size);

        page = sysLogininforService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<SysLogininfor> detail(@RequestParam String id) {
        SysLogininfor result = sysLogininforService.get(id);

        return RestResponse.success(result);
    }
}