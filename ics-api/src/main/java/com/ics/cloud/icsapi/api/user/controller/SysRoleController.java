package com.ics.cloud.icsapi.api.user.controller;

import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.common.bean.HandleBean;
import com.ics.cloud.common.model.Sys_role;
import com.ics.cloud.common.system.service.SysRoleService;
import com.ics.cloud.common.util.ListUtil;
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
@Api(description = "系统角色接口")
@RequestMapping("/role")
@Slf4j
public class SysRoleController extends IcsApiController {

    @Autowired
    private SysRoleService roleService;

    @ApiOperation("添加方法")
    @PostMapping(value = "/add")
    public BaseRetBean add(
            @RequestParam(value = "role", required = true) String role,
            @RequestParam(value = "role_name", required = true) String rolename,
            @RequestParam(value = "description", required = false) String description,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        Sys_role record = new Sys_role();
        BeanUtils.copyProperties(new HandleBean().post(super.getUserId(request)), record);
        record.setRole(role);
        record.setRole_name(rolename);
        record.setDescription(description);
        try {
            roleService.insertSelective(record);
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
        Sys_role record = new Sys_role();
        String userid = super.getUserId(request);
        BeanUtils.copyProperties(new HandleBean().delete(id, userid), record);
        try {
            roleService.updateByPrimaryKeySelective(record);
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
            @RequestParam(value = "role", required = true) String role,
            @RequestParam(value = "role_name", required = false) String rolename,
            @RequestParam(value = "description", required = false) String description,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");

        Sys_role record = new Sys_role();
        record.setId(id);
        record.setRole_name(rolename);
        record.setRole(role);
        record.setDescription(description);
        try {
            roleService.updateByPrimaryKeySelective(record);
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
            PageInfo pageInfo = roleService.queryAll(pageNum, pageSize, pageFlag);
            baseRetBean.renderRet(pageInfo);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }


    @ApiOperation("查询全部用户角色方法")
    @PostMapping(value = "/queryUserRoleByUserid")
    public BaseRetBean queryUserRoleByUserid(
            @RequestParam(value = "userid", required = true) String userid,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean();
        baseRetBean.setRet(1);
        baseRetBean.setMsg("success");
        try {
            List list = roleService.queryUserRoleByUserId(userid);
            if(ListUtil.is(list)){
                baseRetBean.setData(list);
            }
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("保存用户角色方法")
    @PostMapping(value = "/saveUserRoleByUserid")
    public BaseRetBean saveUserRoleByUserid(
            @RequestParam(value = "userid", required = true) String userid,
            @RequestParam(value = "roles", required = true) String roles,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean();
        baseRetBean.setRet(1);
        baseRetBean.setMsg("success");
        try {
            roleService.saveUserRoleByUserid(userid,roles);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

}
