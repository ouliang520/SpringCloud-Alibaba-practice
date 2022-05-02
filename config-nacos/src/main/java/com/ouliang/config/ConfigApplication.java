package com.ouliang.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ConfigApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext= SpringApplication.run(ConfigApplication.class,args);
        String userName=configurableApplicationContext.getEnvironment().getProperty("user.name");
        String userAge=configurableApplicationContext.getEnvironment().getProperty("user.age");
        String config=configurableApplicationContext.getEnvironment().getProperty("user.config");
        System.out.print(userName+" "+userAge +" ");
        if (config!=null){
            System.out.println(config);
        }
        //nacos客户端会每十毫秒去注册中心进行判断,根据MD5进行判断
    }
}
