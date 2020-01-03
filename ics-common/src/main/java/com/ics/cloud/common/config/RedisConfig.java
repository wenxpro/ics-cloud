package com.ics.cloud.common.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ics.cloud.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class RedisConfig  {


    //lettuce客户端连接工厂
    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * 设置RedisTemplate的序列化方式
     */
    public void setSerializer(RedisTemplate<String, Object> template) {

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);

        // value序列化方式采用jackson
        template.setValueSerializer(stringRedisSerializer);

        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);

        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(stringRedisSerializer);

        template.afterPropertiesSet();
    }


    /**
     * 配置redisTemplate 注入方式使用@Resource(name="") 方式注入
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public RedisUtil defaultRedisTemplate() {
        RedisTemplate <String,Object> template = new RedisTemplate<String,Object>();
        template.setConnectionFactory(lettuceConnectionFactory);
        setSerializer(template);
        template.afterPropertiesSet();
        return new RedisUtil(template);
    }
}
