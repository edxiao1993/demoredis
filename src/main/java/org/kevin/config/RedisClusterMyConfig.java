package org.kevin.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Kevin.Zng
 * @date 2022/11/19 22:55
 */
//@Configuration
public class RedisClusterMyConfig {
    @Bean
    public RedisClusterConfiguration clusterConfiguration() {
        Set<RedisNode> redisNodes = new HashSet<>();
        RedisNode n1 = new RedisNode("localhost", 6379);
        redisNodes.add(n1);

        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        redisClusterConfiguration.setClusterNodes(redisNodes);
        redisClusterConfiguration.setPassword("myRedis");
        return redisClusterConfiguration;
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(8);
        jedisPoolConfig.setMaxTotal(8);
        jedisPoolConfig.setMinIdle(0);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    @Bean("jedisCluster")
    public JedisConnectionFactory jedisConnectionFactory(RedisClusterConfiguration redisClusterConfiguration) {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig());
        return jedisConnectionFactory;
    }

    @Bean
    @Qualifier("jedisCluster")
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory connectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
        redisTemplate.setConnectionFactory(connectionFactory);

        return redisTemplate;
    }
}
