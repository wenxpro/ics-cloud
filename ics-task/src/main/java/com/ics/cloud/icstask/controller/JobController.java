package com.ics.cloud.icstask.controller;

import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.common.util.PageUtil;
import com.ics.cloud.icstask.config.QuartzJobManager;
import com.ics.cloud.icstask.job.JobQuartz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Api(description = "任务调度接口")
@RequestMapping(value = "/job")
@Slf4j
public class JobController {

    @Autowired
    private QuartzJobManager quartzJobManager;

    @ApiOperation(value = "添加简单任务")
    @PostMapping("/add")
    public BaseRetBean add(
            @RequestParam(value = "jobname", required = true) String jobName,
            @RequestParam(value = "groupname", required = true) String groupName,
            @RequestParam(value = "cron", required = true) String cron,
            @RequestParam(value = "map", required = false) Map map,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        try {
            quartzJobManager.addJob(JobQuartz.class, jobName, groupName, cron, map);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation(value = "删除简单任务")
    @DeleteMapping("/del/{jobname}/{groupname}")
    public BaseRetBean del(
            @PathVariable(value = "jobname", required = true) String jobname,
            @PathVariable(value = "groupname", required = true) String groupname,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        try {
            quartzJobManager.deleteJob(jobname, groupname);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }


    @ApiOperation(value = "查询所有简单任务")
    @PostMapping("/getAll")
    public BasePageRetBean getAll(
            @RequestParam(value = "pageSize", required = false, defaultValue = "8") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageFlag", required = false, defaultValue = "true") boolean pageFlag,
            HttpServletRequest request, HttpServletResponse response) {
        BasePageRetBean baseRetBean = new BasePageRetBean();
        baseRetBean.setRet(1);
        baseRetBean.setMsg("success");
        try {
            PageInfo pageInfo =
                    PageUtil.renderPage(quartzJobManager.getAllJob(), pageSize, pageNum, pageFlag);
            if (pageInfo != null) {
                baseRetBean.renderRet(pageInfo);
            }
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation(value = "暂停任务")
    @PostMapping("/pauseJob")
    public BaseRetBean pauseJob(@RequestParam(value = "jobname", required = true) String jobname,
                                @RequestParam(value = "groupname", required = true) String groupname,
                                HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        try {
            quartzJobManager.pauseJob(jobname, groupname);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation(value = "恢复任务")
    @PostMapping("/resumeJob")
    public BaseRetBean resumeJob(@RequestParam(value = "jobname", required = true) String jobname,
                                 @RequestParam(value = "groupname", required = true) String groupname,
                                 HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        try {
            quartzJobManager.resumeJob(jobname, groupname);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation(value = "删除任务")
    @DeleteMapping("/delete/{jobname}/{groupname}")
    public BaseRetBean delete(@PathVariable(value = "jobname", required = true) String jobname,
                              @PathVariable(value = "groupname", required = true) String groupname,
                              HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        try {
            quartzJobManager.deleteJob(jobname, groupname);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }
}
