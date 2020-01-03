package com.ics.cloud.icsapi.common.controller;


import com.ics.cloud.common.config.SysConfig;
import com.ics.cloud.common.model.Sys_user;
import com.ics.cloud.common.util.HttpUtil;
import com.ics.cloud.common.util.RedisUtil;
import com.ics.cloud.icsapi.api.user.bean.UserLoginBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class IcsApiController {

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private SysConfig sysConfig;

    protected Sys_user getUser(HttpServletRequest request) {
        String token = null;
        //从header 中获取token
        token = request.getHeader(sysConfig.getLoginPrefix());
        //从cookie中 获取token
        if (token == null) {
            HttpUtil.getCookieValue(request, sysConfig.getLoginPrefix());
        }
        //从session中获取token
        if (token == null) {
            token = (String) request.getSession().getAttribute(sysConfig.getLoginPrefix());
        }
        UserLoginBean userLoginBean = (UserLoginBean) redisUtil.get(sysConfig.getLoginPrefix() + "::" + token);
        Sys_user user = new Sys_user();
        BeanUtils.copyProperties(userLoginBean.getUser(), user);
        return user;
    }

    protected String getUserId(HttpServletRequest request) {
        return this.getUser(request).getId();
    }
}
