package com.thoughtmechanix.licenses.repository;

import com.thoughtmechanix.licenses.model.Organization;

/**
 * @author LucasLiu
 * @date 2021/10/28
 */
public interface OrganizationRedisRepository {

    void saveOrganization(Organization organization);

    void updateOrganization(Organization organization);

    void deleteOrganization(String organizationId);

    Organization findOrganization(String organizationId);
}
