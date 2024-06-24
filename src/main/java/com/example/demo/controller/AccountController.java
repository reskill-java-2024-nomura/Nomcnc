package com.example.demo.controller;

import java.time.LocalDate;
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
	CustomerRepository customerRepository;

	//ログイン
	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		List<Customer> customers = customerRepository.findByEmailAndPassword(email, password);
		if (customers.size() == 0) {
			return "redirect:/login";
		} else {
			account.setId(customers.get(0).getId());
			account.setName(email);
			account.setEmail(password);
			return "redirect:/top";
		}
	}

	//会員登録
	@GetMapping("/signin")
	public String showSignUp() {
		return "signUp";
	}

	@PostMapping("/signin")
	public String signUp(
			@RequestParam("name") String name,
			@RequestParam("postal") String postal,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("birthday") LocalDate birthday,
			@RequestParam("password") String password) {

		Customer customer = new Customer(name, postal, address, tel, email, birthday, password);
		customerRepository.save(customer);

		return "redirect:/signin";
	}

	//会員マイページ
	@GetMapping("/mypage")
	public String mypageIndex() {
		return "mypage";
	}

	//会員情報の変更

	@GetMapping("/mypage/customer/edit")
	public String update(
			Model model) {
		Customer customer = customerRepository.findById(account.getId()).get();
		model.addAttribute(customer);
		return "editCustomer";
	}

	//会員の退会
	@PostMapping("mypage/customer/delete")
	public String delete() {
		customerRepository.deleteById(account.getId());
		return "redirect:/";
	}
}
