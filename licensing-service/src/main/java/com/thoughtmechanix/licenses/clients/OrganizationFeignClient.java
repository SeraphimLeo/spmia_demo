package com.thoughtmechanix.licenses.clients;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.thoughtmechanix.licenses.model.Organization;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
@FeignClient("organizationservice")
public interface OrganizationFeignClient {


    @RequestMapping(method = RequestMethod.GET,
            value = "v1/organizations/{organizationId}",
            consumes = "application/json")
    public Organization getOrganization(@PathVariable("organizationId") String organizationId);


    @RequestMapping(value = "v1/organizations/timeover/{time}", method = RequestMethod.GET)
    public void timeOver(@PathVariable("time") String time);
}
