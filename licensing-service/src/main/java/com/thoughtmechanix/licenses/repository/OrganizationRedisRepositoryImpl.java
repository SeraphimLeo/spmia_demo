package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * @author LucasLiu
 * @date 2021/10/28
 */

@Repository
public class OrganizationRedisRepositoryImpl implements OrganizationRedisRepository {

    private static final String HASH_NAME = "organization";

    private RedisTemplate<String, Organization> redisTemplate;
    private HashOperations<String, String, Organization> hashOperations;

    private Logger logger = LoggerFactory.getLogger(OrganizationRedisRepositoryImpl.class);

    public OrganizationRedisRepositoryImpl() {
        super();
    }

    @Autowired
    public OrganizationRedisRepositoryImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void saveOrganization(Organization organization) {
        logger.debug("HASH_NAME:{},organizationId:{},organization:{}.", HASH_NAME, organization.getId(), organization);
        hashOperations.put(HASH_NAME, organization.getId(), organization);
    }

    @Override
    public void updateOrganization(Organization organization) {
        hashOperations.put(HASH_NAME, organization.getId(), organization);
    }

    @Override
    public void deleteOrganization(String organizationId) {
        hashOperations.delete(HASH_NAME, organizationId);
    }

    @Override
    public Organization findOrganization(String organizationId) {
        return (Organization) hashOperations.get(HASH_NAME, organizationId);
    }
}
