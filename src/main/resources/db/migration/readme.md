# Flyway

## 一.特点

- 专一 Flyway 专注于搞数据库迁移、版本控制而并没有其它副作用。
- 强大 专为连续交付而设计。让Flyway在应用程序启动时迁移数据库。

## 二.工作机制

- Flyway 需要在 DB 中先创建一个 metadata 表 (缺省表名为 flyway_schema_history),
  在该表中保存着每次 migration （迁移）的记录, 记录包含 migration 脚本的版本号和 SQL 脚本的 checksum 值。

- Flyway 扫描文件系统或应用程序的类路径读取 DDL 和 DML 以进行迁移。根据metadata
  表进行检查迁移。如果脚本声明的版本号小于或等于标记为当前版本的版本号之一，将忽略它们。其余迁移是待处理迁移：可用，但未应用。最后按版本号对它们进行排序并按顺序执行
  并将执行结果写入 metadata 表。

## 三.规则

Flyway 是如何比较两个 SQL 文件的先后顺序呢？它采用 采用左对齐原则, 缺位用 0 代替 。举几个例子：

~~~
1.0.1.1 比 1.0.1 版本高。
1.0.10 比 1.0.9.4 版本高。

1.0.10 和 1.0.010 版本号一样高, 每个版本号部分的前导 0 会被忽略。
~~~

---
Flyway 将 SQL 文件分为 Versioned 、Repeatable 和 Undo 三种：

- Versioned 用于版本升级, 每个版本有唯一的版本号并只能执行一次.
- Repeatable 可重复执行, 当 Flyway检测到 Repeatable 类型的 SQL 脚本的 checksum 有变动, Flyway 就会重新应用该脚本.
  它并不用于版本更新, 这类的 migration 总是在 Versioned 执行之后才被执行。
- Undo 用于撤销具有相同版本的版本化迁移带来的影响。但是该回滚过于粗暴，过于机械化，一般不推荐使用。一般建议使用 Versioned
  模式来解决。

---

Flyway文件名称组成：

- 前缀:

~~~
  V 代表版本变迁(Versioned Migrations),
  U 代表撤销变迁(Undo Migrations)，
  R 代表可重复变迁(Repeatable Migrations)，一般为可重复执行的sql语句。
~~~

- 版本号: 唯一的版本号，比如V1.0.1
- 分隔符: __ (两个下划线)
- 描述信息: 描述信息
- 后缀: .sql

### PS: 其中，V开头的SQL执行优先级要比R开头的SQL优先级高

## 四.最佳实践

- 生产务必禁 spring.flyway.cleanDisabled=false 。
- 尽量避免使用 Undo 模式。
- 开发版本号尽量根据团队来进行多层次的命名避免混乱。比如 V1.0.1__ProjectName_{Feature|fix}_Developer_Description.sql
  ，这种命名同时也可以获取更多脚本的开发者和相关功能的信息。
- spring.flyway.outOfOrder 取值 生产上使用 false，开发中使用 true。
- 多个系统公用一个 数据库 schema 时配置spring.flyway.table 为不同的系统设置不同的 metadata 表名而不使用缺省值
  flyway_schema_history 。