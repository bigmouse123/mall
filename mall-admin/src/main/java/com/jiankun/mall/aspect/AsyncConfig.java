package com.jiankun.mall.aspect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author OfferKing
 * @version 1.0
 * @date 2025/3/20 17:18
 */
@Configuration
public class AsyncConfig {
    @Bean("logTaskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("log-async-");
        executor.initialize();
        return executor;
    }
}
