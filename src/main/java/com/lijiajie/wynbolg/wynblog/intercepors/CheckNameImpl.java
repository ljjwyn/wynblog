package com.lijiajie.wynbolg.wynblog.intercepors;

import com.lijiajie.wynbolg.wynblog.annotation.CheckName;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class CheckNameImpl {

    @Pointcut("@annotation(com.lijiajie.wynbolg.wynblog.annotation.CheckName)")
    public void annotationPointcut() {
    }

    @Before("annotationPointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckName annotation = method.getAnnotation(CheckName.class);
        String value = annotation.value();
        System.out.println("准备"+value);
    }

    @After("annotationPointcut()")
    public void afterPointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CheckName annotation = method.getAnnotation(CheckName.class);
        String value = annotation.value();
        System.out.println("结束"+value);
    }
}