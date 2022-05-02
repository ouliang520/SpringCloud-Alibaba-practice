package order.feign;


import order.feign.fallback.StcokFeignServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//添加feign接口和方法
/*
name 指定调用rest接口对应的服务名 (spring.applicatio.name)
path 指定调用rest接口所在的StockController指定的@RequestMapping
 */
@FeignClient(name = "stock-service", path = "/stock",fallback = StcokFeignServiceFallBack.class)
public interface StockFeignService {
    //声明需要对应的rest接口对应的方法
    @RequestMapping("/reduct2")
    public String reduct();


}
