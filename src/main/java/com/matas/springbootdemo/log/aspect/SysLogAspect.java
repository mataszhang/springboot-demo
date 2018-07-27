package com.matas.springbootdemo.log.aspect;

import com.matas.springbootdemo.log.annnotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author matas
 * @date 2018/7/26 7:10
 * @email mataszhang@163.com
 */
@Aspect
@Component
public class SysLogAspect {
    @Pointcut("@annotation(com.matas.springbootdemo.log.annnotation.SysLog)")
    public void pointcut() {
    }

    private static ThreadLocal<String> logContext = new ThreadLocal<>();


    @AfterReturning(value = "pointcut() && @annotation(sysLog)", returning = "result")
    public void saveLog(JoinPoint joinPoint, SysLog sysLog, Object result) {
        Object[] args = joinPoint.getArgs();
        String method = joinPoint.getSignature().getName();
        String context = sysLog.value();
        System.err.println("执行" + method + ",result=>" + result + ",操作=>" + context + "(" + logContext.get() + ")");
    }

    public static void addLog(String log) {
        logContext.set(log);
    }

}
