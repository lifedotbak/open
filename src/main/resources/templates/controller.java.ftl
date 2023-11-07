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

import ${package.Parent}.search.${entity}Search;

import lombok.RequiredArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.spyker.framework.response.RestResponse;
import cn.dev33.satoken.annotation.SaCheckLogin;


/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
@SaCheckLogin
@RequiredArgsConstructor
@RestController
@Tag(name = "${table.comment!}", description = "${table.comment!}")
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Slf4j
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public RestResponse<List<${entity}>> list(${entity}Search search) {

        List<${entity}> result = ${table.serviceName?uncap_first}.query(search);

        return RestResponse.success(result);
    }


    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public RestResponse<IPage<${entity}>> list_page(${entity}Search search) {
        int current = 1;
        int size = 10;

        if (null != search) {
          current = search.getPage();
          size = search.getSize();
        }

        IPage<${entity}> page = new Page<>(current, size);

        page = ${table.serviceName?uncap_first}.queryPage(page, search);

        return RestResponse.success(page);
    }


    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public RestResponse<${entity}> detail(@RequestParam String id) {
      	${entity} result = ${table.serviceName?uncap_first}.get(id);

        return RestResponse.success(result);
    }


    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public RestResponse<?> add(@RequestBody ${entity} add) {

         ${table.serviceName?uncap_first}.insert(add);

         return RestResponse.success();
    }


    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public RestResponse<?> update(@RequestBody ${entity} update) {

         ${table.serviceName?uncap_first}.update(update);

         return RestResponse.success();
    }


    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public RestResponse<?> delete(@RequestParam String id) {

         ${table.serviceName?uncap_first}.delete(id);

         return RestResponse.success();
    }

}