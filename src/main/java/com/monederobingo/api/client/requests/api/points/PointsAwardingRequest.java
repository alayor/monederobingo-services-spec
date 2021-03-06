package com.monederobingo.api.client.requests.api.points;

import com.monederobingo.api.client.api_client.ApiUser;
import com.monederobingo.api.client.api_client.HttpMethod;
import com.monederobingo.api.client.requests.ResultListener;
import com.monederobingo.api.client.requests.api.ApiRequest;
import org.json.JSONObject;

import static java.lang.Long.parseLong;

public class PointsAwardingRequest extends ApiRequest
{
    private String phoneNumber = "6661234567";
    private String saleKey = "ABC";
    private float saleAmount = 100;

    public PointsAwardingRequest(ApiUser apiUser, ResultListener resultListener) {
        super(apiUser, resultListener);
    }

    @Override
    public String getRequestBody() {
        return new JSONObject()
                .put("companyId", apiUser.getCompanyId())
                .put("phoneNumber", phoneNumber)
                .put("saleAmount", saleAmount)
                .put("saleKey", saleKey)
                .toString();
    }

    @Override
    protected HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    protected String getUrlPath() {
        return "/api/v1/points";
    }

    public PointsAwardingRequest withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PointsAwardingRequest andSaleKey(String saleKey) {
        this.saleKey = saleKey;
        return this;
    }

    public float getSaleAmount() {
        return saleAmount;
    }

    public PointsAwardingRequest andSaleAmount(float saleAmount) {
        this.saleAmount = saleAmount;
        return this;
    }
}
