package org.yhguodu.iot.datacenter.loadbalancer;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.netflix.ribbon.*;

import java.io.IOException;

/**
 * Created by yhguodu on 2017/10/13.
 */
public class DcRibbonLoadBalancerClient extends RibbonLoadBalancerClient{
    private SpringClientFactory clientFactory;

    public DcRibbonLoadBalancerClient(SpringClientFactory factory) {
        super(factory);
        this.clientFactory = factory;
    }

    @Override
    public <T> T execute(String serviceId, LoadBalancerRequest<T> request) throws IOException {
        ILoadBalancer loadBalancer = this.getLoadBalancer(serviceId);
        Server server = this.getServer(loadBalancer);
        if(server == null) {
            throw new IllegalStateException("No instances available for " + serviceId);
        } else {
            RibbonLoadBalancerClient.RibbonServer ribbonServer = new RibbonLoadBalancerClient.RibbonServer(serviceId, server, this.isSecure(server, serviceId), this.serverIntrospector(serviceId).getMetadata(server));
            return this.execute(serviceId, ribbonServer, request);
        }
    }

    protected Server getServer(ILoadBalancer loadBalancer) {
        return loadBalancer == null?null:loadBalancer.chooseServer("default");
    }

    private boolean isSecure(Server server, String serviceId) {
        IClientConfig config = this.clientFactory.getClientConfig(serviceId);
        ServerIntrospector serverIntrospector = this.serverIntrospector(serviceId);
        return RibbonUtils.isSecure(config, serverIntrospector, server);
    }

    private ServerIntrospector serverIntrospector(String serviceId) {
        Object serverIntrospector = (ServerIntrospector)this.clientFactory.getInstance(serviceId, ServerIntrospector.class);
        if(serverIntrospector == null) {
            serverIntrospector = new DefaultServerIntrospector();
        }

        return (ServerIntrospector)serverIntrospector;
    }
}
