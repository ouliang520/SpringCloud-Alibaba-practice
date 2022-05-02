package com.ouliang.sentinelnew;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StarApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarApplication.class,args);
    }


    // 注解支持的配置Bean
    //@SentinelResource配置类
    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
