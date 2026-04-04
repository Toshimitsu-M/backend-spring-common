package com.example.backend.common.auth.cognito;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CognitoLoginPathResolverTest {

    @Test
    void loginPath_shouldBuildPathWithDefaultRegistrationId() {
        CognitoLoginPathResolver resolver = new CognitoLoginPathResolver(null);

        assertThat(resolver.loginPath()).isEqualTo("/oauth2/authorization/cognito");
    }

    @Test
    void loginPath_shouldBuildPathWithCustomRegistrationId() {
        CognitoLoginPathResolver resolver = new CognitoLoginPathResolver("corp-cognito");

        assertThat(resolver.loginPath()).isEqualTo("/oauth2/authorization/corp-cognito");
    }
}
