package com.cks.ciftcikredisistemi.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class TaskExecutorConfig {
    @Value("${custom.task-executor.core-size}")
    Integer coreSize;

    @Value("${custom.task-executor.max-size}")
    Integer maxSize;

    @Value("${custom.task-executor.queue-capacity}")
    Integer queueCapacity;

    @Value("${custom.task-executor.thread-prefix}")
    String prefix;

    @Bean
    public TaskExecutor customTaskExecutor() {
        val executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(coreSize);
        executor.setMaxPoolSize(maxSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(prefix);

        return executor;
    }
}