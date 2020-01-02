package com.ics.cloud.common.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class SysServerLog {

    @Pointcut("execution (* com.ics.cloud..*Service.*(..))")
    public void SysServerLog() {
    }


    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around(value = "SysServerLog()")
    public Object doArround(ProceedingJoinPoint pjp) throws Exception {
        long stime = System.currentTimeMillis();
        log.debug("[Service_Method] : {} ", pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        try {
            Object o = pjp.proceed();
            return o;
        } catch (Throwable throwable) {
            throw new Exception(throwable.getMessage(), throwable);
        } finally {
            long etime = System.currentTimeMillis();
            log.debug("[Service_TIME] : {}ms", (etime - stime));
        }
    }
}
