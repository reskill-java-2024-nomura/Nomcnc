package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class AccountController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;
	@Autowired
	CustomerRepository cutstomerRepository;

	//ログイン
	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("name") String name,
			Model model) {
		List<Customer> customers = cutstomerRepository.findByNameAndEmail(name, email);
		account.setId(customers.get(0).getId());
		account.setName(name);
		account.setEmail(email);
		if (customers.size() != 0) {
			return "top";
		} else {
			return "redirect:/login";
		}
	}

	//会員登録
	@GetMapping("/signin")
	public String showSignUp(
			@RequestParam("name") String name,
			@RequestParam("postal") String postal,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("birthday") String birthday,
			@RequestParam("registerDate") String registerDate,
			@RequestParam("withdrawDate") String withdrawDate,
			@RequestParam("password") String password) {
		Customer customer = new Customer(name, postal, address, tel, email, birthday, registerDate, withdrawDate,
				password);
		return "signUp";
	}

	@PostMapping("/signin")
	public String signUp() {
		return "redirect:/signin";
	}

	//会員マイページ
	@GetMapping("/mypage")
	public String mypageIndex() {
		return "mypage";
	}

	//会員情報の変更

	@PostMapping("mypage/customer/edit")
	public String update() {
		return "redirect:/mypage";
	}

	//会員の退会
	@PostMapping("mypage/customer/delete")
	public String delete() {
		return "redirect:/";
	}
}
