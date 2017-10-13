package org.yhguodu.iot.datacenter.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;


/**
 * Created by yhguodu on 2017/10/13.
 */
public class ConsistencyHashRule extends AbstractLoadBalancerRule {

    private static final Logger logger = LoggerFactory.getLogger(ConsistencyHashRule.class);

    private TreeMap<Long,Server> serverTreeMap = new TreeMap<Long, Server>();
    private int numberOfReplicas = 4;

    public ConsistencyHashRule() {}

    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        super.setLoadBalancer(lb);
        List<Server> servers = lb.getReachableServers();
        for(Server server:servers)
            addServer(server);
    }

    public void addServer(Server server) {
        for(int i =0;i<numberOfReplicas;i++)
            serverTreeMap.put(HashFunction.hash(server.getId()+i),server);
    }

    public void removeServer(Server server) {
        for(int i = 0; i<numberOfReplicas; i++)
            serverTreeMap.remove(HashFunction.hash(server.getId()+i));
    }

    public Server getServer(Object k) {
        if(serverTreeMap.isEmpty())
            return null;

        long key = HashFunction.hash(k.toString());
        if(!serverTreeMap.containsKey(key)) {
            SortedMap<Long, Server> tailMap = serverTreeMap.tailMap(key);
            key = tailMap.isEmpty() ? serverTreeMap.firstKey() : tailMap.firstKey();
        }

        return serverTreeMap.get(key);
    }

    public Server choose(ILoadBalancer lb, Object o) {
        logger.info("choose at {}",System.currentTimeMillis());
        List<Server> servers = lb.getAllServers();

        logger.info("servers size {}",servers.size());
        for(Server server: servers)
            logger.info("server name {}",server.getHost());
        return servers.get(0);
    }

    public Server choose(Object o) {
        System.out.println("choose server");
        return this.choose(this.getLoadBalancer(),o);
    }


    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }
}
