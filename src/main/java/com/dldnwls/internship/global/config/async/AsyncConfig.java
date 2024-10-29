package com.dldnwls.internship.global.config.async;

import org.hibernate.boot.jaxb.hbm.internal.ExecuteUpdateResultCheckStyleConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Bean(name = "fileUploadTaskExecutor")
    public Executor fileUploadTaskExecutor(){

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10); //기본 스레드 개수
//        executor.setMaxPoolSize(20); //최대 스레드 개수
//        executor.setQueueCapacity(1000); //대기 큐 사이즈
        executor.setThreadNamePrefix("FileUpload-");
        executor.initialize();
        return executor;
    }
}
