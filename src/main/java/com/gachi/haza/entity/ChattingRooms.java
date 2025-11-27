package com.gachi.haza.entity;

import com.gachi.haza.entity.enums.RoomType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chatting_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingRooms {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titles")
    private String title;

    @Column(name = "max_participants")
    private RoomType maxParticipants;

    @Column(name = "game")
    private String game;

    @Column(name = "play_level")
    private String playLevel;

    @Column(name = "play_style")
    private String playStyle;


}
