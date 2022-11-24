package org.kevin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Kevin.Zng
 * @date 2022/11/19 22:02
 */
@Configuration
public class RedisMyConfig {

    @Bean(name = "standaloneJedis")
    @Primary
    public JedisConnectionFactory standalone() {
        RedisStandaloneConfiguration aloneConfiguration =
                new RedisStandaloneConfiguration("localhost", 6379);
        aloneConfiguration.setPassword("myRedis");
        return new JedisConnectionFactory(aloneConfiguration, RedisMyConfig.jedisPoolConfig());
    }

    public static JedisClientConfiguration jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(1);
        jedisPoolConfig.setMaxTotal(2);
        jedisPoolConfig.setMinIdle(0);

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolConfigBuilder =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder)JedisClientConfiguration.builder();
        jedisPoolConfigBuilder.poolConfig(jedisPoolConfig);
        return jedisPoolConfigBuilder.build();
    }

    @Bean
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
