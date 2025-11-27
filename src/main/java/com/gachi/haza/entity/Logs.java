package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="logs")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="game_name",nullable = false)
    private String gameName;
}
