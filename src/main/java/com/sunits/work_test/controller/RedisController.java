package com.sunits.work_test.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @projectName: work_test
 * @creator: xieyunsheng
 * @since: 2022/3/11--15:11
 * @description:
 */
@RequestMapping("redis")
@RestController
public class RedisController {
    @Resource
    private RedisTemplate stringRedisTemplate;

    @PostMapping
    public void testRedis(){
        stringRedisTemplate.boundSetOps("a").add("121","111");
    }
}
