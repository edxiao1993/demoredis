package org.kevin.controller.switcher;

import org.kevin.config.switcher.HowSwitchRedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kevin.Zng
 * @date 2022/11/24 01:10
 */
@RestController
public class SwitchController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private HowSwitchRedisServer howSwitchRedisServer;

    @GetMapping("switch1")
    public String switch1() {
        redisTemplate.opsForValue().set("myTime", "Nov24:0111");
        howSwitchRedisServer.switchSource();
        String val = redisTemplate.opsForValue().get("myTime");
        System.out.println("val = " + val);
        return "done";
    }
}
