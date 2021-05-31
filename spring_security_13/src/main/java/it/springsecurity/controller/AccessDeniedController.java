package it.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessDeniedController {
	@GetMapping("/403")
	public String getAccessDenied() {
		return "/error/403";
	}
}
