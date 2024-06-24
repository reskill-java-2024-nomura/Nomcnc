package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Plan;
import com.example.demo.repository.PlanRepository;

@Controller
public class AdminPlanController {

	@Autowired
	PlanRepository planRepository;

	@GetMapping("/admin/plans/add")
	public String create() {
		return "adminaddplan";
	}

	@PostMapping("/admin/plans/add")
	public String store(
			@PathVariable("hotelId") Integer hotelId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "roomCount", defaultValue = "") Integer roomCount,
			@RequestParam(name = "note", defaultValue = "") String note,
			Model model) {
		Plan plan = new Plan(hotelId, name, price, roomCount, note);
		planRepository.save(plan);
		return "redirect:/admin/hotels/{id}";
	}
}
