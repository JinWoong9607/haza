package com.gachi.haza.component;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // google / naver
        Map<String, Object> attributes = new HashMap<>(oAuth2User.getAttributes());

        if ("naver".equals(registrationId)) {
            Object responseObj = attributes.get("response");
            if (responseObj instanceof Map<?, ?> responseMap) {
                Map<String, Object> flat = new HashMap<>();
                for (Map.Entry<?, ?> e : responseMap.entrySet()) {
                    flat.put(String.valueOf(e.getKey()), e.getValue());
                }
                flat.put("provider", "NAVER");
                return new DefaultOAuth2User(
                        Set.of(() -> "ROLE_USER"),
                        flat,
                        "email"
                );
            }
        }

        attributes.put("provider", registrationId.toUpperCase());

        return new DefaultOAuth2User(
                Set.of(() -> "ROLE_USER"),
                attributes,
                "email"
        );
    }
}
