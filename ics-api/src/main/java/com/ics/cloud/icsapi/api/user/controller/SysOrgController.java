package com.ics.cloud.icsapi.api.user.controller;

import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.common.bean.HandleBean;
import com.ics.cloud.common.model.Sys_organization;
import com.ics.cloud.common.system.service.SysOrgService;
import com.ics.cloud.common.util.PageUtil;
import com.ics.cloud.icsapi.common.controller.IcsApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/org")
@Api(description = "机构接口")
@Slf4j
public class SysOrgController extends IcsApiController {

    @Autowired
    private SysOrgService orgService;

    @ApiOperation("添加方法")
    @PostMapping(value = "/add")
    public BaseRetBean add(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "parent_id", required = true) String parent_id,
            @RequestParam(value = "description", required = false) String description,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        Sys_organization record = new Sys_organization();
        BeanUtils.copyProperties(new HandleBean().post(super.getUserId(request)), record);
        record.setName(name);
        record.setParent_id(parent_id);
        record.setDescription(description);
        try {
            orgService.insertSelective(record);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("删除方法")
    @DeleteMapping(value = "/delete/{id}")
    public BaseRetBean delete(
            @PathVariable(value = "id", required = true) String id,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        Sys_organization record = new Sys_organization();
        String userid = super.getUserId(request);
        BeanUtils.copyProperties(new HandleBean().delete(id, userid), record);
        try {
            orgService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("修改方法")
    @PostMapping(value = "/update")
    public BaseRetBean update(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "parent_id", required = false) String parent_id,
            @RequestParam(value = "description", required = false) String description, HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");

        Sys_organization record = new Sys_organization();
        record.setId(id);
        record.setName(name);
        record.setParent_id(parent_id);
        record.setDescription(description);
        try {
            orgService.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("查询全部方法")
    @PostMapping(value = "/queryAll")
    public BasePageRetBean queryAll(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageFlag", required = false, defaultValue = "true") boolean pageFlag,
            HttpServletRequest request, HttpServletResponse response) {
        BasePageRetBean baseRetBean = new BasePageRetBean();
        baseRetBean.setRet(1);
        baseRetBean.setMsg("success");
        try {
            PageInfo pageInfo = orgService.queryAll(pageNum, pageSize, pageFlag);
            baseRetBean.renderRet(pageInfo);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("查询全部方法")
    @PostMapping(value = "/queryTree")
    public BasePageRetBean queryTree(
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageFlag", required = false, defaultValue = "true") boolean pageFlag,
            HttpServletRequest request, HttpServletResponse response) {
        BasePageRetBean baseRetBean = new BasePageRetBean();
        baseRetBean.setRet(1);
        baseRetBean.setMsg("success");
        try {
            List list = orgService.queryTreeList();
            PageInfo pageInfo = PageUtil.renderPage(list, pageSize, pageNum, pageFlag);
            baseRetBean.renderRet(pageInfo);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }
}
