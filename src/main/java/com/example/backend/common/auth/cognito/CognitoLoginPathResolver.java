package com.example.backend.common.auth.cognito;

import java.util.Objects;

public class CognitoLoginPathResolver {

    private final String registrationId;

    public CognitoLoginPathResolver(String registrationId) {
        this.registrationId = Objects.requireNonNullElse(registrationId, "cognito");
    }

    public String loginPath() {
        return "/oauth2/authorization/" + registrationId;
    }
}
