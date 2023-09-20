package ${package.Service};

import com.spyker.application.BaseTest;
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

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
 @RequiredArgsConstructor
 @Slf4j
public class ${table.serviceName} extends BaseTest {


  private final ${table.serviceName} service;

  @Test
  public void get(){

     ${entity} result = service.getById("1");
  }

  @Test
  public void delete(){
     service.delete("1");
  }

  @Test
  public void add(){

    ${entity} add = new ${entity}();

    <#list table.fields as field>
     <#if field.capitalName != "ModifyTime" && field.capitalName != "CreateTime" && field.capitalName != "Id">
    add.set${field.capitalName}("${field.propertyName}");
     </#if>
    </#list>

    service.insert(add);
  }

  @Test
  public void update(){

    ${entity} update = new ${entity}();

    <#list table.fields as field>
      update.set${field.capitalName}("${field.propertyName}");
    </#list>

    service.update(update);
  }

  @Test
  public void list(){

    ${entity}Search search = new ${entity}Search();

    <#list table.fields as field>
      search.set${field.capitalName}("${field.propertyName}");
    </#list>

    service.list(search);
  }

  @Test
  public void listPage(){

   IPage<${entity}> page = new Page<>(1, 10);

   ${entity}Search search = new ${entity}Search();

   <#list table.fields as field>
      search.set${field.capitalName}("${field.propertyName}");
   </#list>

   service.listPage(page, search);
  }


}
