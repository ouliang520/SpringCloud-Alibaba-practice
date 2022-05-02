package com.ouliang.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//ribbon配置类
@Configuration
public class RibbonRandomRuleConfig {
    //方法名一定要叫IRule
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}
