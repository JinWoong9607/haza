package com.gachi.haza.service;

import com.gachi.haza.dto.SocialProfile;
import com.gachi.haza.entity.enums.Providers;
import com.gachi.haza.entity.Users;
import com.gachi.haza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Users findOrCreateOAuthUser(OAuth2User principal) {

        String provider = principal.getAttribute("provider");
        String email = principal.getAttribute("email");

        String providerId;
        if ("NAVER".equals(provider)) {
            providerId = principal.getAttribute("id");
        } else {
            providerId = principal.getAttribute("sub");
        }

        if (providerId == null) throw new IllegalStateException("providerId is null");
        if (email == null) throw new IllegalStateException("email is null");

        Providers p = Providers.valueOf(provider);

        return userRepository.findByProviderAndProviderId(p, providerId)
                .orElseGet(() -> userRepository.save(
                        Users.createOAuthUser(p, provider, providerId, email)
                ));
    }

    @Transactional
    public Users findOrCreateSocialUser(SocialProfile profile) {
        if (profile.provider() == null) throw new IllegalStateException("provider is null");
        if (profile.providerId() == null || profile.providerId().isBlank())
            throw new IllegalStateException("providerId is null/blank");

        String email = profile.email();

        return userRepository.findByProviderAndProviderId(profile.provider(), profile.providerId())
                .orElseGet(() -> {
                    String username = generateUniqueUsername(email, profile.name(), profile.provider());
                    return userRepository.save(
                            Users.createOAuthUser(profile.provider(), profile.providerId(), email, username)
                    );
                });
    }

    private String generateUniqueUsername(String email, String name, Providers provider) {
        String base;

        if (name != null && !name.isBlank()) {
            base = sanitize(name);
        } else if (email != null && !email.isBlank() && email.contains("@")) {
            base = sanitize(email.substring(0, email.indexOf('@')));
        } else {
            base = provider.name().toLowerCase() + "_user";
        }

        if (base.length() < 3) base = base + "_user";

        if (!userRepository.existsByUserName(base)) {
            return base;
        }

        for (int i = 2; i <= 50; i++) {
            String candidate = base + i;
            if (!userRepository.existsByUserName(candidate)) {
                return candidate;
            }
        }

        for (int i = 0; i < 10; i++) {
            String rand = java.util.UUID.randomUUID().toString().replace("-", "").substring(0, 6);
            String candidate = base + "_" + rand;
            if (!userRepository.existsByUserName(candidate)) {
                return candidate;
            }
        }

        return base + "_" + System.currentTimeMillis();
    }

    private String sanitize(String s) {
        String out = s.trim();

        out = out.replaceAll("\\s+", "_");

        if (out.length() > 20) out = out.substring(0, 20);

        return out;
    }

}
