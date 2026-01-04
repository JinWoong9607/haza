package com.gachi.haza.controller;

import com.gachi.haza.dto.SocialProfile;
import com.gachi.haza.entity.Users;
import com.gachi.haza.global.response.ApiResponse;
import com.gachi.haza.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth")
public class AuthController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ApiResponse<SocialProfile> me(Authentication authentication) {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return ApiResponse.createSuccess(SocialProfile.from(user));
    }

}