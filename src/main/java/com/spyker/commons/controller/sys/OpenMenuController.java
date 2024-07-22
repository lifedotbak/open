package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.OpenMenu;
import com.spyker.commons.search.OpenMenuSearch;
import com.spyker.commons.service.OpenMenuService;
import com.spyker.framework.constants.BusinessTypeEnum;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import com.spyker.framework.web.BaseController;
import com.spyker.framework.web.response.RestResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Tag(name = "菜单管理", description = "菜单管理")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/open-menu")
@Slf4j
public class OpenMenuController extends BaseController {

    // @formatter:off

    private final OpenMenuService openMenuService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "菜单管理--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<OpenMenu>> list(OpenMenuSearch search) {

        List<OpenMenu> result = openMenuService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "菜单管理--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<OpenMenu>> listPage(@ModelAttribute OpenMenuSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<OpenMenu> page = new Page<>(current, size);

        page = openMenuService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<OpenMenu> detail(@PathVariable("id") String id) {

        OpenMenu result = openMenuService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "菜单管理--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<OpenMenu> add(@RequestBody OpenMenu add) {

        openMenuService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<OpenMenu> update(
            @PathVariable("id") String id, @RequestBody OpenMenu update) {

        update.setId(id);

        openMenuService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "菜单管理--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<OpenMenu> delete(@PathVariable("id") String id) {

        openMenuService.delete(id);

        return RestResponse.success();
    }
}