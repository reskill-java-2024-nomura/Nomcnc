package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Category;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Plan;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.PlanRepository;

@Controller
class HotelController {

	@Autowired
	PlanRepository planRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	HotelRepository hotelRepository;

	@GetMapping("/top")
	public String top(
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "top";
	}

	@GetMapping("/hotels/{categoryId}")
	public String getHotels(
			@PathVariable("categoryId") Integer categoryId,
			Model model) {

		Category category = categoryRepository.findById(categoryId).get();
		model.addAttribute("category", category);

		List<Hotel> hotels = hotelRepository.findByCategoryId(categoryId);
		model.addAttribute("hotels", hotels);

		return "hotelList";
	}

	@GetMapping("/hotels/details/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		Hotel hotel = hotelRepository.findById(id).get();
		model.addAttribute("hotel", hotel);

		List<Plan> plans = planRepository.findByHotelId(id);
		model.addAttribute("plans", plans);

		return "hotelDetail";
	}

}
