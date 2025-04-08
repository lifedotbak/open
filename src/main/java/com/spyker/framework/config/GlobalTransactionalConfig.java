package com.spyker.framework.config;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/** 全局事务管理器 */
@Slf4j
@Aspect
@Configuration
public class GlobalTransactionalConfig {

    /** 配置方法过期时间，默认-1,永不超时 */
    private static final int METHOD_TIME_OUT = 5000;

    @Autowired GlobalTransactionalConfigProperties properties;

    /** 事务管理器 */
    @Autowired PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        /*事务管理规则，声明具备事务管理的方法名**/
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        /*只读事务，不做更新操作*/
        RuleBasedTransactionAttribute readOnly = new RuleBasedTransactionAttribute();
        readOnly.setReadOnly(true);
        readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);
        /*当前存在事务就使用当前事务，当前不存在事务就创建一个新的事务*/
        RuleBasedTransactionAttribute required = new RuleBasedTransactionAttribute();
        /*抛出异常后执行切点回滚,这边你可以更换异常的类型*/
        required.setRollbackRules(
                Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        /*PROPAGATION_REQUIRED:事务隔离性为1，若当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务。这是默认值*/
        required.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        /*设置事务失效时间，如果超过5秒，则回滚事务*/
        required.setTimeout(METHOD_TIME_OUT);
        Map<String, TransactionAttribute> attributesMap = new HashMap<>(30);

        log.info("requiredMethods-->{}", properties.getRequiredMethods());
        log.info("readOnlyMethods-->{}", properties.getReadOnlyMethods());

        // 默认开启事务方法
        if (null == properties.getRequiredMethods()
                || properties.getReadOnlyMethods().size() == 0) {
            attributesMap.put("save*", required);
            attributesMap.put("remove*", required);
            attributesMap.put("update*", required);
            attributesMap.put("batch*", required);
            attributesMap.put("clear*", required);
            attributesMap.put("add*", required);
            attributesMap.put("append*", required);
            attributesMap.put("modify*", required);
            attributesMap.put("edit*", required);
            attributesMap.put("insert*", required);
            attributesMap.put("delete*", required);
            attributesMap.put("do*", required);
            attributesMap.put("create*", required);
            attributesMap.put("import*", required);
        }

        // 设置增删改上传等使用事务
        for (String method : properties.getRequiredMethods()) {
            attributesMap.put(method, required);
        }

        // 默认查询开启只读
        if (null == properties.getReadOnlyMethods()
                || properties.getReadOnlyMethods().size() == 0) {

            attributesMap.put("select*", readOnly);
            attributesMap.put("get*", readOnly);
            attributesMap.put("valid*", readOnly);
            attributesMap.put("list*", readOnly);
            attributesMap.put("count*", readOnly);
            attributesMap.put("find*", readOnly);
            attributesMap.put("load*", readOnly);
            attributesMap.put("search*", readOnly);
        }

        // 查询开启只读
        for (String method : properties.getReadOnlyMethods()) {
            attributesMap.put(method, readOnly);
        }

        source.setNameMap(attributesMap);
        return new TransactionInterceptor(transactionManager, source);
    }

    /** 设置切面=切点pointcut+通知TxAdvice */
    @Bean
    public Advisor txAdviceAdvisor() {

        /* 声明切点的面：切面就是通知和切入点的结合。通知和切入点共同定义了关于切面的全部内容——它的功能、在何时和何地完成其功能*/
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        /*声明和设置需要拦截的方法,用切点语言描写*/
        pointcut.setExpression(properties.getPointcut());
        /*设置切面=切点pointcut+通知TxAdvice*/
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
