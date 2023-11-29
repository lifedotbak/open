package com.spyker.commons.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spyker.framework.core.BaseController;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.commons.entity.RankInfo;
import com.spyker.commons.service.RankInfoService;

import com.spyker.framework.core.BaseController;

import com.spyker.commons.search.RankInfoSearch;

import lombok.RequiredArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.spyker.framework.response.RestResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import org.springframework.web.bind.annotation.*;


/**
* <p>
* Rank表 前端控制器
* </p>
*
* @author CodeGenerator
* @since 2023-11-28
*/
@Tag(name = "Rank表", description = "Rank表")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("/commons/rank-info")
@Slf4j
public class RankInfoController extends BaseController {

    private final RankInfoService rankInfoService;

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @Log(title = "Rank表--列表", businessType = BusinessType.QUERY)
    public RestResponse<List<RankInfo>> list(RankInfoSearch search) {

        List<RankInfo> result = rankInfoService.query(search);

        return RestResponse.success(result);
    }


    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @Log(title = "Rank表--列表（分页）", businessType = BusinessType.QUERY)
    public RestResponse<IPage<RankInfo>> list_page(@ModelAttribute RankInfoSearch search, @ModelAttribute SearchPageInfo searchPageInfo) {
        int current = 1;
        int size = 10;

        if (null != searchPageInfo) {
          current = searchPageInfo.getPage();
          size = searchPageInfo.getSize();
        }

        IPage<RankInfo> page = new Page<>(current, size);

        page = rankInfoService.queryPage(page, search);

        return RestResponse.success(page);
    }


    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @Log(title = "Rank表--详情", businessType = BusinessType.QUERY)
    public RestResponse<RankInfo> detail(@PathVariable("id") String id) {
      	RankInfo result = rankInfoService.get(id);

        return RestResponse.success(result);
    }


    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @Log(title = "Rank表--新增", businessType = BusinessType.INSERT)
    public RestResponse<?> add(@RequestBody RankInfo add) {

         rankInfoService.insert(add);

         return RestResponse.success();
    }


    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @Log(title = "Rank表--修改", businessType = BusinessType.UPDATE)
    public RestResponse<?> update(@PathVariable("id") String id, @RequestBody RankInfo update) {

         update.setId("id");

         rankInfoService.update(update);

         return RestResponse.success();
    }


    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @Log(title = "Rank表--删除", businessType = BusinessType.DELETE)
    public RestResponse<?> delete(@PathVariable("id") String id) {

         rankInfoService.delete(id);

         return RestResponse.success();
    }

}