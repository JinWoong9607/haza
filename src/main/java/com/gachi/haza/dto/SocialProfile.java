package com.gachi.haza.dto;

import com.gachi.haza.entity.Users;
import com.gachi.haza.entity.enums.Providers;

public record SocialProfile(
        Providers provider,
        String providerId,
        String email,
        String name,
        String pictureUrl
) {
    public static SocialProfile from(Users user) {
        return new SocialProfile(
                user.getProvider(),
                user.getProviderId(),
                user.getEmail(),
                user.getUserName(),
                user.getImageUrl()
        );
    }
}
