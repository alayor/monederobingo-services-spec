package com.lealpoints.tests.functional.cases.api.company_user;

import com.lealpoints.tests.functional.cases.api.BaseApiTest;
import com.lealpoints.tests.model.Language;
import com.lealpoints.tests.model.ServiceResult;
import com.lealpoints.tests.requests.api.users.CompanyUserRegistrationRequest;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SuccessfulRegistration extends BaseApiTest {
    private ServiceResult serviceResult;

    @Test
    public void test() {
        serviceResult = new CompanyUserRegistrationRequest(getApiUser()).send();
        runAssertions(serviceResult);
    }

    private void runAssertions(ServiceResult serviceResult) {
        assertNotNull(serviceResult);
        assertTrue(Long.parseLong(serviceResult.getObject()) > 0);
    }

    @Override
    protected Map<Language, String> getExpectedMessages() {
        Map<Language, String> expectedMessages = new HashMap<>();
        expectedMessages.put(Language.ENGLISH, "The user was successfully added.");
        expectedMessages.put(Language.SPANISH, "El usuario fue agregado exitosamente.");
        return expectedMessages;
    }

    @Override
    protected ServiceResult getServiceResult() {
        return serviceResult;
    }
}
