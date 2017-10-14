package org.yhguodu.iot.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.exception.IotException;


/**
 * Created by Administrator on 2017/10/13.
 */
public class SdkApplication {

    private static final Logger logger = LoggerFactory.getLogger(SdkApplication.class);

    public static void main(String[] args) throws IotException {
        final KeyProperties keyProperties = new KeyProperties("application.properties");
        keyProperties.loadProperties();

        SdkController controller = new SdkController(keyProperties);
        controller.launch();

           //add shutdown hook
//        Runtime.getRuntime().addShutdownHook(() -> {
//            keyProperties.saveToPropertiesFile();
//        });
    }
}
