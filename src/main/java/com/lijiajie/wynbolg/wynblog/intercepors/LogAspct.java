package com.lijiajie.wynbolg.wynblog.intercepors;

import com.lijiajie.wynbolg.wynblog.annotation.Log;
import com.lijiajie.wynbolg.wynblog.mapper.ActionLogMapper;
import com.lijiajie.wynbolg.wynblog.pojo.ActionLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


@Aspect
@Configuration
@Component
public class LogAspct {

//    @Autowired
//    private ActionLog actionLog;

    @Autowired
    private ActionLogMapper actionLogMapper;

    private Logger logger = LoggerFactory.getLogger(LogAspct.class);

    @Pointcut("@annotation(com.lijiajie.wynbolg.wynblog.annotation.Log)")
    public void pointcut() {}

    @Before("pointcut()")
    public void beforePointcut(JoinPoint joinPoint) {
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        String value = annotation.value();
        //System.out.println("操作"+value);
    }

    /**
     * 正常情况返回
     *
     * @param joinPoint 切入点
     */
    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) throws Exception {
        handleLog(joinPoint, null);
    }

    /**
     * 异常信息拦截
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "pointcut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) throws Exception {
        handleLog(joinPoint, e);
    }

    private void handleLog(final JoinPoint joinPoint, final Exception e) throws Exception{
        MethodSignature methodSignature =  (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Log annotation = method.getAnnotation(Log.class);
        Date now = new Date();
        String value = annotation.value();
        ActionLog actionLog = new ActionLog();
        System.out.println("操作:"+value);
        actionLog.setLogAction(value);
        actionLog.setLogDate(now);
        if(e!=null){
            actionLog.setLogError(e.getMessage());
        }
        actionLogMapper.insertLog(actionLog);
    }
}

