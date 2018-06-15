package com.qding.api.util;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by jinhaishan on 17/8/4.
 */
public class PropertyUtil {

    private static final Logger logger = Logger.getLogger(PropertyUtil.class);

    private static final String DEFAULT_FILE = "config.properties";

    private static final PropertyUtil instance = new PropertyUtil();

    private static PropertiesConfiguration config = null;

    public static PropertyUtil getInstance() {
        return instance;
    }

    static {
        Configurations configs = new Configurations();
        File propertiesFile = new File(PropertyUtil.class.getResource("/") + DEFAULT_FILE);
        try {
            config = configs.properties(propertiesFile);
        } catch (ConfigurationException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String getString(String key) {
        return config.getString(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyUtil.getInstance().getString("lable.shareurl"));
    }
}
