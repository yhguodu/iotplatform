package org.yhguodu.iot.starter.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yhguodu.iot.common.metadata.MetaService;
import org.yhguodu.iot.starter.metadata.netty.MetadataRpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by yhguodu on 2017/10/24.
 */

@Configuration
@EnableConfigurationProperties(MetaStarterProperties.class)
public class MetadataAutoConfiguration {

    @Bean
    public MetadataRpcClient metadataRpcClient(MetaStarterProperties metaProperties) {
        return new MetadataRpcClient(metaProperties);
    }

    @Bean
    public InvocationHandler handler(MetadataRpcClient client) {
        return new MetaInvocationHandler(client);
    }

    @Bean
    public MetaService metaService(InvocationHandler handler) {
        return (MetaService) Proxy.newProxyInstance(handler.getClass().getClassLoader(),new Class[]{MetaService.class},handler);
    }
}
