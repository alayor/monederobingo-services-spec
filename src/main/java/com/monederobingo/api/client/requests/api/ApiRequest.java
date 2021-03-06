package com.monederobingo.api.client.requests.api;

import com.monederobingo.api.client.api_client.ApiClient;
import com.monederobingo.api.client.api_client.ApiUser;
import com.monederobingo.api.client.api_client.HttpMethod;
import com.monederobingo.api.client.api_client.HttpRequestData;
import com.monederobingo.api.client.model.ServiceResult;
import com.monederobingo.api.client.requests.ResultListener;

import static com.monederobingo.api.client.util.ParserUtil.toServiceResult;
import static java.util.Objects.nonNull;

public abstract class ApiRequest {

    protected ApiUser apiUser;
    private ResultListener resultListener;

    public ApiRequest(ApiUser apiUser, ResultListener resultListener) {
        this.apiUser = apiUser;
        this.resultListener = resultListener;
    }

    public ServiceResult send() {
        final HttpRequestData httpRequestData = getHttpRequestData(apiUser);
        final String restResponse = ApiClient.getRestResponse(httpRequestData);
        ServiceResult serviceResult = toServiceResult(restResponse);
        resultListener.set(serviceResult);
        return serviceResult;
    }

    private HttpRequestData getHttpRequestData(ApiUser apiUser) {
        HttpRequestData httpRequestData = new HttpRequestData()
                .setHttpMethod(getHttpMethod())
                .setApiUser(apiUser)
                .setBody(getRequestBody())
                .setUrlPath(getUrlPath())
                .setContentType("application/json")
                .setApiUrl("http://test.localhost:9090/");
        if (nonNull(apiUser.getApiToken()) && apiUser.getApiToken().length() > 0)
        {
            httpRequestData.setAuthorization("Bearer " + httpRequestData.getApiUser().getApiToken());
        }
        return httpRequestData;
    }

    public ApiUser getApiUser() {
        return apiUser;
    }

    protected abstract String getRequestBody();

    protected abstract HttpMethod getHttpMethod();

    protected abstract String getUrlPath();
}
