package com.ics.cloud.icsapi.api.user.service;

import cn.hutool.core.util.StrUtil;
import com.ics.cloud.common.bean.HandleBean;
import com.ics.cloud.common.bean.JWT;
import com.ics.cloud.common.config.SysConfig;
import com.ics.cloud.common.dao.Sys_userMapper;
import com.ics.cloud.common.model.Sys_user;
import com.ics.cloud.common.util.BPwdUtil;
import com.ics.cloud.common.util.HttpUtil;
import com.ics.cloud.common.util.RedisUtil;
import com.ics.cloud.icsapi.api.user.bean.UserLoginBean;
import com.ics.cloud.icsapi.feign.client.UaaFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@Slf4j
public class LoginUserService {

    @Autowired
    private Sys_userMapper userMapper;
    @Autowired
    private SysConfig sysConfig;
    @Autowired
    private UaaFeignService uaaFeignService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     */
    public UserLoginBean login(String username, String password,
                               HttpServletRequest request, HttpServletResponse response) {
        Sys_user user = userMapper.queryUserByName(username);
        if (user == null) {
            log.debug("用户：{} 登录查询失败", username);
            return null;
        }
        if (!BPwdUtil.matches(password, user.getPassword())) {
            log.debug("用户：{} 登录密码错误", username);
        }
        JWT jwt = uaaFeignService.getToken("ics-api","123456","password", username, password);
        if (jwt == null) {
            log.debug("用户：{} token 签发错误", username);
            return null;
        }
        //api用户bean
        UserLoginBean loginBean = new UserLoginBean();
        user.setPassword(null);
        loginBean.setUser(user);
        loginBean.setJwt(jwt);

        String token = StrUtil.uuid().replace("-", "");

        redisUtil.set(sysConfig.getTokenkeyPrefex() + "::" + token, loginBean, 24 * 60 * 60);

        //写session
        HttpSession session = request.getSession();
        session.setAttribute(sysConfig.getTokenkeyPrefex(), jwt.getAccess_token());
        //写cookie
        HttpUtil.addCookie(response, sysConfig.getTokenkeyPrefex(), jwt.getAccess_token());
        //写header
        response.setHeader(sysConfig.getTokenkeyPrefex(), jwt.getAccess_token());

        return loginBean;
    }

    public boolean register(String username, String password) {
        Sys_user user = userMapper.queryUserByName(username);
        //用户已存在
        if (user != null) {
            return false;
        }
        Sys_user adduser = new Sys_user();
        BeanUtils.copyProperties(new HandleBean().post(), adduser);
        adduser.setUsername(username);
        adduser.setPassword(new BCryptPasswordEncoder().encode(password));
        userMapper.insertSelective(adduser);
        return true;
    }
}
