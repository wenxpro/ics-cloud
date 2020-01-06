package com.ics.cloud.icsgateway.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ics.cloud.icsgateway.bean.UserLoginBean;
import com.ics.cloud.icsgateway.config.SysConfig;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StreamUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;


@Component
@Slf4j
public class AuthConvertFilter implements GlobalFilter, Ordered {

    @Autowired
    private SysConfig sysConfig;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().toString();
        String path = request.getURI().getPath();
        HttpMethod method = exchange.getRequest().getMethod();
        StringBuilder builder = new StringBuilder();

        if(HttpMethod.GET.equals(method)){
            builder.append(JSON.toJSONString(request.getQueryParams()));
        }else{
            Flux<DataBuffer> body = request.getBody();
            ServerHttpRequest serverHttpRequest = request.mutate().uri(request.getURI()).build();
            body.subscribe(dataBuffer -> {
                InputStream inputStream = dataBuffer.asInputStream();
                try {
                    builder.append(StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8));
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            });
            // 重写请求体,因为请求体数据只能被消费一次
            request = new ServerHttpRequestDecorator(serverHttpRequest) {
                @Override
                public Flux<DataBuffer> getBody() {
                    return Flux.just(new NettyDataBufferFactory(ByteBufAllocator.DEFAULT)
                            .wrap(builder.toString().getBytes(StandardCharsets.UTF_8)));
                }
            };
        }
        //排除url
        log.debug("request uri : {}",  uri);
        String[] urlArr = sysConfig.getUrlExclude().split(",");
        boolean bool = false;
        AntPathMatcher matcher = new AntPathMatcher();
        for (int i = 0; i < urlArr.length; i++) {
           if(matcher.match(urlArr[i],path)){
               bool = true;
               break;
           }
        }
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        if(bool){
            return chain.filter(exchange.mutate().request(request).build()).then(Mono.fromRunnable(() -> {
                ServerHttpResponse response = exchange.getResponse();
                HttpStatus statusCode = response.getStatusCode();
                log.debug("请求路径:{},远程IP地址:{},请求方法:{},请求参数:{},目标URI:{},响应码:{}",
                        path, remoteAddress, method, builder.toString(), uri, statusCode);
            }));
        }

        //携带token url
        String token = request.getQueryParams().getFirst("token");
        ServerHttpResponse response = exchange.getResponse();
        JSONObject result = new JSONObject();

        if (token == null || token.isEmpty()) {
            // 错误信息
            result.put("ret",6);
            result.put("msg","token错误");
            try {
                // 返回错误信息
                response.setStatusCode(HttpStatus.UNAUTHORIZED); //设置浏览器返回的回执码
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes());
                return response.writeWith(Mono.just(buffer));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!redisTemplate.hasKey(token)) {
            result.put("ret",2);
            result.put("msg","会话过期，请重新登录");
            // 返回错误信息
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        UserLoginBean userLoginBean = (UserLoginBean) redisTemplate.opsForValue().get(token);
        if (userLoginBean == null) {
            result.put("ret",6);
            result.put("msg","非法用户");
            // 返回错误信息
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            DataBuffer buffer = response.bufferFactory().wrap(result.toString().getBytes());
            return response.writeWith(Mono.just(buffer));
        }


        log.debug("loginBean:{}",userLoginBean.getUser());
        //向headers中放文件，记得build
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("Authorization", "Bearer "+userLoginBean.getJwt().getAccess_token());
        };
        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        //将现在的request 变成 change对象
        ServerWebExchange build = exchange.mutate().request(serverHttpRequest).build();
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
