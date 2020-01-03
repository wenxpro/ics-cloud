package com.ics.cloud.common.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.ics.cloud.common.bean.ReqParamBean;
import com.ics.cloud.common.model.Sys_access_log;
import com.ics.cloud.common.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
@Component
public class SysAccessLog {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Pointcut("execution (* com.ics..*Controller.*(..))")
    public void AppAccessLog() {
    }

    //环绕通知,环绕增强，相当于MethodInterceptor
    @Around(value = "AppAccessLog()")
    public Object doArround(ProceedingJoinPoint pjp) throws Exception {
        log.debug("[ACTION_Method] : {}", pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
        Sys_access_log model = new Sys_access_log();
        model.setRid(IdUtil.fastUUID());
        long stime = System.currentTimeMillis();

        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest req = attributes.getRequest();
            // 记录下请求内容
            log.debug("[HTTP_URL <<<] : {}", req.getRequestURL().toString());
            log.debug("[HTTP_METHOD] : {}", req.getMethod());
            log.debug("[REMOTE_IP] : {}", req.getRemoteAddr());

            model.setOptclasses(pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
            model.setOptmethod(req.getServletPath());
            model.setCreatetime(DateUtil.date());
            model.setOpthostip(HttpUtil.getClientIP(req));
            model.setOpthostname(req.getRemoteAddr());

            //处理参数
            ReqParamBean reqParamBean = HttpUtil.getReqParam(req);
            model.setOptkey(reqParamBean.getKey());
            model.setOptcontent(reqParamBean.getParameterJson());
            //运行方法
            Object o = pjp.proceed();
            return o;
        } catch (Throwable throwable) {
            throw new Exception(throwable.getMessage(), throwable);
        } finally {
            long etime = System.currentTimeMillis();
            model.setComplettime(DateUtil.date());
            rabbitTemplate.convertAndSend("log-queue", JSON.toJSONString(model));
            log.debug("[ACTION_TIME] : {}", (etime - stime) + "ms");
        }
    }
}
