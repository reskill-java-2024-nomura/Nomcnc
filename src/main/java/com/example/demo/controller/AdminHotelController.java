package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Hotel;
import com.example.demo.repository.HotelRepository;

@Controller
public class AdminHotelController {

	@Autowired
	HotelRepository hotelRepository;

	//宿一覧画面の表示
	@GetMapping("/admin/hotels")
	public String index(Model model) {
		List<Hotel> hotels = hotelRepository.findAll();
		model.addAttribute("hotels", hotels);
		return "adminShowHotels";
	}

	//宿新規登録画面の表示
	@GetMapping("/admin/hotels/add")
	public String create() {
		return "addHotel";
	}

	//宿新規登録処理
	@PostMapping("/admin/hotels/add")
	public String store(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "address", defaultValue = "") String address) {
		Hotel hotel = new Hotel(name, address);
		hotelRepository.save(hotel);
		return "redirect:/admin/hotels";
	}

	//宿詳細画面表示
	@GetMapping("/admin/hotels/{id}")
	public String show(@PathVariable("id") Integer id, Model model) {
		Hotel hotel = hotelRepository.findById(id).get();
		model.addAttribute(hotel);
		return "adminEditHotel";
	}

	//削除処理
	@PostMapping("/admin/hotels/{id}/delete")
	public String delete(
			@PathVariable("id") Integer id) {
		hotelRepository.deleteById(id);
		return "redirect:/admin/hotels";
	}

}
