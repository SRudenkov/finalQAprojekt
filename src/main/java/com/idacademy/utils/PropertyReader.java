package com.idacademy.utils;

import com.idacademy.Enums.Capability;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    private static final Logger LOGGER = LogManager.getLogger(DriverFactory.class);
    public static String getProperty(PropertyFile propertyfile, Capability capability) {
        Properties properties = new Properties();
        FileReader fileReader;
        try {
            fileReader = new FileReader("src/main/java/resources/" + propertyfile.getPathToFile());
            properties.load(fileReader);
        } catch (IOException e) {
            LOGGER.info(" Properties are not loaded, use default value... chrome ");
        }
        for (Object key : properties.keySet()) {
            String systemValue = System.getProperty((String) key);
            if (!StringUtils.isEmpty(systemValue)) {
                properties.put(key, systemValue);
            }
        }
        return properties.getProperty(capability.getKey(), capability.getDefaultValue());
    }

    public static String getConfigProperty(Capability capability) {
        return getProperty(PropertyFile.CONFIG, capability);

    }
}
