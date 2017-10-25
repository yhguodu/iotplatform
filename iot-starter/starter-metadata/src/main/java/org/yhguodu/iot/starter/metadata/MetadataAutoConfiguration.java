package org.yhguodu.iot.starter.metadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by yhguodu on 2017/10/24.
 */

@Configuration
@EnableConfigurationProperties(MetaStarterProperties.class)
public class MetadataAutoConfiguration {

    @Autowired
    private MetaStarterProperties metaProperties;


}
