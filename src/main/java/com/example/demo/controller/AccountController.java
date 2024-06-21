package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class AccountController {
	//会員登録
	@GetMapping("/signin")
	public String showSignUp() {
		return "login";
	}

	@PostMapping("/signin")
	public String login() {
		return "signUp";
	}

	//ログイン
	//会員情報の変更
	//会員の退会
	//会員マイページ(遷移まで)

}
