package com.example.backend.common.auth.cognito;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "common.auth.cognito")
public class CognitoAuthFeatureProperties {

    /**
     * OAuth2 registration id for login redirection.
     */
    private String registrationId = "cognito";

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}
