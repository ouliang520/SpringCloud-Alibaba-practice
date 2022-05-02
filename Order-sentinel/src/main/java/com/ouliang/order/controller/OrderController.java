package com.ouliang.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {


    @RequestMapping("/add")
    @SentinelResource(value = "add",blockHandler = "handlerException")
    public String add(){
        System.out.println("下单成功");
        return "成功 ";
    }

    public String handlerException(BlockException e) {
        return "failed" + e;
    }
}
