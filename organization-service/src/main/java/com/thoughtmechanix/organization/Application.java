package com.thoughtmechanix.organization;

import com.thoughtmechanix.organization.utils.TestRemoteProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;

/**
 * @author LucasLiu
 * @date 2021/10/18
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableBinding(Source.class)
public class Application {

    @Autowired
    private TestRemoteProperties testRemoteProperties;


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
