package org.kevin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kevin.Zng
 * @date 2022/11/19 21:57
 */
@RestController
public class Index {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("index")
    public String index() {
        return "hello world";
    }

    @RequestMapping("input")
    public String input(@RequestParam("key") String key) {
        redisTemplate.opsForValue().set("nov19", key);
        return "done";
    }

    @RequestMapping("output")
    public String output() {
        String name = redisTemplate.opsForValue().get("nov19");
        return name;
    }
}
