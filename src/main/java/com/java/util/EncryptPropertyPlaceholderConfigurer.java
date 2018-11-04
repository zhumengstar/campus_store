package com.java.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @author:zhumeng
 * @desc:
 **/
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private String[] encryptPropNames = {"jdbc.username", "jdbc.password"};

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
//            String decryptValue=DESUtils.get
        }
        return "r";
    }

    private boolean isEncryptProp(String propertyName) {
        for (String encryptPropertyName : encryptPropNames) {
            if (encryptPropertyName.equals(propertyName))
                return true;
        }
        return false;
    }

}
