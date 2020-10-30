package com.example.threadpool.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.*;

/**
 *功能描述
 * @author Barret
 * @date 10/19/2020
 * @return
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutorConfig {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorConfig.class);

    @Bean
    public ThreadPoolExecutor asyncServiceExecutor() {
        logger.info("Init ThreadPoolExecutor");
//        ExecutorService executorService = Executors.newCachedThreadPool();

        return null;
    }
}
