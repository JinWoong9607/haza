package com.gachi.haza.entity.enums;

public enum FriendStatus {
    PENDING("보류"),
    ACCEPT("수락"),
    CANCELED("취소"),
    BLOCKED("차단");

    private final String description;

    FriendStatus(String description) {
        this.description = description;
    }
}
