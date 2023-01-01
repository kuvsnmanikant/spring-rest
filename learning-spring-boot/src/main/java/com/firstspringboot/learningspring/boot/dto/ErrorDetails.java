package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDetails implements Serializable {

    private Date timeStamp;
    private String message;
    private String details;

}
