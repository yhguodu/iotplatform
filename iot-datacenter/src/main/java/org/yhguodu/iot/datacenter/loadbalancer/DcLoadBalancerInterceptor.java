package org.yhguodu.iot.datacenter.loadbalancer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;

import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * Created by yhguodu on 2017/10/13.
 */
public class DcLoadBalancerInterceptor extends LoadBalancerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DcLoadBalancerInterceptor.class);
    private LoadBalancerClient loadBalancer;
    private LoadBalancerRequestFactory requestFactory;

    public DcLoadBalancerInterceptor(LoadBalancerClient loadBalancer, LoadBalancerRequestFactory requestFactory) {
        super(loadBalancer,requestFactory);
        this.loadBalancer = loadBalancer;
        this.requestFactory = requestFactory;
    }

    public DcLoadBalancerInterceptor(LoadBalancerClient loadBalancer) {
        this(loadBalancer, new LoadBalancerRequestFactory(loadBalancer));
    }

    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        logger.info("test DcLoadBalancerInterceptor");
        URI originalUri = request.getURI();
        String serviceName = originalUri.getHost();
        
        Assert.state(serviceName != null, "Request URI does not contain a valid hostname: " + originalUri);
        return (ClientHttpResponse)this.loadBalancer.execute(serviceName, this.requestFactory.createRequest(request, body, execution));
    }

}
