package com.gachi.haza.entity.enums;

public enum RoomType {
    T2(2),
    T3(3),
    T4(4),
    T5(5),
    T6(6),
    T7(7),
    T8(8),
    T9(9),
    T10(10);


    private final int maxCapacity;

    RoomType(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

}
