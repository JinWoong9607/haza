package com.gachi.haza.entity;

import com.gachi.haza.entity.enums.Providers;
import com.gachi.haza.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_provider_pid", columnNames = {"provider", "provider_id"})
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Users {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider")
    private Providers provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Column(name= "img_url", nullable = true)
    private String imageUrl;

    @Column(name = "user_about", nullable = true)
    private String userAbout;

    @Column(name = "play_time", nullable = true)
    private String playTime;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "user_status", nullable = false)
    private UserStatus userStatus;

    @Column(name = "email", nullable = false, unique = true)
    private String email;


    public static Users createOAuthUser(Providers provider, String providerId, String email, String userName) {
        Users u = new Users();
        u.provider = provider;
        u.providerId = providerId;
        u.email = email;
        u.userName = userName;
        u.userStatus = UserStatus.ACTIVE;
        u.password = null;
        return u;
    }

    public void updateProfileImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
