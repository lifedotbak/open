package ${package.Service};

import com.spyker.BaseTest;
import ${package.Service}.${table.serviceName};
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};

import ${package.Parent}.search.${entity}Search;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
*
* ${table.comment!} 服务测试类
*
* @author ${author}
* @since ${date}
*/
@Slf4j
public class ${table.serviceName}Test extends BaseTest {

// @formatter:off

    @Autowired
    private ${table.serviceName} service;

    @Test
    public void get(){
        ${entity} result = service.getById("1");
        log.info("result------>{}", result);
    }

    @Test
    public void delete(){
        service.delete("1");
    }

    @Test
    public void add(){
        ${entity} add = new ${entity}();

        <#list table.fields as field>
            <#if !field.keyFlag && !field.capitalName?contains("Time")>

                <#if field.propertyType == 'Integer'>
                    add.set${field.capitalName}(1);
                </#if>

                <#if field.propertyType == 'String'>
                    add.set${field.capitalName}("${field.propertyName}");
                </#if>

            </#if>
        </#list>

        log.info("add------>{}", add);

        service.insert(add);
    }

    @Test
    public void update(){
        ${entity} update = new ${entity}();

        <#list table.fields as field>
            <#if !field.capitalName?contains("Time")>
                <#if field.propertyType == 'Integer'>
                    update.set${field.capitalName}(1);
                </#if>

                <#if field.propertyType=='String'>
                    update.set${field.capitalName}("${field.propertyName}");
                </#if>
            </#if>
        </#list>

        log.info("update------>{}", update);

        service.update(update);
    }

    @Test
    public void query(){
        ${entity}Search search = new ${entity}Search();

        <#list table.fields as field>
            <#if !field.keyFlag && !field.capitalName?contains("Time")>
                <#if field.propertyType == 'Integer'>
                    search.set${field.capitalName}(1);
                </#if>

                <#if field.propertyType=='String'>
                    search.set${field.capitalName}("${field.propertyName}");
                </#if>
            </#if>
        </#list>

        service.query(search);
    }

    @Test
    public void queryPage(){
        IPage<${entity}> page = new Page<>(1, 10);

        ${entity}Search search = new ${entity}Search();

        <#list table.fields as field>
            <#if !field.keyFlag && !field.capitalName?contains("Time")>

                <#if field.propertyType == 'Integer'>
                    search.set${field.capitalName}(1);
                </#if>

                <#if field.propertyType=='String'>
                    search.set${field.capitalName}("${field.propertyName}");
                </#if>

            </#if>
        </#list>

        service.queryPage(page, search);
    }

}