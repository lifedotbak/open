# spingboot配置nacos说明

## 一. 初始化数据库

运行D:\nacos-server-2.3.0\conf\mysql-schema.sql

## 二. 配置nacos连接mysql

~~~
#*************** Config Module Related Configurations ***************#
### If use MySQL as datasource:
### Deprecated configuration property, it is recommended to use `spring.sql.init.platform` replaced.
spring.datasource.platform=mysql
# spring.sql.init.platform=mysql

### Count of DB:
db.num=1

### Connect URL of DB:
db.url.0=jdbc:mysql://127.0.0.1:3306/nacos?characterEncoding=utf8&serverTimezone=UTC&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
db.user.0=root
db.password.0=123456
~~~

### PS：

如果没有执行mysql初始化语句，启动nacos会报错

## 三. springboot配置

application.yml配置文件添加

~~~
################ nacos配置 ##############
nacos:
  config:
    server-addr: 127.0.0.1:8848
    # 配置 ID，采用类似 package.class（如com.taobao.tc.refund.log.level）的命名规则保证全局唯一性，class部分建议是配置的业务含义。
    # 全部字符小写。只允许英文字符和 4 种特殊字符（”.”、”:”、”-”、”_”），不超过 256 字节。
    data-id: com.spyker.open
    # 配置分组，建议填写产品名:模块名（Nacos）保证唯一性，只允许英文字符和4种特殊字符（”.”、”:”、”-”、”_”），不超过128字节。
    group: dev
    auto-refresh: true
    # endpoint--->springboot endpoint暂时配置为空，但是不能少了这个字段，负责启动报错
    endpoint:
~~~

### PS：

endpoint 配置必须存在，配置为空，负责应用启动失败