package com.thoughtmechanix.organization.controller;

import com.thoughtmechanix.organization.event.source.SimpleSourceBean;
import com.thoughtmechanix.organization.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
@RestController
@RequestMapping("v1/organizations/")
public class OrganizationServiceController {

    @Autowired
    private SimpleSourceBean simpleSourceBean;


    @RequestMapping("{organizationId}")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId) {
        Organization organization = new Organization();
        organization.setId(organizationId);
        organization.setName("testName");
        organization.setContactName("testContactName");
        organization.setContactEmail("testContactEmail");
        organization.setContactPhone("1234567890");


        simpleSourceBean.publishOrChange("testSave", organizationId);
        return organization;
    }

    @RequestMapping("/timeover/{time}")
    public void timeoverMethod(@PathVariable("time") String time) throws InterruptedException {
        System.out.println("this is time over method , " + time);
        Thread.sleep(11000);
    }

}
