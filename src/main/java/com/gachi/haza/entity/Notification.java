package com.gachi.haza.entity;

import com.gachi.haza.entity.enums.NotificationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
@Getter
public class Notification {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users requestor;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "type", nullable = false)
    private NotificationType type;
}
