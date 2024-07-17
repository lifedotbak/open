package com.spyker.framework.mybatisplus;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.spyker.framework.web.BaseController;

import lombok.Data;
import lombok.SneakyThrows;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodeGenerator {

    private static final String CODE_AUTHOR = "CodeGenerator";

    private static final String BASE_PACKAGE = "com.spyker";

    // private static final String applicationName = "application";

    private static final String MAIN_SOURE = "/src/main/java";

    private static final String TEST_SOURCE = "/src/test/java";

    private static final String YML_SOURE = "/src/main/resources/application-dev.yml";

    public static void main(String[] args) {

        String projectPath = System.getProperty("user.dir");

        String applicationName = scanner("applicationName(应用名称)!");

        List<String> tableNames = getTables(scanner("表名，多个英文逗号分割!所有表请输入all!"));

        generatorCode(tableNames, projectPath, applicationName);
    }

    /** 读取控制台内容 */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入" + tip + ":");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "!");
    }

    // 处理 all 情况
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

    private static void generatorCode(
            List<String> tableNames, String projectPath, String applicationName) {

        String xmlPath = projectPath + "/src/main/resources/mapper/" + applicationName;

        String outputDir = projectPath + MAIN_SOURE;

        String testOutputDir =
                projectPath + TEST_SOURCE + generatorTestDir(BASE_PACKAGE, applicationName);

        ApplicationConfig applicationConfig = readYaml();

        if (null == applicationConfig) {
            return;
        }

        String dbUrl = applicationConfig.getUrl();
        String dbUserName = applicationConfig.getUsername();
        String dbPassword = applicationConfig.getPassword();

        DataSourceConfig dataSourceConfig =
                new DataSourceConfig.Builder(dbUrl, dbUserName, dbPassword).build();
        AutoGenerator generator = new AutoGenerator(dataSourceConfig);

        // 2 全局配置
        GlobalConfig globalConfig =
                new GlobalConfig.Builder()
                        .disableOpenDir() // 禁止打开输出目录 默认值:true
                        .outputDir(outputDir) // 指定输出目录 /opt/baomidou/ 默认值:
                        // windows:D:// linux or mac : /tmp
                        .dateType(DateType.ONLY_DATE) // 设置时间类型为java.util.date
                        .enableSpringdoc() // 支持spring doc
                        .author(CODE_AUTHOR) //
                        .enableSpringdoc()
                        .build();

        generator.global(globalConfig);

        // 3 包配置
        // 3.1 自定义包名
        PackageConfig.Builder packageConfig =
                new PackageConfig.Builder()
                        .parent(BASE_PACKAGE) // 父包名 默认值:com.baomidou
                        .moduleName(applicationName) // 父包模块名 默认值:无
                        .pathInfo((Collections.singletonMap(OutputFile.xml, xmlPath)));

        // 4. 配置策略
        StrategyConfig strategyConfig =
                new StrategyConfig.Builder()
                        .addInclude(tableNames)
                        .controllerBuilder()
                        .enableRestStyle()
                        .enableHyphenStyle()
                        .enableFileOverride()
                        .superClass(BaseController.class)
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .mapperBuilder()
                        .entityBuilder()
                        .enableLombok()
                        .enableChainModel()
                        .enableFileOverride()
                        .disableSerialVersionUID()
                        .idType(IdType.ASSIGN_UUID)
                        .addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("create_by", FieldFill.INSERT),
                                new Column("modify_time", FieldFill.INSERT_UPDATE),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("update_by", FieldFill.INSERT_UPDATE))
                        .build();

        generator.strategy(strategyConfig);

        CustomFile controllerTestFile =
                new CustomFile.Builder()
                        .fileName("ControllerTest.java")
                        .enableFileOverride()
                        .filePath(testOutputDir)
                        .templatePath("/templates/controllerTest.java.ftl")
                        .packageName("controller")
                        .build();

        CustomFile serviceTestFile =
                new CustomFile.Builder()
                        .fileName("ServiceTest.java")
                        .enableFileOverride()
                        .filePath(testOutputDir)
                        .templatePath("/templates/serviceTest.java.ftl")
                        .packageName("service")
                        .build();

        CustomFile mapperTestFile =
                new CustomFile.Builder()
                        .fileName("MapperTest.java")
                        .enableFileOverride()
                        .filePath(testOutputDir)
                        .templatePath("/templates/mapperTest.java.ftl")
                        .packageName("mapper")
                        .build();

        CustomFile searchCustomFile =
                new CustomFile.Builder()
                        .fileName("Search.java")
                        .enableFileOverride()
                        .templatePath("/templates/search" + ".java.ftl")
                        .packageName("search")
                        .build();

        CustomFile redisRepository =
                new CustomFile.Builder()
                        .fileName("Repository.java")
                        .enableFileOverride()
                        .templatePath("/templates/redisRepository" + ".java.ftl")
                        .packageName("repository.redis")
                        .build();

        List<CustomFile> customFiles = new ArrayList<>();

        customFiles.add(controllerTestFile);
        customFiles.add(serviceTestFile);
        customFiles.add(mapperTestFile);
        customFiles.add(searchCustomFile);
        customFiles.add(redisRepository);

        InjectionConfig ic = new InjectionConfig.Builder().customFile(customFiles).build();

        generator.injection(ic);

        generator.packageInfo(packageConfig.build());

        generator.execute(new FreemarkerTemplateEngine());
    }

    private static String generatorTestDir(String basePackage, String applicationName) {

        String[] basePackageDirs = basePackage.split("\\.");

        String result = "";

        for (String dir : basePackageDirs) {
            result = result + "/" + dir;
        }

        result = result + "/" + applicationName;

        return result;
    }

    @SneakyThrows
    private static ApplicationConfig readYaml() {

        String projectPath = System.getProperty("user.dir");

        String yamlPath = projectPath + YML_SOURE;

        File yamlFile = new File(yamlPath);

        if (yamlFile.exists()) {

            Yaml yaml = new Yaml();

            InputStream is = new FileInputStream(yamlFile);

            Map<String, Object> obj = yaml.load(is);

            @SuppressWarnings("unchecked")
            Map<String, Object> spring = (Map<String, Object>) obj.get("spring");

            @SuppressWarnings("unchecked")
            Map<String, Object> datasource = (Map<String, Object>) spring.get("datasource");

            @SuppressWarnings("unchecked")
            Map<String, Object> druid = (Map<String, Object>) datasource.get("druid");

            String userName = (String) druid.get("username");
            String password = (String) druid.get("password");
            String url = (String) druid.get("url");

            ApplicationConfig applicationConfig = new ApplicationConfig();

            applicationConfig.setUsername(userName);
            applicationConfig.setPassword(password);
            applicationConfig.setUrl(url);

            return applicationConfig;
        }

        return null;
    }

    @Data
    public static class ApplicationConfig {

        private String username;
        private String password;
        private String url;
    }
}