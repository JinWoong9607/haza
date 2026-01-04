package com.gachi.haza.dto;

import com.gachi.haza.entity.Users;
import com.gachi.haza.entity.enums.Providers;
import com.gachi.haza.entity.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private Providers provider;
    private String providerId;
    private String userName;
    private String imageUrl;
    private String userAbout;
    private String playTime;
    private String password;
    private UserStatus userStatus;
    private String email;

    public static UserResponseDto from(Users user) {
        return new UserResponseDto(
                user.getId(),
                user.getProvider(),
                user.getProviderId(),
                user.getUserName(),
                user.getImageUrl(),
                user.getUserAbout(),
                user.getPlayTime(),
                user.getPassword(),
                user.getUserStatus(),
                user.getEmail()
        );
    }
}
