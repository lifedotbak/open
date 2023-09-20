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
import java.util.Date;

import lombok.RequiredArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.grid.common.core.domain.R;

/**
* <p>
* ${table.comment!} 前端控制器
* </p>
*
* @author ${author}
* @since ${date}
*/
@RequiredArgsConstructor
@RestController
@Slf4j
@Tag(name = "${table.comment!}", description = "${table.comment!}")
@RequestMapping("/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private final ${table.serviceName} ${table.serviceName?uncap_first};

    @Operation(summary = "列表", description = "列表")
    @GetMapping("list")
    public R<List<${entity}>> list() {

        ${entity}Search search = new ${entity}Search();

        List<${entity}> result = ${table.serviceName?uncap_first}.list(search);

        log.info("result -->{}", result);

        return R.ok(result);
    }


    @Operation(summary = "列表（分页）", description = "列表（分页）")
    @GetMapping("list_page")
    public R<IPage<${entity}>> list_page(@RequestParam(defaultValue = "1") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {

        ${entity}Search search = new ${entity}Search();

        IPage<${entity}> page = new Page<>(pageNumber, pageSize);

        page = ${table.serviceName?uncap_first}.listPage(page, search);

        log.info("page -->{}", page);

        return R.ok(page);
    }


    @Operation(summary = "详情", description = "详情")
    @GetMapping("detail")
    public R<${entity}> detail(@RequestParam String id) {

        ${entity} result = ${table.serviceName?uncap_first}.get(id);

        return R.ok(result);
    }


    @Operation(summary = "新增", description = "新增")
    @PostMapping("add")
    public R<?> add(@RequestBody ${entity} add) {

        Date now = new Date();
        add.setCreateTime(now);
        add.setModifyTime(now);
        ${table.serviceName?uncap_first}.insert(add);

        return R.ok();
    }


    @Operation(summary = "修改", description = "修改")
    @PutMapping("update")
    public R<?> update(@RequestBody ${entity} update) {

        Date now = new Date();
        update.setModifyTime(now);
        ${table.serviceName?uncap_first}.update(update);

        return R.ok();
    }


    @Operation(summary = "删除", description = "删除")
    @DeleteMapping("delete")
    public R<?> delete(@RequestParam String id) {

        ${table.serviceName?uncap_first}.delete(id);

        return R.ok();
    }

}
