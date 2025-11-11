package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="genre_prefer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class GenrePrefers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id",nullable = false)
    private Users userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="genre_id",nullable = false)
    private Genres genreId;
}
