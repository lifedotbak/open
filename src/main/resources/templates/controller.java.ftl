package ${package.Controller};
import org.springframework.web.bind.annotation.RequestMapping;
<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>
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
import ${package.Entity}.${entity};
import ${package.Parent}.excel.${entity}Excel;
import ${package.Service}.${table.serviceName};

import com.spyker.framework.web.BaseController;
import com.spyker.framework.web.request.PageParamRequest;

import ${package.Parent}.search.${entity}Search;

import lombok.RequiredArgsConstructor;
import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.spyker.framework.web.response.RestResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spyker.framework.constants.BusinessTypeEnum;
import com.spyker.framework.log.annotation.ControllerLogAnnotation;
import org.springframework.web.bind.annotation.*;


/**
* ${table.comment!} 前端控制器
*
*/
@Tag(name = "${table.comment!}", description = "${table.comment!}")
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Slf4j
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

// @formatter:off

    private final ${table.serviceName} ${table.serviceName?uncap_first};
    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;


    @Operation(summary = "导出模板", description = "导出模板")
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) {
        ExcelUtil<${entity}Excel> util = new ExcelUtil<>(${entity}Excel.class);
        util.importTemplateExcel(response, "用户数据");

    }


    @SneakyThrows
    @Operation(summary = "数据导入", description = "数据导入")
    @PostMapping("/importExcel")
    public void importExcel(MultipartFile file) {

       ExcelUtil<${entity}Excel> util = new ExcelUtil<>(${entity}Excel.class);
       List<${entity}Excel> importExcels = util.importExcel(file.getInputStream(), 0);

       ${table.serviceName?uncap_first}.importExcel(importExcels);
    }

    @Operation(summary = "数据导出", description = "数据导出")
    @PostMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {

        ${entity}Search search = new ${entity}Search();

        List<${entity}> list = ${table.serviceName?uncap_first}.query(search);

        List<${entity}Excel> excelList = new ArrayList<>();

        for (${entity} item : list) {

            ${entity}Excel excel = new ${entity}Excel();
            BeanUtils.copyProperties(item, excel);
            excelList.add(excel);
        }

        ExcelUtil<${entity}Excel> util = new ExcelUtil<>(${entity}Excel.class);
        util.exportExcel(response, excelList, "用户数据");
    }

    @SaCheckPermission(value = "user:get",orRole = "admin")
    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @ControllerLogAnnotation(title = "${table.comment!}--列表", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<List<${entity}>> list(${entity}Search search) {

        List<${entity}> result = ${table.serviceName?uncap_first}.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }


    @SaCheckPermission(value = "user:get",orRole = "admin")
    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @ControllerLogAnnotation(title = "${table.comment!}--列表（分页）", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<IPage<${entity}>> listPage(@ModelAttribute ${entity}Search search) {
        int current = 1;
        int size = 10;

        if (null != search) {
        current = search.getPage();
        size = search.getSize();
        }

        IPage<${entity}> page = new Page<>(current, size);

        page = ${table.serviceName?uncap_first}.queryPage(page, search);

        log.info("page------>{}", page);

        return RestResponse.success(page);
    }

    @SaCheckPermission(value = "user:get",orRole = "admin")
    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @ControllerLogAnnotation(title = "${table.comment!}--详情", businessType = BusinessTypeEnum.QUERY)
    public RestResponse<${entity}> detail(@PathVariable("id") String id) {

        ${entity} result = ${table.serviceName?uncap_first}.get(id);

        return RestResponse.success(result);
    }

    @SaCheckRole("admin")
    @SaCheckPermission("admin:add")
    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @ControllerLogAnnotation(title = "${table.comment!}--新增", businessType = BusinessTypeEnum.INSERT)
    public RestResponse<${entity}> add(@RequestBody ${entity} add) {

        ${table.serviceName?uncap_first}.insert(add);

        return RestResponse.success();
    }

    @SaCheckRole("admin")
    @SaCheckPermission("admin:update")
    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @ControllerLogAnnotation(title = "${table.comment!}--修改", businessType = BusinessTypeEnum.UPDATE)
    public RestResponse<${entity}> update(@PathVariable("id") String id, @RequestBody ${entity} update) {

        update.setId(id);

        ${table.serviceName?uncap_first}.update(update);

        return RestResponse.success();
    }


    @SaCheckRole("admin")
    @SaCheckPermission("admin:delete")
    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @ControllerLogAnnotation(title = "${table.comment!}--删除", businessType = BusinessTypeEnum.DELETE)
    public RestResponse<${entity}> delete(@PathVariable("id") String id) {

        ${table.serviceName?uncap_first}.delete(id);

        return RestResponse.success();
    }

}
