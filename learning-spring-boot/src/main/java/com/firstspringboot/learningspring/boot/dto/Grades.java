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
public class Grades implements Serializable {
    private @Getter@Setter String name;
    private @Getter@Setter String subject;
    private @Getter@Setter String score;
}
