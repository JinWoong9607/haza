package com.gachi.haza.entity.enums;

public enum NotificationType {
    FRIEND_REQUEST("친구 요청"),
    SYSTEM("시스템"),
    MESSAGE("메시지");

    private final String description;

    NotificationType(String description) {
        this.description = description;
    }

}
