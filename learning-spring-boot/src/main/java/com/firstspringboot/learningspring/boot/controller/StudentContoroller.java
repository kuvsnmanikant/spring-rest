package com.firstspringboot.learningspring.boot.controller;

import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.firstspringboot.learningspring.boot.entries.Student;


@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/studentcontroller")
public class StudentContoroller {

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(Arrays.asList(
            new Student(1, "javas", "self-learn1"),
            new Student(1, "java", "self-learn15"),
            new Student(1, "java", "self-learn9")
        ));
    }

    @GetMapping("/students/{id}/{first-name}/{last-name}")  // this is the naming convention for the word having two words
    public ResponseEntity<Student> studentdPathVariable(@PathVariable("id") int id, @PathVariable("first-name") String firstName, @PathVariable("last-name") String lastName){
        //return new ResponseEntity<>(new Student(id, firstName, lastName), HttpStatus.OK);
        return ResponseEntity.ok().header("myname", "vijay").body(new Student(id, firstName, lastName));
    }
    
    @GetMapping("/students/query")
    public ResponseEntity<Student> studentQueryParam(@RequestParam int id, @RequestParam String firstName, @RequestParam String lastName){
        return ResponseEntity.ok(new Student(id, firstName, lastName));
    }

    @PostMapping("/students")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/students/{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int id){
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id){
        return ResponseEntity.ok("student "+id+" got deleted");
    }
}
