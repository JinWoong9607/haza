package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="user_games")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserGames {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private Users userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="game_id",nullable = false)
    private Games gameId;

    @Column(name="is_bookmarked",nullable = false)
    private boolean isBookmarked = false;

    @Column(name="is_preferred", nullable = false)
    private boolean isPreferred = false;

}
