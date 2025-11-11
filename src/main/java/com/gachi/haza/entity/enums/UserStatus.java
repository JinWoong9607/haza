package com.gachi.haza.entity.enums;

public enum UserStatus {
    ACTIVE("활성화"),
    INACTIVE("일시정지"),
    BANNED("정지");

    private final String description;

    UserStatus(String description) {
        this.description = description;
    }
}