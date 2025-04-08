package ${package.Parent}.view;

<#list table.importPackages as pkg>
    import ${pkg};
</#list>
import io.swagger.v3.oas.annotations.media.Schema;
<#if entityLombokModel>
    import lombok.Data;
    import lombok.EqualsAndHashCode;
    import lombok.experimental.Accessors;
</#if>

/**
* ${table.comment!}视图对象
*/
<#if entityLombokModel>
    @Data
    <#if superEntityClass??>
        @EqualsAndHashCode(callSuper = true)
    <#else>
        @EqualsAndHashCode(callSuper = false)
    </#if>
    @Accessors(chain = true)
</#if>
<#if table.convert>
    @TableName("${table.name}")
</#if>
@Schema(name = "${entity}对象", description="${table.comment!}对象")
@ToString
<#if superEntityClass??>
    public class ${entity}View extends ${superEntityClass}<#if activeRecord><${entity}></#if> {
<#elseif activeRecord>
    public class ${entity}View extends Model<${entity}> {
<#else>
    public class ${entity}View implements Serializable {
</#if>

<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>

    <#if field.comment!?length gt 0>
        <#if springdoc>
            @Schema(description = "${field.comment}")
        <#elseif swagger>
            @ApiModelProperty("${field.comment}")
        <#else>
            /**
            * ${field.comment}
            */
        </#if>
    </#if>
    private ${field.propertyType} ${field.propertyName};
</#list>

}
