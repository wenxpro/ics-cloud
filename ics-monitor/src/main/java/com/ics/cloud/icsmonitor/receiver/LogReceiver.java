package com.ics.cloud.icsmonitor.receiver;


import com.alibaba.fastjson.JSON;
import com.ics.cloud.common.dao.Sys_access_logMapper;
import com.ics.cloud.common.model.Sys_access_log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RabbitListener(queues = "log-queue")
public class LogReceiver {

    @Autowired
    private Sys_access_logMapper sysAccessLogMapper;

    @RabbitHandler
    public void process(String message) {
        Sys_access_log accessLog = JSON.parseObject(message, Sys_access_log.class);
        try {
            sysAccessLogMapper.insert(accessLog);
        } catch (Exception e) {
            log.debug("日志收集错误:{}", e);
        }
    }
}