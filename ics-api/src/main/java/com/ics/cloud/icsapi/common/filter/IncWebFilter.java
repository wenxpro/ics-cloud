package com.ics.cloud.icsapi.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebFilter(filterName="IncWebFilter",urlPatterns="/*")
@Slf4j
public class IncWebFilter implements Filter {

    /**
     * 需要排除的页面
     */
    private String[] excludedPageArray = {"/user/login", "/user/register"};


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        //用于的使 Browser 不缓存页面的过滤器
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", -1);
        Map map = req.getParameterMap();
        log.debug(req.getRequestURI());

//        boolean isExcludedPage = false;
//        for (String page : excludedPageArray) {//判断是否在过滤url之外
//            if (((HttpServletRequest) request).getServletPath().equals(page)) {
//                isExcludedPage = true;
//                break;
//            }
//        }
//        if (isExcludedPage) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        //从header 中获取token
//        String token = req.getHeader(sysConfig.getLoginPrefix());
//        //从cookie中 获取token
//        if (token == null) {
//            HttpUtil.getCookieValue(req, sysConfig.getLoginPrefix());
//        }
//        //从session中获取token
//        if (token == null) {
//            token = (String) req.getSession().getAttribute(sysConfig.getLoginPrefix());
//        }
//        BaseRetBean baseRetBean = new BaseRetBean();
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("application/json; charset=utf-8");
//
//        if (token == null) {//非法的请求
//            baseRetBean.setRet(5);
//            baseRetBean.setMsg("login_token错误");
//            resp.getWriter().write(JSON.toJSONString(baseRetBean));
//            resp.flushBuffer();
//            return;
//        }
//        String login_token = sysConfig.getLoginPrefix() + "::" + token;
//        if (!redisUtil.hasKey(login_token)) {
//            baseRetBean.setRet(2);
//            baseRetBean.setMsg("会话过期，请重新登录");
//            resp.getWriter().write(JSON.toJSONString(baseRetBean));
//            resp.flushBuffer();
//            return;
//        }
//        UserLoginBean userLoginBean = (UserLoginBean) redisUtil.get(login_token);
//        if (userLoginBean == null) {
//            baseRetBean.setRet(6);
//            baseRetBean.setMsg("非法用户");
//            resp.getWriter().write(JSON.toJSONString(baseRetBean));
//            resp.flushBuffer();
//            return;
//        }
//        log.debug("用户：{} 发起请求", userLoginBean.getUser().getUsername());
        chain.doFilter(req, resp);

    }

    @Override
    public void destroy() {

    }

}
