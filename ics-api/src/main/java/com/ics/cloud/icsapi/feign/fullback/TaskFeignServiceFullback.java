package com.ics.cloud.icsapi.feign.fullback;

import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.icsapi.feign.client.TaskFeignService;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TaskFeignServiceFullback implements TaskFeignService {

    @Override
    public BaseRetBean add(String jobName, String groupName, String cron, Map map) {
        return null;
    }

    @Override
    public BaseRetBean del(String jobname, String groupname) {
        return null;
    }

    @Override
    public BasePageRetBean getAll(Integer pageSize, Integer pageNum, boolean pageFlag) {
        return null;
    }

    @Override
    public BaseRetBean pauseJob(String jobname, String groupname) {
        return null;
    }

    @Override
    public BaseRetBean resumeJob(String jobname, String groupname) {
        return null;
    }

    @Override
    public BaseRetBean delete(String jobname, String groupname) {
        return null;
    }
}
