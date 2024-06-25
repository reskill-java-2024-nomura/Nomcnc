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

	@GetMapping("/admin/hotels/{hotelId}/plans/add")
	public String create(
			@PathVariable("hotelId") Integer hotelId,
			Model model) {
		model.addAttribute("hotelId", hotelId);
		return "adminaddplan";
	}

	@PostMapping("/admin/plans/add")
	public String store(
			@RequestParam(name = "hotelId", defaultValue = "") Integer hotelId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "roomCount", defaultValue = "") Integer roomCount,
			@RequestParam(name = "note", defaultValue = "") String note,
			Model model) {
		Plan plan = new Plan(hotelId, name, price, roomCount, note);
		planRepository.save(plan);
		return "redirect:/admin/hotels/" + hotelId;
	}

	@GetMapping("/admin/plans/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		Plan plan = planRepository.findById(id).get();
		model.addAttribute("plan", plan);
		return "adminEditPlan";
	}

	//プラン削除処理
	@PostMapping("/admin/plans/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id) {
		Plan plan = planRepository.findById(id).get();
		Integer hotelId = plan.getHotelId();
		planRepository.deleteById(id);
		return "redirect:/admin/hotels/" + hotelId;
	}

	@PostMapping("/admin/plans/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "hotelId", defaultValue = "") Integer hotelId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price,
			@RequestParam(name = "roomCount", defaultValue = "") Integer roomCount,
			@RequestParam(name = "note", defaultValue = "") String note) {
		Plan plan = new Plan(id, hotelId, name, price, roomCount, note);
		planRepository.save(plan);
		return "redirect:/admin/hotels/" + hotelId;
	}
}
