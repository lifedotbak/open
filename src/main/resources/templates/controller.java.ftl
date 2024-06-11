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
import ${package.Service}.${table.serviceName};

import com.spyker.framework.base.BaseController;
import com.spyker.framework.request.PageParamRequest;

import ${package.Parent}.search.${entity}Search;

import lombok.RequiredArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.spyker.framework.response.RestResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;
import com.spyker.framework.enums.BusinessType;
import com.spyker.framework.log.Log;
import org.springframework.web.bind.annotation.*;


/**
* ${table.comment!} 前端控制器
*
* @author ${author}
* @since ${date}
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

    @Operation(summary = "列表", description = "列表")
    @GetMapping("/")
    @Log(title = "${table.comment!}--列表", businessType = BusinessType.QUERY)
    public RestResponse<List<${entity}>> list(${entity}Search search) {

        List<${entity}> result = ${table.serviceName?uncap_first}.query(search);

        log.info("result------>{}", result);

        return RestResponse.success(result);
    }

    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("/page")
    @Log(title = "${table.comment!}--列表（分页）", businessType = BusinessType.QUERY)
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


    @Operation(summary = "详情", description = "详情")
    @GetMapping("/{id}")
    @Log(title = "${table.comment!}--详情", businessType = BusinessType.QUERY)
    public RestResponse<${entity}> detail(@PathVariable("id") String id) {

      	${entity} result = ${table.serviceName?uncap_first}.get(id);

        return RestResponse.success(result);
    }


    @Operation(summary = "新增", description = "新增")
    @PostMapping("/")
    @Log(title = "${table.comment!}--新增", businessType = BusinessType.INSERT)
    public RestResponse<${entity}> add(@RequestBody ${entity} add) {

         ${table.serviceName?uncap_first}.insert(add);

         return RestResponse.success();
    }


    @Operation(summary = "修改", description = "修改")
    @PutMapping("/{id}")
    @Log(title = "${table.comment!}--修改", businessType = BusinessType.UPDATE)
    public RestResponse<${entity}> update(@PathVariable("id") String id, @RequestBody ${entity} update) {

        update.setId(id);

        ${table.serviceName?uncap_first}.update(update);

        return RestResponse.success();
    }


    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("/{id}")
    @Log(title = "${table.comment!}--删除", businessType = BusinessType.DELETE)
    public RestResponse<${entity}> delete(@PathVariable("id") String id) {

         ${table.serviceName?uncap_first}.delete(id);

         return RestResponse.success();
    }

}