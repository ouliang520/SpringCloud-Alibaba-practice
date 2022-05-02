package com.ouliang.sentinelnew.controller;


import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.ouliang.sentinelnew.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@RestController
public class HelloController {
    private static final String RESOURCE_NAME="hello";
    private static final String USER_RESOURCE_NAME="users";
    private static final String DEGRADE_RESOURCE_NAME="degrade";
    @RequestMapping("/hello")
    public String hello(){
        Entry entry=null;
        try {
            //sentinel针对资源进行限制的
            entry = SphU.entry(RESOURCE_NAME);
            //被保护的业务逻辑
            String str="hello world";
            log.info("====="+str+"=====");
            return str;
        }catch (BlockException ex){
            //资源访问诅咒,被限流或者被降级
            //进行相应的处理操作
            log.info("block!");
            return "被流控了!";
        }catch (Exception ex){
            //若需要配置降级规则,则需要通过这种方式记录业务异常
            Tracer.traceEntry(ex,entry);
        }finally {
            if (entry!=null){
                entry.exit();
            }
        }
        return null;
    }
    @PostConstruct //Spring 的初始化方法
    private static void initFlowRules(){
        //流控规则
        List<FlowRule> rules=new ArrayList<>();

        //流控
        FlowRule rule =new FlowRule();
        //设置需要保护的资源
        rule.setResource(RESOURCE_NAME);
        //设置流控规则QPS
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源阈值
        //Set limit QPS to 20
        rule.setCount(1);//一秒内只能访问一次
        rules.add(rule);


        //流控
        FlowRule rule2 =new FlowRule();
        //设置需要保护的资源
        rule2.setResource(USER_RESOURCE_NAME);
        //设置流控规则QPS
        rule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //设置受保护的资源阈值
        //Set limit QPS to 20
        rule2.setCount(1);//一秒内只能访问一次
        rules.add(rule2);

        FlowRuleManager.loadRules(rules); //加载流控规则


        //降级规则
        List<DegradeRule> degradeRules=new ArrayList<>();
         DegradeRule deprecated=new DegradeRule();
         deprecated.setResource(DEGRADE_RESOURCE_NAME);
         //设置规则侧率
         deprecated.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
         //熔断触发异常数
         deprecated.setCount(2);
         //熔断触发最小请求数
        deprecated.setMinRequestAmount(2);
         //统计时长 多少毫米内:执行2次,执行2次异常,就会熔断
         deprecated.setStatIntervalMs(60*1000);
         //熔断持续时长
         deprecated.setTimeWindow(10);
         degradeRules.add(deprecated);

        DegradeRuleManager.loadRules(degradeRules);

    }
    //添加了@SentinelResource 依赖
    /**
     * value 流控的资源
     * blockHandler 降级方法 必须定义在同一个类中 一定要public
     * fallback接口异常跳转
     */
    @RequestMapping("/user")
    @SentinelResource(value = USER_RESOURCE_NAME,fallback = "back",blockHandler = "blockHandlerForGetUser")
    public User GetUser(String id){

        return new User("ouLiang");
    }
    public static User back(String id,Throwable e){
        e.printStackTrace();
        return new User("异常");
    }

    public static User blockHandlerForGetUser(String id, BlockException ex){
        ex.printStackTrace();
        return new User("流控");
    }

    @RequestMapping("degrade")
    @SentinelResource(value = DEGRADE_RESOURCE_NAME,entryType = EntryType.IN,blockHandler = "degradeJ")
    public String degrade(){
        throw new RuntimeException("异常");
    }

    public static String degradeJ(BlockException e){
        return "熔断降级";
    }


}
