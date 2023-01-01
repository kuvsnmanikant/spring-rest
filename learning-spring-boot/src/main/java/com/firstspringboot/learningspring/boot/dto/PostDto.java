package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;
import java.util.Set;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto implements Serializable {

    private long id;
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 char")
    private String title;
    @NotEmpty
    @Size(min = 10, message = "Post description should have at least 10 char")
    private String description;
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    
}
