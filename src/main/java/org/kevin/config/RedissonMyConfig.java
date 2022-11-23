package org.kevin.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin.Zng
 * @date 2022/11/21 23:37
 */
@Configuration
public class RedissonMyConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://localhost:6379")
                .setPassword("myRedis");
        return Redisson.create(config);
    }
}
