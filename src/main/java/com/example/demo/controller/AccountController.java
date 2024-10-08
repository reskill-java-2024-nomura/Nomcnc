package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Customer;
import com.example.demo.entity.Review;
import com.example.demo.entity.ViewReview;
import com.example.demo.model.Account;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ReviewRepository;
import com.example.demo.repository.ViewReviewRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class AccountController {
	@Autowired
	HttpSession session;

	@Autowired
	Account account;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ReviewRepository reviewRepository;

	@Autowired
	ViewReviewRepository viewReviewRepository;

	//ログイン
	@GetMapping({ "/", "/login" })
	public String index(
			@RequestParam(name = "error", defaultValue = "") String error,
			Model model) {
		if (error.equals("reservation")) {
			String msg = "予約するにはログインしてください";
			model.addAttribute("msg", msg);
		}
		if (error.equals("mypage")) {
			String msg = "マイページを表示するにはログインしてください";
			model.addAttribute("msg", msg);
		}
		if (error.equals("comment")) {
			String msg = "コメントを投稿するにはログインしてください";
			model.addAttribute("msg", msg);
		}
		return "login";

	}

	@PostMapping("/login")
	public String login(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			Model model) {
		List<Customer> customers = customerRepository.findByEmailAndPassword(email, password);

		if (customers.size() == 0) {
			String msg = "メールアドレスとパスワードの組み合わせが不正です";
			model.addAttribute("msg", msg);
			return "login";
		} else {
			account.setId(customers.get(0).getId());
			account.setName(customers.get(0).getName());
			account.setEmail(customers.get(0).getEmail());
			return "redirect:/top";
		}
	}

	//ログアウト
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

	//会員登録
	@GetMapping("/signin")
	public String showSignUp() {
		return "signUp";
	}

	@PostMapping("/signin")
	public String signUp(
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "postal", defaultValue = "") String postal,
			@RequestParam(name = "address", defaultValue = "0000") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "birthday", defaultValue = "") LocalDate birthday,
			@RequestParam(name = "password", defaultValue = "himitu") String password) {
		Customer customer = new Customer(name, postal, address, tel, email, birthday, password);
		customerRepository.save(customer);
		return "redirect:/login";
	}

	//会員マイページ

	@GetMapping("/mypage")
	public String mypageIndex(
			Model model) {
		Customer customer = customerRepository.findById(account.getId()).get();
		model.addAttribute(customer);
		return "mypage";
	}

	//会員情報の変更

	@PostMapping("/mypage/customer/edit")
	public String edit(
			@RequestParam("id") Integer id,
			@RequestParam("name") String name,
			@RequestParam("postal") String postal,
			@RequestParam("address") String address,
			@RequestParam("tel") String tel,
			@RequestParam("email") String email,
			@RequestParam("birthday") LocalDate birthday,
			@RequestParam("password") String password) {
		Customer customer = customerRepository.findById(id).get();
		customer.setName(name);
		customer.setPostal(postal);
		customer.setAddress(address);
		customer.setTel(tel);
		customer.setEmail(email);
		customer.setBirthday(birthday);
		customer.setPassword(password);
		customerRepository.save(customer);
		account.setName(name);
		account.setEmail(email);
		return "redirect:/top";
	}

	//会員の退会
	@PostMapping("mypage/customer/delete")
	public String delete() {
		customerRepository.deleteById(account.getId());
		return "redirect:/";
	}

	//口コミの一覧
	@GetMapping("/reviews")
	public String myReview(
			Model model) {
		List<ViewReview> reviews = viewReviewRepository.findByCustomerIdOrderByIdAsc(account.getId());
		model.addAttribute("reviews", reviews);

		return "myReview";
	}

	//口コミの編集
	@GetMapping("/reviews/{id}/edit")
	public String editReview(
			@PathVariable("id") Integer id,
			Model model) {
		ViewReview review = viewReviewRepository.findById(id).get();
		model.addAttribute("review", review);

		return "editReview";
	}

	//口コミ更新
	@PostMapping("/reviews/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam("userAge") Integer userAge,
			@RequestParam("stayMonth") Integer stayMonth,
			@RequestParam("stayDays") Integer stayDays,
			@RequestParam("point") Integer point,
			@RequestParam("review") String review) {

		Review newReview = reviewRepository.findById(id).get();
		newReview.setUserAge(userAge);
		newReview.setStayMonth(stayMonth);
		newReview.setStayDays(stayDays);
		newReview.setPoint(point);
		newReview.setReview(review);

		reviewRepository.save(newReview);

		return "redirect:/reviews";
	}

	//口コミの削除
	@PostMapping("/reviews/{id}/delete")
	public String deleteReview(
			@PathVariable("id") Integer id) {
		reviewRepository.deleteById(id);
		return "redirect:/reviews";
	}
}
