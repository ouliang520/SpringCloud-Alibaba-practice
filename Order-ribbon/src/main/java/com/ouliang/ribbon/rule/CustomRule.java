package com.ouliang.ribbon.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class CustomRule extends AbstractLoadBalancerRule {

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object o) {
        //获得当前球球的服务的实例
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        List<Server> reachableServers = loadBalancer.getReachableServers();
        int random= ThreadLocalRandom.current().nextInt(reachableServers.size());
        Server server=reachableServers.get(random);
        if (server.isAlive()){//server存活的时候返回
            return server;
        }else {
            //否则重新获取(不写了)
        }
        return null;
    }
}
