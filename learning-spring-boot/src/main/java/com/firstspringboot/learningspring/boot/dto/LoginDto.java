package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {

    private String usernameOrEmail;
    private String password;

}
