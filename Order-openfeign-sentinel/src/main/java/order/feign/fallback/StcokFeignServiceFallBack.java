package order.feign.fallback;

import order.feign.StockFeignService;
import org.springframework.stereotype.Component;

@Component
public class StcokFeignServiceFallBack implements StockFeignService {
    @Override
    public String reduct() {
        System.out.println("降级了");
        return "降级啦";
    }
}
