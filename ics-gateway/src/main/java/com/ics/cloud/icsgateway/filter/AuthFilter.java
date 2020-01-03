package com.ics.cloud.icsgateway.filter;

import com.alibaba.fastjson.JSON;
import com.ics.cloud.icsgateway.bean.BaseRetBean;
import com.ics.cloud.icsgateway.bean.UserLoginBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String token = exchange.getRequest().getQueryParams().getFirst("token");

        ServerHttpResponse response = exchange.getResponse();
        BaseRetBean baseRetBean = new BaseRetBean();

        if (token == null || token.isEmpty()) {
            // 错误信息
            baseRetBean.setRet(6);
            baseRetBean.setMsg("token错误");
            try {
                // 返回错误信息
                response.setStatusCode(HttpStatus.UNAUTHORIZED); //设置浏览器返回的回执码
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(baseRetBean).getBytes());
                return response.writeWith(Mono.just(buffer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!redisTemplate.hasKey(token)) {
            baseRetBean.setRet(2);
            baseRetBean.setMsg("会话过期，请重新登录");
            // 返回错误信息
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(baseRetBean).getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        UserLoginBean userLoginBean = (UserLoginBean) redisTemplate.opsForValue().get(token);
        if (userLoginBean == null) {
            baseRetBean.setRet(6);
            baseRetBean.setMsg("非法用户");
            // 返回错误信息
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONString(baseRetBean).getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        log.debug("loginBean:{}",userLoginBean.getUser());
        //向headers中放文件，记得build
        ServerHttpRequest host = exchange.getRequest().mutate()
                .header("Authorization", "Bearer "+userLoginBean.getJwt().getAccess_token()).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(host).build();
        return chain.filter(build);
    }

    /**
     * 设置过滤器的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
