package org.kevin.controller.switcher;

import org.kevin.config.switcher.HotSwitchRedisServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private HotSwitchRedisServer hotSwitchRedisServer;

    @GetMapping("switch1")
    public String switch1(@RequestParam("mode") String mode) {
        System.out.println(redisTemplate.opsForValue().get("nov19"));
        hotSwitchRedisServer.switchSource(mode);
        String val = redisTemplate.opsForValue().get("myTime");
        System.out.println("val = " + val);
        return "done";
    }
}
