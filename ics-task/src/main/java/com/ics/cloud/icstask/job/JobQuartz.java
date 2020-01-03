package com.ics.cloud.icstask.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

/**
 * 自定义简单job
 */
@Component
@Slf4j
public class JobQuartz implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String taskName = jobExecutionContext.getJobDetail().getKey().getName();
        String groupName = jobExecutionContext.getJobDetail().getKey().getGroup();
        log.debug("组名: {} >> 任务名: {}  开始执行 times：{}", groupName, taskName, DateUtil.date());
    }
}
