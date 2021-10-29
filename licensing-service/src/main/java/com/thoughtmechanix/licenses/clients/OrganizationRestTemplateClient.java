package com.thoughtmechanix.licenses.clients;

import com.thoughtmechanix.licenses.model.Organization;
import com.thoughtmechanix.licenses.repository.OrganizationRedisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
@Component
public class OrganizationRestTemplateClient {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrganizationRedisRepository organizationRedisRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationRestTemplateClient.class);


    private Organization checkRedisCache(String organizationId) {
        try {
            return organizationRedisRepository.findOrganization(organizationId);
        } catch (Exception e) {
            logger.error("Error encountered while trying to retrieve organization {} check Redis Cache.Exception {}", organizationId, e);
            return null;
        }
    }

    private void cacheOrganizationObject(Organization organization) {
        try {
            organizationRedisRepository.saveOrganization(organization);
        } catch (Exception e) {
            logger.error("Unable to cache organization {} in redis , exception {}."
                    , organization.getId()
                    , e);
        }
    }

    public Organization getOrganization(String organizationId) {
        Organization organization = this.checkRedisCache(organizationId);
        if (organization != null) {
            logger.debug("I have successfully retrieved an organization {} from the redis cache {}"
                    , organizationId
                    , organization);
            return organization;
        } else {
            organization = restTemplate.exchange("http://organizationservice/v1/organizations/{organizationId}",
                    HttpMethod.GET,
                    null,
                    Organization.class,
                    organizationId).getBody();

            cacheOrganizationObject(organization);
            return organization;
        }
    }


}
