package com.firstspringboot.learningspring.boot.exception;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogAPIException extends RuntimeException {
    
    private HttpStatus status;
    private String message;
    public BlogAPIException(Throwable cause, HttpStatus status, String message) {
        super(cause);
        this.status = status;
        this.message = message;
    }
    
    
}
