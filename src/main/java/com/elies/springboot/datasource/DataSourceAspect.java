package com.elies.springboot.datasource;

import com.elies.springboot.controller.UserController;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author 牟雪
 * @since 2018/4/13
 */

@Component
@Aspect
public class DataSourceAspect {
    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Pointcut("execution( * com.elies.springboot.dao.*.*(..))")
    public void dataSourcePointCut() {
    }
    @Before("dataSourcePointCut()")
    public void before(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] clazz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = clazz[0].getMethod(method, parameterTypes);
            if(m != null){
                String methodName = m.getName();
                if(methodName.startsWith("find") || methodName.startsWith("get")){
                    DynamicDataSourceHolder.markRead();
                }else{
                    DynamicDataSourceHolder.markWrite();
                }
            }
        } catch (Exception e) {
            log.error("current thread " + Thread.currentThread().getName() + " add data to ThreadLocal error", e);
        }
    }

    //执行完切面后，将线程共享中的数据源名称清空
    @After("dataSourcePointCut()")
    public void after(JoinPoint joinPoint){
        DynamicDataSourceHolder.reset();
    }
}
