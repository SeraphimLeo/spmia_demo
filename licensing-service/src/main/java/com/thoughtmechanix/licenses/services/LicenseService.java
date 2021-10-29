package com.thoughtmechanix.licenses.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.thoughtmechanix.licenses.clients.OrganizationDiscoveryClient;
import com.thoughtmechanix.licenses.clients.OrganizationFeignClient;
import com.thoughtmechanix.licenses.clients.OrganizationRestTemplateClient;
import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
@Service
public class LicenseService {

    @Autowired
    private OrganizationDiscoveryClient organizationDiscoveryClient;

    @Autowired
    private OrganizationFeignClient organizationFeignClient;

    @Autowired
    private OrganizationRestTemplateClient organizationRestTemplateClient;


    public License getLicense(String organizationId, String licenseId, String clientType) {
        License license = new License();
        license.setLicenseId(licenseId);
        license.setOrganization(this.retrieveOrgInfo(organizationId, clientType));

        return license;
    }

    private Organization retrieveOrgInfo(String organizationId, String clientType) {

        Organization organization = null;
        switch (clientType) {
            case "feign":
                organization = organizationFeignClient.getOrganization(organizationId);
                break;
            case "rest":
                organization = organizationRestTemplateClient.getOrganization(organizationId);
                break;
            case "discovery":
                organization = organizationDiscoveryClient.getOrganization(organizationId);
                break;
            default:
                organization = organizationFeignClient.getOrganization(organizationId);
        }

        return organization;
    }

    //断路器设置
    @HystrixCommand(
            fallbackMethod = "buildFallbackLicenseList",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")}, //设置超时时间，另外可以还在 bootstrap.yml 中设置
            threadPoolKey = "timeOverTestPool", //设置
            threadPoolProperties = {@HystrixProperty(name = "coreSize", value = "30"), @HystrixProperty(name = "maxQueueSize", value = "10")})
    public void timeOverTest(String time) {
        organizationFeignClient.timeOver(time);
    }

    private void buildFallbackLicenseList(String time) {
        System.out.println("this is a fall back method" + time);
    }
}
