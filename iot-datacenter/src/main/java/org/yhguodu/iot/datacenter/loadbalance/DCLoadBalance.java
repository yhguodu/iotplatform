package org.yhguodu.iot.datacenter.loadbalance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;

/**
 * Created by Administrator on 2017/10/12.
 */
public class DCLoadBalance implements ILoadBalancer{

    public void addServers(List<Server> list) {

    }

    public Server chooseServer(Object o) {
        return null;
    }

    public void markServerDown(Server server) {

    }

    public List<Server> getServerList(boolean b) {
        return null;
    }

    public List<Server> getReachableServers() {
        return null;
    }

    public List<Server> getAllServers() {
        return null;
    }
}
