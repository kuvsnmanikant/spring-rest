package com.firstspringboot.learningspring.boot.services.interfaces;

import com.firstspringboot.learningspring.boot.dto.LoginDto;
import com.firstspringboot.learningspring.boot.dto.RegisterUserDto;

public interface AuthService {
    
    String login(LoginDto loginDto);
    String registerUser(RegisterUserDto registerUserDto);
}
