package com.spyker.commons.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.RInfo;
import com.spyker.commons.search.RInfoSearch;
import com.spyker.commons.service.RInfoService;
import com.spyker.framework.core.BaseController;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author CodeGenerator
 * @since 2023-11-24
 */
@Tag(name = "R-info", description = "R-info")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/r-info")
@Slf4j
public class RInfoController extends BaseController {

    private final RInfoService rInfoService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @Log(title = "--列表", businessType = BusinessType.QUERY)
    public RestResponse<List<RInfo>> list(RInfoSearch search) {

        List<RInfo> result = rInfoService.query(search);

        return RestResponse.success(result);
    }

    /**
     * ps:@ModelAttribute属性解决get请求两个对象参数的问题
     *
     * @param search
     * @param searchPageInfo
     * @return
     */
    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @Log(title = "--列表（分页）", businessType = BusinessType.QUERY)
    public RestResponse<IPage<RInfo>> list_page(@ModelAttribute RInfoSearch search,
            @ModelAttribute SearchPageInfo searchPageInfo) {

        int current = 1;
        int size = 10;

        if (null != searchPageInfo) {
            current = searchPageInfo.getPage();
            size = searchPageInfo.getSize();
        }

        IPage<RInfo> page = new Page<>(current, size);

        page = rInfoService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @Log(title = "--详情", businessType = BusinessType.QUERY)
    public RestResponse<RInfo> detail(@PathVariable("id") String id) {

        RInfo result = rInfoService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @Log(title = "--新增", businessType = BusinessType.INSERT)
    public RestResponse<?> add(@RequestBody RInfo add) {

        rInfoService.insert(add);

        return RestResponse.success();
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @Log(title = "--修改", businessType = BusinessType.UPDATE)
    public RestResponse<?> update(@PathVariable("id") String id, @RequestBody RInfo update) {

        update.setId("id");

        rInfoService.update(update);

        return RestResponse.success();
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @Log(title = "--删除", businessType = BusinessType.DELETE)
    public RestResponse<?> delete(@PathVariable("id") String id) {

        rInfoService.delete(id);

        return RestResponse.success();
    }

}