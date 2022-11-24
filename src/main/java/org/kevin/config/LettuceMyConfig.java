package org.kevin.config;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin.Zng
 * @date 2022/11/25 01:01
 */
@Configuration
public class LettuceMyConfig {

    @Bean
    public LettuceConnectionFactory lettuceConnectionFactory() {
        GenericObjectPoolConfig poolConfig = DefaultPoolConfiguration.genericObjectPoolConfig();

        /*List<RedisNode> redisNodeList = new ArrayList<>();
        redisNodeList.add(new RedisNode("localhost", 6379));
        // standalone or cluster is configured here~
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration();
        clusterConfiguration.setClusterNodes(redisNodeList);
        clusterConfiguration.setPassword("myRedis");
        clusterConfiguration.setMaxRedirects(5);*/

        RedisStandaloneConfiguration aloneConfiguration =
                new RedisStandaloneConfiguration("47.119.128.80", 6379);
        aloneConfiguration.setPassword("myRedis");

        // cluster 的配置完全看不懂
        ClusterTopologyRefreshOptions topologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh(Duration.ofMinutes(5L))
                .enableAllAdaptiveRefreshTriggers()
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .pingBeforeActivateConnection(true)
                .autoReconnect(true)
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.DEFAULT)
//                .topologyRefreshOptions(topologyRefreshOptions)
                .build();

        LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(5L))
                .poolConfig(poolConfig)
                .clientOptions(clusterClientOptions)
                .build();
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(aloneConfiguration, lettuceClientConfiguration);
        return lettuceConnectionFactory;
    }
}
