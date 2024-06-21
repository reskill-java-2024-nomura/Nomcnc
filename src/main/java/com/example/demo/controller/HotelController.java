package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;

@Controller
class HotelController {

	@Autowired
	CategoryRepository categoryRepository;

	@GetMapping("/top")
	public String top(
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "top";
	}

	@GetMapping("/hotels")
	public String index(
			@RequestParam(name = "categoryName", defaultValue = "") String categoryName,
			Model model) {

		List<Category> hotelList = categoryRepository.findByName(categoryName);
		model.addAttribute("hotelList", hotelList);

		return "hotelList";
	}

	@PostMapping("/hotels")
	public String getCategory(
			@RequestParam(name = "categoryName", defaultValue = "") String categoryName,
			Model model) {
		model.addAttribute("categoryName", categoryName);

		return "hotelList";
	}

}
