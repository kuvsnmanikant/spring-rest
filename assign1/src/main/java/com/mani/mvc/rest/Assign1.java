package com.mani.mvc.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Assign1 {

	@RequestMapping("/hi")
	public String helloWorld() {
		return "Hello World";
	}
	
	
}
