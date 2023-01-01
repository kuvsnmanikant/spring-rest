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
public class CommentDto implements Serializable {

    private long id;
    @NotEmpty(message = "Name sould not be null or empty")
    private String name;
    @Email(message = "Please enter valid email id")
    @NotEmpty(message = "Email sould not be null or empty")
    private String email;
    @NotEmpty
    @Size(min = 10, message = "Comment body should have at least 10 char")
    private String body;
}
