package com.gachi.haza.component;

import com.gachi.haza.dto.SocialProfile;
import com.gachi.haza.entity.Users;
import com.gachi.haza.entity.enums.Providers;
import com.gachi.haza.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        String registrationId = token.getAuthorizedClientRegistrationId();

        Providers provider = switch (registrationId) {
            case "google" -> Providers.GOOGLE;
            case "naver" -> Providers.NAVER;
            default -> throw new IllegalArgumentException("Unsupported provider: " + registrationId);
        };

        OAuth2User principal = (OAuth2User) authentication.getPrincipal();

        System.out.println("registrationId=" + registrationId);
        System.out.println("principal.attributes=" + principal.getAttributes());
        System.out.println("principal.name=" + principal.getName());

        SocialProfile profile = extractProfile(provider, principal);

        Users user = userService.findOrCreateSocialUser(profile);

        // TODO: 여기서 jwt 발급/쿠키 설정/리다이렉트 처리
        response.sendRedirect("/");
    }

    @SuppressWarnings("unchecked")
    private SocialProfile extractProfile(Providers provider, OAuth2User principal) {
        Map<String, Object> attrs = principal.getAttributes();

        if (provider == Providers.NAVER) {
            Map<String, Object> src = attrs;

            Object resObj = attrs.get("response");
            if (resObj instanceof Map) {
                @SuppressWarnings("unchecked")
                Map<String, Object> res = (Map<String, Object>) resObj;
                src = res;
            }

            String providerId = src.get("id") != null ? String.valueOf(src.get("id")) : null;
            String email = src.get("email") != null ? String.valueOf(src.get("email")) : null;
            String name = src.get("name") != null ? String.valueOf(src.get("name")) : null;

            String pictureUrl = null;
            Object picObj = src.get("profile_image");
            if (picObj == null) picObj = src.get("profile_image_url");
            if (picObj != null) pictureUrl = String.valueOf(picObj);

            if (providerId == null || providerId.isBlank()) {
                throw new IllegalStateException("naver id is null, attrs=" + attrs);
            }

            return new SocialProfile(provider, providerId, email, name, pictureUrl);
        }

        if (provider == Providers.GOOGLE) {
            String sub = principal.getAttribute("sub");
            String email = principal.getAttribute("email");
            String name = principal.getAttribute("name");
            String picture = principal.getAttribute("picture");

            if (sub == null || sub.isBlank()) {
                throw new IllegalStateException("google sub is null, attrs=" + attrs);
            }

            return new SocialProfile(provider, sub, email, name, picture);
        }

        throw new IllegalArgumentException("Unsupported provider: " + provider);
    }
}
