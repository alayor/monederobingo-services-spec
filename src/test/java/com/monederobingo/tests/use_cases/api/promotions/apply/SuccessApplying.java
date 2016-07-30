package com.monederobingo.tests.use_cases.api.promotions.apply;

import com.monederobingo.tests.use_cases.api.ApiUseCase;
import com.monederobingo.api.client.model.Language;
import com.monederobingo.api.client.model.ServiceResult;
import com.monederobingo.api.client.requests.api.points.PointsAwardingRequest;
import com.monederobingo.api.client.requests.api.promotions.ApplyPromotionRequest;
import com.monederobingo.api.client.requests.api.promotions.PromotionGetAvailableByClientRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class SuccessApplying extends ApiUseCase
{
    private ServiceResult serviceResult;
    String phoneNumber = "6661234567";
    long promotionConfigurationId;

    @Before
    public void setUp() {
        PointsAwardingRequest pointsAwardingRequest = new PointsAwardingRequest(getApiUser());
        pointsAwardingRequest.setPhoneNumber(phoneNumber);
        pointsAwardingRequest.setSaleAmount(1000);
        pointsAwardingRequest.send();

        PromotionGetAvailableByClientRequest promotionGetAvailableByClientRequest =
                new PromotionGetAvailableByClientRequest(getApiUser());
        promotionGetAvailableByClientRequest.setPhoneNumber(phoneNumber);
        ServiceResult serviceResult = promotionGetAvailableByClientRequest.send();
        promotionConfigurationId = serviceResult.getJSONArray().getJSONObject(0).getLong("promotionConfigurationId");
    }

    @Test
    public void test() {
        ApplyPromotionRequest applyPromotionRequest = new ApplyPromotionRequest(getApiUser());
        applyPromotionRequest.setPhoneNumber(phoneNumber);
        applyPromotionRequest.setPromotionConfigurationId(promotionConfigurationId);
        serviceResult = applyPromotionRequest.send();
        runAssertions(serviceResult);
    }

    private void runAssertions(ServiceResult serviceResult) {
        assertNotNull(serviceResult);
        assertEquals(true, serviceResult.isSuccess());
    }

    @Override
    protected Map<Language, String> getExpectedMessages() {
        Map<Language, String> expectedMessages = new HashMap<>();
        expectedMessages.put(Language.ENGLISH, "Promotion applied.");
        expectedMessages.put(Language.SPANISH, "Promoción aplicada.");
        return expectedMessages;
    }

    @Override
    protected ServiceResult getServiceResult() {
        return serviceResult;
    }
}
