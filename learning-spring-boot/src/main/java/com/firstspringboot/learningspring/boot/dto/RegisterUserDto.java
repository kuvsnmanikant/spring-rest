package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto implements Serializable {

    @NotEmpty
    @Size(min = 4, message = "User name should have at least 4 char")
    private String name;

    @NotEmpty
    @Size(min = 4, message = "User userName should have at least 4 char")
    private String userName;

    @NotEmpty
    @Email(message = "Please enter valid email id")
    private String email;

    @NotEmpty
    @Size(min = 8, message = "User password should have at least 8 char")
    private String password;
}
