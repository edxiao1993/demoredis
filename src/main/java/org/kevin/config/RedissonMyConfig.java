package org.kevin.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.connection.ConnectionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * @author Kevin.Zng
 * @date 2022/11/21 23:37
 */
@Configuration
@Slf4j
public class RedissonMyConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setLockWatchdogTimeout(30000)
                .setConnectionListener(new ConnectionListener() {
                    @Override
                    public void onConnect(InetSocketAddress inetSocketAddress) {
                        log.info("connect success!!!");
                    }

                    @Override
                    public void onDisconnect(InetSocketAddress inetSocketAddress) {
                        log.info("disconnect~");
                    }
                })
                .useSingleServer()
                .setAddress("redis://localhost:6379")
                .setPassword("myRedis");
        return Redisson.create(config);
    }
}
