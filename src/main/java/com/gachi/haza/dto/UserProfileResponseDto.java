package com.gachi.haza.dto;

import com.gachi.haza.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {
    public Long id;
    public String userName;
    public String imageUrl;
    public String userAbout;

    public static UserProfileResponseDto from(Users user) {
        return UserProfileResponseDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .imageUrl(user.getImageUrl())
                .userAbout(user.getUserAbout())
                .build();
    }
}
