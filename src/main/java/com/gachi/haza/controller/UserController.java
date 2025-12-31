package com.gachi.haza.controller;

import com.gachi.haza.dto.UserProfileResponseDto;
import com.gachi.haza.dto.UserResponseDto;
import com.gachi.haza.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
    // logger
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUserInfo(id);
    }
    @GetMapping("/profile/{id}")
    public UserProfileResponseDto getUserProfile(@PathVariable Long id) {
        return userService.getUserProfile(id);
    }

}
