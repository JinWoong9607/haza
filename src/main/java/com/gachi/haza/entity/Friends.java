package com.gachi.haza.entity;

import com.gachi.haza.entity.enums.FriendStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "friends")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users requestor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_id", nullable = false)
    private Users receiver;

    @Column(name = "friend_status", nullable = false)
    private FriendStatus status;

}
