package com.spyker.application.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.spyker.application.entity.TestCode;
import com.spyker.application.search.TestCodeSearch;
import com.spyker.application.service.TestCodeService;
import com.spyker.framework.response.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * @since 2023-09-14
 */

@RequiredArgsConstructor
@RestController
@Tag(name = "", description = "")
@RequestMapping("/application/test-code")
@Slf4j
public class TestCodeController {

    private final TestCodeService testCodeService;

    /**
     * 列表查询
     *
     * @param search 查询条件
     * @return
     */
    @GetMapping("list")
    public RestResponse<List<TestCode>> list(TestCodeSearch search) {

        List<TestCode> result = testCodeService.query(search);

        return RestResponse.success(result);
    }

    /**
     * 分页查询
     *
     * @param search 查询条件
     * @return
     */
    @GetMapping("list_page")
    public RestResponse<IPage<TestCode>> list_page(TestCodeSearch search) {
        int current = 1;
        int size = 10;

        if (null != search) {
            current = search.getPage();
            size = search.getSize();
        }

        IPage<TestCode> page = new Page<>(current, size);

        page = testCodeService.queryPage(page, search);

        return RestResponse.success(page);
    }

    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<TestCode> detail(@RequestParam String id) {
        TestCode result = testCodeService.get(id);

        return RestResponse.success(result);
    }

    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody TestCode add) {

        return testCodeService.insert(add);
    }

    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody TestCode update) {

        return testCodeService.update(update);
    }

    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

        return testCodeService.delete(id);
    }

}