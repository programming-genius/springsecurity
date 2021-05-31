package it.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPageController {

	@GetMapping("/")
	public String main() {
		return "main.html";
	}
	
	@PostMapping("/cors")
	@ResponseBody
	@CrossOrigin(origins="http://localhost") 
	public String test() {
		return "http://localhost is now allowed";
	}
}