// package com.spyker.framework.config;
//
// import com.alibaba.druid.pool.DruidDataSource;
//
// import lombok.RequiredArgsConstructor;
//
// import org.springframework.context.annotation.Bean;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.RedisSerializer;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;
// import org.springframework.transaction.annotation.EnableTransactionManagement;
//
// import java.sql.SQLException;
//
/// ** RedisTemplate 开启事务配置 */
//// @Configuration
/// ** 配置Spring Context以实现 声明式事务管理。 */
// @EnableTransactionManagement
// @RequiredArgsConstructor
// public class RedisTemplateTransactionConfig {
//
//    private final DruidDataSource druidDataSource;
//
//    /**
//     * 默认情况下，RedisTemplate 不参与管理Spring事务。如果你想让 RedisTemplate 在使用 @Transactional 或
//     * TransactionTemplate 时利用Redis事务，你需要通过设置 setEnableTransactionSupport(true) 为每个 RedisTemplate
//     * 明确启用事务支持。启用事务支持将 RedisConnection 绑定到由 ThreadLocal 支持的当前事务。如果事务完成时没有错误，Redis事务会以 EXEC
//     * 的方式提交，否则以 DISCARD 的方式回滚。Redis 事务是面向批处理的。在一个正在进行的事务中发出的命令是排队的，只有在提交事务时才应用。
//     *
//     * @param factory
//     * @return
//     */
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(factory);
//
//        redisTemplate.setKeySerializer(RedisSerializer.string());
//        redisTemplate.setValueSerializer(RedisSerializer.json());
//
//        // 配置 RedisTemplate，使其通过绑定连接到当前线程来参与事务。
//        redisTemplate.setEnableTransactionSupport(true);
//
//        return redisTemplate;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() throws SQLException {
//        // 事务管理需要一个 PlatformTransactionManager。Spring Data Redis并没有附带 PlatformTransactionManager
//        // 的实现。假设你的应用程序使用JDBC，Spring Data Redis可以通过使用现有的事务管理器参与事务。
//        return new DataSourceTransactionManager(druidDataSource);
//    }
// }