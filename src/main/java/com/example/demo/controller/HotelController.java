package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Hotel;
import com.example.demo.entity.Plan;
import com.example.demo.entity.ViewReview;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.PlanRepository;
import com.example.demo.repository.ViewReviewRepository;

@Controller
class HotelController {

	@Autowired
	PlanRepository planRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	ViewReviewRepository viewReviewRepository;

	@GetMapping("/top")
	public String top(
			Model model) {

		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "top";
	}

	//ホテル一覧画面
	@GetMapping("/hotels/{categoryId}")
	public String getHotels(
			@PathVariable("categoryId") Integer categoryId,
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "keywordAddress", defaultValue = "") String keywordAddress,
			Model model) {

		Category category = categoryRepository.findById(categoryId).get();
		model.addAttribute("category", category);

		List<Hotel> hotels = null;
		//宿名検索
		if (keyword.length() > 0) {
			hotels = hotelRepository.findByCategoryIdAndNameContaining(categoryId, keyword);
		}
		//住所検索
		else if (keywordAddress.length() > 0) {
			hotels = hotelRepository.findByCategoryIdAndAddressContaining(categoryId, keywordAddress);
		}
		//宿名＆住所検索
		else if (keyword.length() > 0 && keywordAddress.length() > 0) {
			hotels = hotelRepository.findByCategoryIdAndAddressContainingAndNameContaining(categoryId, keywordAddress,
					keyword);
		}
		//カテゴリー内全検索
		else {
			hotels = hotelRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("hotels", hotels);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordAddress", keywordAddress);

		return "hotelList";
	}

	//ホテル詳細画面
	@GetMapping("/hotels/details/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		Hotel hotel = hotelRepository.findById(id).get();
		model.addAttribute("hotel", hotel);

		List<Plan> plans = planRepository.findByHotelId(id);
		model.addAttribute("plans", plans);

		List<ViewReview> reviews = viewReviewRepository.findByHotelId(id);
		model.addAttribute("reviews", reviews);

		return "hotelDetail";
	}

	@GetMapping("/reviews/{hotelId}/post")
	public String post(
			@PathVariable("hotelId") Integer hotelId,
			Model model) {
		Hotel hotel = hotelRepository.findById(hotelId).get();
		model.addAttribute("hotel", hotel);

		return "postReview";
	}

	@PostMapping("/reviews/{hotelId}/post")
	public String store(
			@PathVariable("hotelId") Integer hotelId,
			@RequestParam("planName") String planName,
			@RequestParam("userAge") Integer usrAge,
			@RequestParam("stayMonth") Integer stayMonth,
			@RequestParam("stayDays") Integer stayDays,
			@RequestParam("point") Integer point,
			@RequestParam("review") String review,
			Model model) {

		return "redirect:/hotels/details/{hotelId]";
	}

}
