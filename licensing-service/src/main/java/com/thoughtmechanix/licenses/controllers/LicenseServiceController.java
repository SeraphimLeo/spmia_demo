package com.thoughtmechanix.licenses.controllers;

import com.thoughtmechanix.licenses.model.License;
import com.thoughtmechanix.licenses.services.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LucasLiu
 * @date 2021/10/18
 */
@RestController
@RequestMapping("v1/organization/{organizationId}/licenses")
public class LicenseServiceController {

    @Autowired
    private LicenseService licenseService;


    @RequestMapping
    public void getLicenses(@PathVariable("organizationId") String organizationId) {
        System.out.println(organizationId);
        licenseService.timeOverTest(organizationId);
    }

    @RequestMapping("/{licensesId}/{clientType}")
    public License getLicensesWithClient(@PathVariable("organizationId") String organizationId,
                                         @PathVariable("licensesId") String licensesId,
                                         @PathVariable("clientType") String clientType) {

        return licenseService.getLicense(organizationId, licensesId, clientType);
    }



}
