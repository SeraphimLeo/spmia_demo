package com.thoughtmechanix.organization.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author LucasLiu
 * @date 2021/10/27
 */
@Component
public class TestRemoteProperties {

    @Value("${lala.test}")
    public String testProperty;

    public String getTestProperty() {
        return testProperty;
    }

    public void setTestProperty(String testProperty) {
        this.testProperty = testProperty;
    }
}
