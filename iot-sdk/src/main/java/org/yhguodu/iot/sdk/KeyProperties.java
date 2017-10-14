package org.yhguodu.iot.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yhguodu.iot.common.exception.ExceptionMeta;
import org.yhguodu.iot.common.exception.IotException;

import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/10/14.
 */
public class KeyProperties {
    private static final Logger logger = LoggerFactory.getLogger(KeyProperties.class);
    private String path;
    private Map<String,String> properties;

    public KeyProperties(String path) {
        this.path = path;
        properties = new HashMap<String,String>();
    }

    public void loadProperties() throws IotException {
        try {
            Properties p = new Properties();
            FileInputStream fin = new FileInputStream(path);
            p.load(fin);
            Enumeration<Object> keys = p.keys();
            while(keys.hasMoreElements()) {
                String key = (String)keys.nextElement();
                properties.put(key,p.getProperty(key));
            }
        }
        catch(FileNotFoundException e) {
            logger.error("FileNotFoundException",e);
            throw new IotException(ExceptionMeta.FileNotFound);
        }
        catch(IOException ie) {
            logger.error("IOException",ie);
            throw new IotException(ExceptionMeta.FileIOError);
        }
    }

    public void addProperty(String key,String value) {
        properties.put(key,value);
    }

    public String getProperty(String key,String defaultValue) {
        if(properties.containsKey(key))
            return properties.get(key);

        return defaultValue;
    }

    public int getIntProperty(String key,int defaultValue) {
        if(properties.containsKey(key)) {
            String value = properties.get(key);
            try {
                return Integer.parseInt(value);
            }
            catch(NumberFormatException e) {
                logger.info("Property {} Format Is Wrong {}",key, value);
                return defaultValue;
            }
        }

        return defaultValue;
    }


    public void saveToPropertiesFile() throws IotException {
        try {
            FileOutputStream fout = new FileOutputStream(path);
            Properties p = new Properties();
            for(String key: properties.keySet()) {
                p.put(key,properties.get(key));
            }
            p.store(fout,"application.propertis");
        }
        catch(FileNotFoundException e) {
            logger.error("FileNotFoundException",e);
            throw new IotException(ExceptionMeta.FileNotFound);
        }
        catch(IOException e) {
            logger.info("IOException",e);
            throw  new IotException(ExceptionMeta.FileIOError);
        }
    }
}
