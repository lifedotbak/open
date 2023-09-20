<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package.Mapper}.${table.mapperName}">

	<select id="listPage" resultType="${package.Entity}.${entity}" parameterType="com.grid.link.search.${entity}Search">
        SELECT
  			a.*
  		FROM
  			${table.name} a
  		<where>
            <#list table.fields as field>
                <#if field.propertyName != "id" && field.propertyName != "modifyTime" && field.propertyName != "createTime">
                    <if test="null != search.${field.propertyName} " >
                        and a.${field.name} = <#noparse>#{</#noparse>search.${field.propertyName}<#noparse>}</#noparse>
                    </if>
                </#if>
            </#list>
  		</where>

    </select>

    <select id="list" resultType="${package.Entity}.${entity}" parameterType="com.grid.link.search.${entity}Search">
        SELECT
            a.*
        FROM
            ${table.name} a
        <where>
            <#list table.fields as field>
                <#if field.propertyName != "id" && field.propertyName != "modifyTime" && field.propertyName != "createTime">
                    <if test="null != ${field.propertyName} " >
                      and a.${field.name} = <#noparse>#{</#noparse>${field.propertyName}<#noparse>}</#noparse>
                    </if>
                </#if>
            </#list>
        </where>

    </select>

</mapper>
