package com.ouliang.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;


    @RequestMapping("/add")
    public String add(){
        System.out.println("下单成功");
        String msg= restTemplate.getForObject("http://stock-service/stock/reduct",String.class);
        return "成功 "+msg;
    }

    @RequestMapping("/header")
    public String master(HttpServletRequest request) {
        String name = request.getHeader("name");
        System.out.println(name);
        return name;
    }
}
