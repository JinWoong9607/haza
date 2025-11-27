package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="game_genres")
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class GameGenres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="game_id", nullable = false)
    private Games gameId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="genre_id", nullable = false)
    private Genres genreId;


}
