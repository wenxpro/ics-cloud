package com.ics.cloud.icsapi.api.user.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.ics.cloud.common.base.BasePageRetBean;
import com.ics.cloud.common.base.BaseRetBean;
import com.ics.cloud.common.bean.HandleBean;
import com.ics.cloud.common.model.Sys_user;
import com.ics.cloud.common.system.bean.SysUserBean;
import com.ics.cloud.common.system.bean.UserInfoBean;
import com.ics.cloud.common.system.service.SysUserService;
import com.ics.cloud.common.util.BPwdUtil;
import com.ics.cloud.icsapi.api.user.bean.UserLoginBean;
import com.ics.cloud.icsapi.api.user.service.LoginUserService;
import com.ics.cloud.icsapi.common.controller.IcsApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(description = "系统用户接口")
@RequestMapping(value = "/user")
@Slf4j
public class SysUserController extends IcsApiController {

    @Autowired
    private SysUserService userService;

    @Autowired
    private LoginUserService loginUserService;

    @ApiOperation("注册方法")
    @PostMapping(value = "/register")
    public BaseRetBean register(@RequestParam(value = "username", required = true) String username,
                                @RequestParam(value = "password", required = true) String password) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "注册成功");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            baseRetBean.setRet(0);
            baseRetBean.setMsg("用户或密码不能为空！");
            return baseRetBean;
        }
        try {
            boolean bool = loginUserService.register(username, password);
            if (!bool) {
                baseRetBean.setRet(0);
                baseRetBean.setMsg("用户名已存在");
            }
        } catch (Exception e) {
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("注册失败");
            log.debug("用户：{} 注册失败，失败原因：{}", username, e);
        }
        return baseRetBean;
    }

    @ApiOperation("登录方法")
    @PostMapping(value = "/login")
    public BaseRetBean login(@RequestParam(value = "username", required = true) String username,
                             @RequestParam(value = "password", required = true) String password,
                             HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "登录成功");
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            baseRetBean.setRet(0);
            baseRetBean.setMsg("用户或密码不能为空！");
            return baseRetBean;
        }
        try {
            UserLoginBean userLogin = loginUserService.login(username, password, request, response);
            if (userLogin == null) {
                baseRetBean.setRet(0);
                baseRetBean.setMsg("登录失败");
            }
            baseRetBean.setData(userLogin);
        } catch (Exception e) {
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("登录失败");
            log.debug("用户：{} 登录失败，失败原因：{}", username, e);
        }
        return baseRetBean;
    }

    @GetMapping("/getUserInfo")
    public BaseRetBean getUserInfo(
            @RequestParam(value = "userid", required = false) String userid,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
        UserInfoBean userInfoBean = new UserInfoBean();
        Sys_user user = super.getUser(request);
        if (user == null) {
            try {
                user = userService.selectByPrimaryKey(userid);
            } catch (Exception e) {
                log.error("error:{}", e);
                baseRetBean.setRet(-1);
                baseRetBean.setMsg("error");
            }
        }
        BeanUtils.copyProperties(user, userInfoBean);
        baseRetBean.setData(userInfoBean);
        return baseRetBean;
    }

    @ApiOperation("添加方法")
    @PostMapping(value = "/add")
    public BaseRetBean add(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "mobile", required = false) String mobile,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");
//        if(!password.equals(repassword)){
//            baseRetBean.setRet(0);
//            baseRetBean.setMsg("两次密码不一致");
//        }
        Sys_user user = new Sys_user();
        BeanUtils.copyProperties(new HandleBean().post(super.getUserId(request)), user);
        user.setUsername(username);
        user.setPassword(BPwdUtil.BCryptPassword("admin"));
        user.setNickname(nickname);
        user.setMobile(mobile);
        user.setEmail(email);
        try {
            boolean bool = userService.add(user);
            if (!bool) {
                baseRetBean.setRet(0);
                baseRetBean.setMsg("用户名已存在");
            }
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
        Sys_user user = new Sys_user();
        String userid = super.getUserId(request);
        BeanUtils.copyProperties(new HandleBean().delete(id, userid), user);
        try {
            userService.updateByPrimaryKeySelective(user);
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
            @RequestParam(value = "nickname", required = false) String nickname,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "mobile", required = false) String mobile,
            HttpServletRequest request, HttpServletResponse response) {
        BaseRetBean baseRetBean = new BaseRetBean(1, "success");

        Sys_user user = new Sys_user();
        user.setId(id);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setMobile(mobile);
        try {
            userService.updateByPrimaryKeySelective(user);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("查询全部")
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
            PageInfo pageInfo = userService.queryAll(pageNum, pageSize, pageFlag);
            baseRetBean.renderRet(pageInfo);
        } catch (Exception e) {
            log.error("error:{}", e);
            baseRetBean.setRet(-1);
            baseRetBean.setMsg("error");
        }
        return baseRetBean;
    }

    @ApiOperation("导出excel")
    @GetMapping(value = "/export")
    public void export(HttpServletResponse response) {
        try {
            PageInfo<SysUserBean> pageInfo = userService.queryAll(0, 0, false);
            // 创建excel工作簿
            Workbook workbook = new XSSFWorkbook();
            // 创建第一个sheet（页），并命名
            Sheet sheet = workbook.createSheet("用户信息");
            //设置首行标题标题
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("id");
            row.createCell(1).setCellValue("昵称");
            row.createCell(2).setCellValue("用户名");
            row.createCell(3).setCellValue("手机号");
            //新增数据行，并且设置单元格数据
            int rowNum = 1;

            for(SysUserBean sysUserBean : pageInfo.getList())
            {
                row = sheet.createRow(rowNum);
                row.createCell(0).setCellValue(sysUserBean.getId());
                row.createCell(1).setCellValue(sysUserBean.getNickname());
                row.createCell(2).setCellValue(sysUserBean.getUsername());
                row.createCell(3).setCellValue(sysUserBean.getMobile());
                rowNum++;
            }
            response.setHeader("Content-type", "application/vnd.ms-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + "用户信息" + ".xlsx");
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (Exception e) {
            log.error("error:{}", e);
        }
    }
}

