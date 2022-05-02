package com.ouliang.sentinelnew.pojo;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.context.annotation.Bean;

public class User {
    public  String name ;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
