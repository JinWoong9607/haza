package com.gachi.haza.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Providers {

    GOOGLE("google", "GOOGLE", "구글"),
    NAVER("naver", "NAVER", "네이버");

    private final String registrationId;

    private final String code;

    private final String displayName;

    public static Providers fromRegistrationId(String registrationId) {
        for (Providers provider : values()) {
            if (provider.registrationId.equalsIgnoreCase(registrationId)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 provider: " + registrationId);
    }

    public static Providers fromCode(String code) {
        for (Providers provider : values()) {
            if (provider.code.equalsIgnoreCase(code)) {
                return provider;
            }
        }
        throw new IllegalArgumentException("지원하지 않는 provider 코드: " + code);
    }
}
