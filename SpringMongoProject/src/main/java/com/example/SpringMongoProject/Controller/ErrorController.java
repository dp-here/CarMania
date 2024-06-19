package com.example.SpringMongoProject.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("/error")
@RequiredArgsConstructor
public class ErrorController {

	@GetMapping
	public String getError() {
		return "Hello";
	}
}
