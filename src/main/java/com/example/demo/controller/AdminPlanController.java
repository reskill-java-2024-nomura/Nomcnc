package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminPlanController {

	@GetMapping("/admin/plans/add")
	public String create() {
		return "adminaddplan";
	}

	@PostMapping("/admin/plans/add")
	public String store() {
		return "adminshowhotels";
	}
}
