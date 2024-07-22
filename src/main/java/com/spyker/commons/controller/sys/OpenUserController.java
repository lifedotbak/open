package com.spyker.commons.controller.sys;

import cn.dev33.satoken.annotation.SaCheckLogin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.OpenUser;
import com.spyker.commons.search.OpenUserSearch;
import com.spyker.commons.service.OpenUserService;
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
 * 用户表 前端控制器
 *
 * @author CodeGenerator
 * @since 2024-07-22
 */
@Tag(name = "用户表", description = "用户表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/open-user")
@Slf4j
public class OpenUserController extends BaseController {

    // @formatter:off

    private final OpenUserService openUserService;
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "用户表--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<OpenUser>> list(OpenUserSearch search) {

        List<OpenUser> result = openUserService.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "用户表--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<OpenUser>> listPage(@ModelAttribute OpenUserSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<OpenUser> page = new Page<>(current, size);

        page = openUserService.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "用户表--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<OpenUser> detail(@PathVariable("id") String id) {

        OpenUser result = openUserService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "用户表--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<OpenUser> add(@RequestBody OpenUser add) {

        openUserService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "用户表--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<OpenUser> update(
            @PathVariable("id") String id, @RequestBody OpenUser update) {

        update.setId(id);

        openUserService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "用户表--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<OpenUser> delete(@PathVariable("id") String id) {

        openUserService.delete(id);

        return RestResponse.success();
    }
}