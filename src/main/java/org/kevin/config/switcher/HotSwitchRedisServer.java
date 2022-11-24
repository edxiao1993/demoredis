package org.kevin.config.switcher;

import org.kevin.config.RedisMyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Kevin.Zng
 * @date 2022/11/24 00:56
 */
@Service
public class HotSwitchRedisServer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @Autowired
//    @Qualifier("clusterJedis")
//    private JedisConnectionFactory clusterFactory;

    @Autowired
    @Qualifier("standaloneJedis")
    private JedisConnectionFactory standaloneFactory;

    @Autowired
    private LettuceConnectionFactory lettuceFactory;

    /**
     * this function works!!!
     */
    public void switchSource(String target) {
        switch (target) {
            case "standalone":
                redisTemplate.setConnectionFactory(standaloneFactory);
                return;
//            case "cluster":
//                redisTemplate.setConnectionFactory(clusterFactory);
//                return;
            case "lettuce":
                redisTemplate.setConnectionFactory(lettuceFactory);
                return;
            default:
                System.out.println("wrong target!!!");
        }
    }
}
