package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPlanController {

	@GetMapping("/admin/plans/add")
	public String create() {
		return "adminaddplan";
	}

	@PostMapping("/admin/plans/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {
		model.addAttribute("name", name);
		return "adminshowhotels";
	}
}
