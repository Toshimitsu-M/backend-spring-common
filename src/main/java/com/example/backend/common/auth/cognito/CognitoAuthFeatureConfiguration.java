package com.example.backend.common.auth.cognito;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(CognitoAuthFeatureProperties.class)
public class CognitoAuthFeatureConfiguration {

    @Bean
    public CognitoLoginPathResolver cognitoLoginPathResolver(CognitoAuthFeatureProperties properties) {
        return new CognitoLoginPathResolver(properties.getRegistrationId());
    }

    @Bean
    public AuthenticatedUserInfoMapper authenticatedUserInfoMapper() {
        return new AuthenticatedUserInfoMapper();
    }
}
