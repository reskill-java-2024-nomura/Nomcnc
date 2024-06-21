package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
class HotelController {

	@GetMapping("/top")
	public String top(
			@RequestParam("categoryId") Integer categoryId,
			Model model) {
		
		
		return "top";
	}
	
	@GetMapping("/hotels")
	
}
