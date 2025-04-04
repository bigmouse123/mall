package com.jiankun.mall.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/19 16:15
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + host + ":6379");
//        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }
}