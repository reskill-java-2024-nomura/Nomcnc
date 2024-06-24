package com.example.demo.controller;

import java.time.LocalTime;
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
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.PlanRepository;

@Controller
public class AdminHotelController {

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	PlanRepository planrepository;

	@Autowired
	CategoryRepository categoryRepository;

	//宿一覧画面の表示
	@GetMapping("/admin/hotels")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "keywordAddress", defaultValue = "") String keywordAddress,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {
		List<Hotel> hotels = null;
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		//宿名検索
		if (keyword.length() > 0) {
			hotels = hotelRepository.findByNameContaining(keyword);
		}
		//住所検索
		else if (keywordAddress.length() > 0) {
			hotels = hotelRepository.findByAddressContaining(keywordAddress);
		}
		//カテゴリー検索
		else if (categoryId != null) {
			hotels = hotelRepository.findByCategoryId(categoryId);
		}
		//全検索
		else {
			hotels = hotelRepository.findAll();
		}
		model.addAttribute("hotels", hotels);
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordAddress", keywordAddress);
		return "adminShowHotels";
	}

	//宿新規登録画面の表示
	@GetMapping("/admin/hotels/add")
	public String create(Model model) {
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		return "addHotel";
	}

	//宿新規登録処理
	@PostMapping("/admin/hotels/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "checkinTime", defaultValue = "") LocalTime checkinTime,
			@RequestParam(name = "checkoutTime", defaultValue = "") LocalTime checkoutTime) {
		Hotel hotel = new Hotel(categoryId, name, address, checkinTime, checkoutTime);
		hotelRepository.save(hotel);
		return "redirect:/admin/hotels";
	}

	//宿詳細画面表示
	@GetMapping("/admin/hotels/{id}")
	public String show(
			@PathVariable("id") Integer id,
			Model model) {
		//ホテル詳細情報取得
		Hotel hotel = hotelRepository.findById(id).get();
		model.addAttribute("hotel", hotel);
		//プラン一覧情報取得
		List<Plan> plans = planrepository.findByHotelId(id);
		model.addAttribute("plans", plans);
		return "adminShowHotel";
	}

	//宿削除処理
	@PostMapping("/admin/hotels/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id) {
		hotelRepository.deleteById(id);
		return "redirect:/admin/hotels";
	}

	//宿編集画面の表示
	@GetMapping("/admin/hotels/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		Hotel hotel = hotelRepository.findById(id).get();
		List<Category> categories = categoryRepository.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute(hotel);
		return "adminEditHotel";
	}

	//宿更新処理
	@PostMapping("/admin/hotels/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "checkinTime", defaultValue = "") LocalTime checkinTime,
			@RequestParam(name = "checkoutTime", defaultValue = "") LocalTime checkoutTime) {
		Hotel hotel = new Hotel(id, categoryId, name, address, checkinTime, checkoutTime);
		hotelRepository.save(hotel);
		return "redirect:/admin/hotels/{id}";
	}

}
