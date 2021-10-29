package com.thoughtmechanix.licenses.model;

/**
 * @author LucasLiu
 * @date 2021/10/19
 */
public class License {

    private String licenseId;
    private Organization organization;

    public String getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(String licenseId) {
        this.licenseId = licenseId;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
