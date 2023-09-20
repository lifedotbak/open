package com.platform.web.template;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    private static final String auhtor = "CodeGenerator";
    private static final String dbUrl = "jdbc:mysql://192.168.200.65:3306/grid-cloud-link?useUnicode=true" +
        "&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8" +
        "&rewriteBatchedStatements=true";

    private static final String dbUserName = "root";
    private static final String dbPassword = "Grid@654321";

    private static final String basePackage = "com.platform";

    private static final String applicationName = "web";

    private static final String entity = "domain";

    private static final String mainSoure = "/src/main/java";

    private static final String testSoure = "/src/test/java";

    private static final String moduleName = "";

    private static final String projectName = "/caojiatan-energy";

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");
        String subprojectPath = projectPath + moduleName + projectName;

        Scanner scanner = new Scanner(System.in);
        List<String> tableNames = getTables(scanner("表名，多个英文逗号分割!所有表请输入all!"));

        //        generatorCode(tableNames, subprojectPath);

        generatorCode(tableNames, subprojectPath);

    }

    // 处理 all 情况
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    private static void generatorCode(List<String> tableNames, String subprojectPath) {

        String xmlPath = subprojectPath + "/src/main/resources/mapper/" + applicationName;

        String outputDir = subprojectPath + mainSoure;

        DataSourceConfig dataSourceConfig = new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassword).build();
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);

        // 2 全局配置
        GlobalConfig globalConfig = new GlobalConfig.Builder().disableOpenDir() // 禁止打开输出目录	默认值:true
            .outputDir(outputDir) // 指定输出目录	/opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
            .author(auhtor) // TODO 作者名	默认值:作者
            .dateType(DateType.ONLY_DATE) // 指定java.util.Date类型
            .enableSpringdoc().build();

        generator.global(globalConfig);

        // 3 包配置
        // 3.1 自定义包名
        PackageConfig.Builder packageConfig = new PackageConfig.Builder().parent(basePackage) // 父包名	默认值:com.baomidou
            .moduleName(applicationName) // 父包模块名	默认值:无
            .entity(entity).pathInfo((Collections.singletonMap(OutputFile.xml, xmlPath)));

        //4. 配置策略
        StrategyConfig strategyConfig = new StrategyConfig.Builder().addInclude(tableNames)

            .controllerBuilder().enableRestStyle().enableHyphenStyle().enableFileOverride()

            .serviceBuilder().formatServiceFileName("%sService")

            .mapperBuilder()

            .entityBuilder()

            .enableLombok().enableFileOverride().idType(IdType.AUTO).addTableFills(new Column("create_time",
                FieldFill.INSERT), new Column("modify_time", FieldFill.INSERT_UPDATE)).build();

        generator.strategy(strategyConfig);

        CustomFile searchCustomFile =
            new CustomFile.Builder().fileName("Search.java").enableFileOverride().templatePath("/templates/search" +
                ".java.ftl").packageName("search").build();

        InjectionConfig ic = new InjectionConfig.Builder().customFile(searchCustomFile).build();

        generator.injection(ic);

        generator.packageInfo(packageConfig.build());

        generator.execute(new FreemarkerTemplateEngine());

    }

}