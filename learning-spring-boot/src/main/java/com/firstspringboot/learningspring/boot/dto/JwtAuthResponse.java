package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse implements Serializable {

    private String accessToken;
    private String tokenType = "Bearer";
   // private Date expirTime; if you want to add this then go ti security/JwtTokenprovic=der.java > generateToken add this class as return type and and chage accordingly
    
}
