package com.sunits.work_test.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/3/11--15:19
 * @description:
 */
@Configuration
@EnableConfigurationProperties
public class RedisConfig9001 {

    @Bean
    public RedisTemplate stringRedisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setHashValueSerializer(stringSerializer);
        return redisTemplate;
    }
}
