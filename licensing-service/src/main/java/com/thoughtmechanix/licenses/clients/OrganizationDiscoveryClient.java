package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
@Component
public class OrganizationDiscoveryClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    public Organization getOrganization(String organizationId) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instanceList = discoveryClient.getInstances("organizationservice");


        if (instanceList.size() != 0) {

            String serviceUri = String.format("%s/v1/organizations/%s",
                    instanceList.get(new Random().nextInt(instanceList.size())).getUri().toString(),
                    organizationId);
            System.out.println(serviceUri);
            ResponseEntity<Organization> responseEntity = restTemplate.exchange(serviceUri, HttpMethod.GET, null, Organization.class);
            System.out.println(responseEntity.getBody());
            return responseEntity.getBody();

        } else {
            System.out.println("organization service instance is null !");
            return null;
        }
    }
}
