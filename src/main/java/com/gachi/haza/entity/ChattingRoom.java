package com.gachi.haza.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "chatting_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ChattingRoom {

}
