package com.firstspringboot.learningspring.boot.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor  
@AllArgsConstructor  
@ToString  
public class Course implements Serializable {
    private @Setter@Getter long id;
    private @Setter@Getter String name;
    private @Setter@Getter String author;
}
