package com.gachi.haza.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="games")
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
    private String imgurl = "img/default.png";

    @Column(name="game_about")
    private String gameAbout;

}
