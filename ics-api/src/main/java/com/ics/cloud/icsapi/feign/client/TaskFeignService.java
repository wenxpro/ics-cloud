package com.ics.cloud.icsapi.feign.client;


import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.icsapi.config.FeignOAuth2RequestInterceptor;
import com.ics.cloud.icsapi.feign.fullback.TaskFeignServiceFullback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "ics-task",
        fallback = TaskFeignServiceFullback.class,
        configuration = FeignOAuth2RequestInterceptor.class)
public interface TaskFeignService {

    @PostMapping("/job/add")
    BaseRetBean add(
            @RequestParam(value = "jobname", required = true) String jobName,
            @RequestParam(value = "groupname", required = true) String groupName,
            @RequestParam(value = "cron", required = true) String cron,
            @RequestParam(value = "map", required = false) Map map);

    @DeleteMapping("/job/del/{jobname}/{groupname}")
    BaseRetBean del(
            @PathVariable(value = "jobname", required = true) String jobname,
            @PathVariable(value = "groupname", required = true) String groupname) ;


    @PostMapping("/job/getAll")
    BasePageRetBean getAll(
            @RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageFlag", required = false, defaultValue = "true") boolean pageFlag) ;

    @PostMapping("/job/pauseJob")
    BaseRetBean pauseJob(@RequestParam(value = "jobname", required = true) String jobname,
                                @RequestParam(value = "groupname", required = true) String groupname);

    @PostMapping("/job/resumeJob")
    BaseRetBean resumeJob(@RequestParam(value = "jobname", required = true) String jobname,
                                 @RequestParam(value = "groupname", required = true) String groupname);

    @DeleteMapping("/job/delete/{jobname}/{groupname}")
    BaseRetBean delete(@PathVariable(value = "jobname", required = true) String jobname,
                              @PathVariable(value = "groupname", required = true) String groupname) ;

}
