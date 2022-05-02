package com.ouliang.order.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//添加feign接口和方法
/*
name 指定调用rest接口对应的服务名 (spring.applicatio.name)
path 指定调用rest接口所在的StockController指定的@RequestMapping
 */
@FeignClient(name = "stock-service", path = "/stock")
public interface StockFeignService {
    //声明需要对应的rest接口对应的方法
    @RequestMapping("/reduct")
    public String reduct();


}
