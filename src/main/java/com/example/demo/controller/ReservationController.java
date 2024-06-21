package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Plan;
import com.example.demo.repository.PlanRepository;

@Controller
public class ReservationController {

	@Autowired
	PlanRepository planRepository;

	@GetMapping("reservation/input/{planId}")
	public String reserve(
			@PathVariable("planId") Integer planId,
			Model model) {

		Plan plan = planRepository.findById(planId).get();
		model.addAttribute("plan", plan);

		return "reservationInput";
	}

}
