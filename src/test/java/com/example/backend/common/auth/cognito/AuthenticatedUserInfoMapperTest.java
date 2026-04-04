package com.example.backend.common.auth.cognito;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

class AuthenticatedUserInfoMapperTest {

    private final AuthenticatedUserInfoMapper mapper = new AuthenticatedUserInfoMapper();

    @Test
    void toMap_shouldReturnMinimalPayloadWhenAuthenticationIsNull() {
        Map<String, Object> result = mapper.toMap(null);

        assertThat(result)
                .containsEntry("authenticated", false)
                .hasSize(1);
    }

    @Test
    void toMap_shouldIncludeNameAuthoritiesAndAttributes() {
        DefaultOAuth2User principal = new DefaultOAuth2User(
                java.util.List.of(new SimpleGrantedAuthority("ROLE_USER")),
                Map.of("email", "user@example.com", "sub", "abc-123"),
                "sub");

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(principal, "n/a", principal.getAuthorities());

        Map<String, Object> result = mapper.toMap(authentication);

        assertThat(result).containsEntry("authenticated", true);
        assertThat(result).containsEntry("name", "abc-123");
        assertThat(result).containsKey("authorities");
        assertThat(result).containsKey("attributes");
    }
}
