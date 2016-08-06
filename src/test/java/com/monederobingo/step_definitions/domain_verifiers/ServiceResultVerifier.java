/* Copyright 2016 Sabre Holdings */
package com.monederobingo.step_definitions.domain_verifiers;

import com.monederobingo.step_definitions.domain_holders.ServiceResultHolder;
import cucumber.api.java.en.Then;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ServiceResultVerifier
{
    private final ServiceResultHolder serviceResultHolder;

    public ServiceResultVerifier(ServiceResultHolder serviceResultHolder)
    {
        this.serviceResultHolder = serviceResultHolder;
    }

    @Then("^The response should be successful$")
    public void theResponseShouldBeSuccessful() throws Throwable
    {
        assertTrue(serviceResultHolder.get().isSuccess());
    }

    @Then("^The response should be not successful$")
    public void theResponseShouldBeNotSuccessful() throws Throwable
    {
        assertFalse(serviceResultHolder.get().isSuccess());
    }
}