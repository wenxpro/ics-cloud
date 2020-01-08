package com.ics.cloud.icsapi.api.task.controller;

import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.icsapi.common.controller.IcsApiController;
import com.ics.cloud.icsapi.feign.client.TaskFeignService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController extends IcsApiController {

    @Autowired
    private TaskFeignService taskFeignService;

    @ApiOperation(value = "添加简单任务")
    @PostMapping("/add")
    public BaseRetBean add(
            @RequestParam(value = "jobname", required = true) String jobName,
            @RequestParam(value = "groupname", required = true) String groupName,
            @RequestParam(value = "cron", required = true) String cron,
            @RequestParam(value = "map", required = false) Map map,
            HttpServletRequest request, HttpServletResponse response) {
       return taskFeignService.add(jobName, groupName, cron, map);
    }

    @ApiOperation(value = "删除简单任务")
    @DeleteMapping("/del/{jobname}/{groupname}")
    public BaseRetBean del(
            @PathVariable(value = "jobname", required = true) String jobname,
            @PathVariable(value = "groupname", required = true) String groupname,
            HttpServletRequest request, HttpServletResponse response) {
       return taskFeignService.del(jobname, groupname);
    }


    @ApiOperation(value = "查询所有简单任务")
    @PostMapping("/getAll")
    public BasePageRetBean getAll(
            @RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageFlag", required = false, defaultValue = "true") boolean pageFlag,
            HttpServletRequest request, HttpServletResponse response) {
       return taskFeignService.getAll(pageSize, pageNum, pageFlag);
    }

    @ApiOperation(value = "暂停任务")
    @PostMapping("/pauseJob")
    public BaseRetBean pauseJob(@RequestParam(value = "jobname", required = true) String jobname,
                                @RequestParam(value = "groupname", required = true) String groupname,
                                HttpServletRequest request, HttpServletResponse response) {
        return taskFeignService.pauseJob(jobname, groupname);
    }

    @ApiOperation(value = "恢复任务")
    @PostMapping("/resumeJob")
    public BaseRetBean resumeJob(@RequestParam(value = "jobname", required = true) String jobname,
                                 @RequestParam(value = "groupname", required = true) String groupname,
                                 HttpServletRequest request, HttpServletResponse response) {
        return taskFeignService.resumeJob(jobname, groupname);
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping("/delete/{jobname}/{groupname}")
    public BaseRetBean delete(@PathVariable(value = "jobname", required = true) String jobname,
                              @PathVariable(value = "groupname", required = true) String groupname,
                              HttpServletRequest request, HttpServletResponse response) {
        return taskFeignService.delete(jobname, groupname);
    }
}
