package ${package.Parent}.excel;

<#list table.importPackages as pkg>
import ${pkg};
</#list>
<#if springdoc>
import io.swagger.v3.oas.annotations.media.Schema;
<#elseif swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if entityLombokModel>
import lombok.Data;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
</#if>
import com.spyker.framework.poi.annotation.Excel;

/**
* <p>
* ${table.comment!}
* </p>
*/
<#if entityLombokModel>
@Data
<#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if table.convert>

</#if>
<#if springdoc>
@Schema(name = "${entity}Excel对象", description = "${table.comment!}Excel对象")
<#elseif swagger>
@ApiModel(value = "${entity}Excel对象", description = "${table.comment!}Excel对象")
</#if>
<#if superEntityClass??>
public class ${entity}Search extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity}Search extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity}Excel implements Serializable {
<#else>
public class ${entity}Excel {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>

        <#if field.propertyName != "id" && field.propertyName != "modifyTime" && field.propertyName != "createTime">
            @Excel(name = "${field.comment}", cellType = Excel.ColumnType.STRING)
            private ${field.propertyType} ${field.propertyName};
        </#if>

    </#list>

}
