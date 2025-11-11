package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="game")
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Getter
public class Games {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="game_name",nullable = false,unique = true)
    private String gameName;

    @Column(name="imgurl",nullable = false)
    @ColumnDefault("img/default.png")
    private String imgurl;

    @Column(name="game_about")
    private String gameAbout;

}
