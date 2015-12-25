package com.lealpoints.automated_tests.actions.registration;

import com.lealpoints.automated_tests.actions.util.ParserUtil;
import com.lealpoints.automated_tests.api.ApiClient;
import com.lealpoints.automated_tests.api.HttpMethod;
import com.lealpoints.automated_tests.model.ServiceResult;
import org.json.JSONObject;

public class CompanyRegistrationAction {
    public static final Data DEFAULT_DATA = new Data();

    private static String getJson(Data data) {
        return new JSONObject()
                .put("companyName", data.companyName)
                .put("urlImageLogo", data.urlImageLogo)
                .put("userName", data.userName)
                .put("email", data.email)
                .put("password", data.password)
                .put("passwordConfirmation", data.passwordConfirmation)
                .put("language", data.language)
                .toString();
    }

    public static ServiceResult registerCompany(Data data) {
        String restResponse = ApiClient.getRestResponse(HttpMethod.POST, "company/register", getJson(data));
        return ParserUtil.toServiceResult(restResponse);
    }

    public static class Data {
        private String companyName = "company name";
        private String urlImageLogo = "images/logo.png";
        private String userName = "user name";
        private String email = "test@lealpoints.com";
        private String password = "Pa$$w0rd";
        private String passwordConfirmation = "Pa$$w0rd";
        private String language = "en";

        public Data setEmail(String email) {
            this.email = email;
            return this;
        }

        public Data setPassword(String password) {
            this.password = password;
            return this;
        }

        public Data setPasswordConfirmation(String passwordConfirmation) {
            this.passwordConfirmation = passwordConfirmation;
            return this;
        }
    }
}
