package it.springsecurity.comp;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello")
	public String hello() {
		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println("SecurityContext:" + context.toString());
		return "Hello!";
	}
	
	@Async
	@GetMapping("/hello2")
	public String hello2() {
		SecurityContext context = SecurityContextHolder.getContext();
		System.out.println("SecurityContext:" + context.toString());
		return "Hello2!";
	}
}