package com.example.backend.common.auth.cognito;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthenticatedUserInfoMapper {

    public Map<String, Object> toMap(Authentication authentication) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("authenticated", authentication != null && authentication.isAuthenticated());

        if (authentication == null) {
            return result;
        }

        result.put("name", authentication.getName());

        List<String> authorities = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .sorted()
                .collect(Collectors.toList());
        result.put("authorities", authorities);

        Object principal = authentication.getPrincipal();
        if (principal instanceof OAuth2User oauth2User) {
            result.put("attributes", oauth2User.getAttributes());
        }

        return result;
    }
}
