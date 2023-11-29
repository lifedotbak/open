package ${package.Parent}.search;

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
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
<#if chainModel>
import lombok.experimental.Accessors;
</#if>
</#if>

/**
* <p>
* ${table.comment!}
* </p>
*
* @author ${author}
* @since ${date}
*/vg
<#if entityLombokModel>
@Data
    <#if chainModel>
@Accessors(chain = true)
    </#if>
</#if>
<#if table.convert>
</#if>
<#if springdoc>
@Schema(name = "${entity}Search对象", description = "${table.comment!}Search对象")
<#elseif swagger>
@ApiModel(value = "${entity}Search对象", description = "${table.comment!}Search对象")
</#if>
<#if superEntityClass??>
public class ${entity}Search extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
public class ${entity}Search extends Model<${entity}> {
<#elseif entitySerialVersionUID>
public class ${entity}Search implements Serializable {
<#else>
public class ${entity}Search {
</#if>
<#if entitySerialVersionUID>

private static final long serialVersionUID = 1L;

</#if>

    private Integer page = 1;
	private Integer size = 10;

<#-- ----------  BEGIN 字段循环遍历  ---------->
    <#list table.fields as field>

        <#if field.propertyName != "id" && field.propertyName != "modifyTime" && field.propertyName != "createTime" && field.propertyName != "updateTime">
        @Schema(description = "${field.comment}")
            private ${field.propertyType} ${field.propertyName};
        </#if>

    </#list>
<#-- ----------  END 字段循环遍历  ---------->

}