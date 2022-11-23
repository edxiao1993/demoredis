package org.kevin.config.switcher;

import org.kevin.config.RedisMyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Kevin.Zng
 * @date 2022/11/24 00:56
 */
@Service
public class HowSwitchRedisServer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * this function works!!!
     */
    public void switchSource() {
        RedisStandaloneConfiguration standaloneConfiguration =
                new RedisStandaloneConfiguration("localhost");
        standaloneConfiguration.setPassword("myRedis");
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfiguration,
                RedisMyConfig.jedisPoolConfig());
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
    }
}
