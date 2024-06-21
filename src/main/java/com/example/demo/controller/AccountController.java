package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class AccountController {
	//会員登録
	@GetMapping("/signin")
	public String showSignUp() {
		return "signUp";
	}

	@PostMapping("/signin")
	public String signUp() {
		return "signUp";
	}

	//ログイン
	@GetMapping({ "/", "/login" })
	public String index() {
		return "login";
	}

	@PostMapping("/login")
	public String login() {
		return "top";
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

	//会員マイページ(遷移まで)
	@GetMapping("/mypage")
	public String mypageIndex() {
		return "mypage";
	}
}
