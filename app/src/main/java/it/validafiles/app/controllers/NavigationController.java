package it.validafiles.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {
	
	@GetMapping(value="/homepage")
	public String showHome() {
		
		return "home";
	}
	
	@GetMapping(value="/")
	public String showIndex() {
		
		return "home";
	}
	
	
}
