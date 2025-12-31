package com.gachi.haza.service;

import com.gachi.haza.dto.UserProfileResponseDto;
import com.gachi.haza.dto.UserResponseDto;
import com.gachi.haza.entity.Users;
import com.gachi.haza.exception.UserNotFoundException;
import com.gachi.haza.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public UserResponseDto getUserInfo(Long id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserResponseDto.from(user);
    }
    @Transactional(readOnly = true)
    public UserProfileResponseDto getUserProfile(Long id){
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return UserProfileResponseDto.from(user);
    }
}
