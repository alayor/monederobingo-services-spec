package com.lealpoints.tests.requests.api.points;

import com.lealpoints.tests.api_client.ApiUser;
import com.lealpoints.tests.api_client.HttpMethod;
import com.lealpoints.tests.requests.api.ApiRequest;
import org.json.JSONObject;

public class PointsAwardingRequest extends ApiRequest {
    private long companyId = 0;
    private String phoneNumber = "6661234567";
    private String saleKey = "ABC";

    public PointsAwardingRequest(ApiUser apiUser) {
        super(apiUser);
        companyId = Long.parseLong(apiUser.getCompanyId());
    }

    @Override
    public String getRequestBody() {
        float saleAmount = 100;
        return new JSONObject()
                .put("companyId", companyId)
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

    public PointsAwardingRequest setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PointsAwardingRequest setSaleKey(String saleKey) {
        this.saleKey = saleKey;
        return this;
    }
}
