package com.gachi.haza.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("해당 사용자를 찾을 수 없습니다. (ID: " + id + ")");
    }

}
