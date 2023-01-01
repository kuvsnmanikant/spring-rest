package com.firstspringboot.learningspring.boot.controller;
// this is nothing to do with app just mock to check
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.firstspringboot.learningspring.boot.dto.Course;

@Controller
@RequiredArgsConstructor
@CrossOrigin("*")
public class FirstController {

    @RequestMapping("/first-controller")
    @ResponseBody
    public List<Course> retrieveAllCourses() {
        return Arrays.asList(
            new Course(1, "javas", "self-learn1"),
            new Course(1, "java", "self-learn15"),
            new Course(1, "java", "self-learn9")
        );
    }

    @RequestMapping("/say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello man! what are you doing?";
    }
    
    @RequestMapping("/say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer strbuf = new StringBuffer();
        strbuf.append("<html><head><title>my spring boot first page title</title></head><body><p>my spring boot first page body</p></body></html>");
        return strbuf.toString();
    }

    @RequestMapping("/say-hello-jsp") // for this we should not use @resopnsebody
    public String sayHelloJsp() {
        return "sayHello";
    }
}
