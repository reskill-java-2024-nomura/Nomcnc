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
import com.example.demo.repository.CustomerRepository;

@Controller
public class AdminCustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@GetMapping("/admin")
	public String top() {
		return "admin";
	}

	@GetMapping("/admin/customers")
	public String index(
			@RequestParam(name = "keyword", defaultValue = "") String keyword,
			@RequestParam(name = "keywordphone", defaultValue = "") String keywordphone,
			Model model) {
		List<Customer> customerList = null;

		if (keyword.length() > 0) {
			customerList = customerRepository.findByNameContaining(keyword);
		} else if (keywordphone.length() > 0) {
			customerList = customerRepository.findByTelContaining(keywordphone);
		} else {
			customerList = customerRepository.findAll();
		}
		model.addAttribute("keyword", keyword);
		model.addAttribute("keywordphone", keywordphone);
		model.addAttribute("customers", customerList);
		return "adminCustomer";
	}

	@GetMapping("/admin/customers/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "editAdminCustomer";
	}

	@PostMapping("/admin/customers/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "postal", defaultValue = "") String postal,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "birthday", defaultValue = "") LocalDate birthday,
			@RequestParam(name = "registerDate", defaultValue = "") LocalDate registerDate,
			@RequestParam(name = "withdrawDate", defaultValue = "") LocalDate withdrawDate,
			@RequestParam(name = "password", defaultValue = "") String password) {
		Customer customer = new Customer(id, name, postal, address, tel, email, birthday, registerDate, withdrawDate,
				password);
		customerRepository.save(customer);
		return "redirect:/admin/customers";
	}

	@PostMapping("/admin/customers/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		customerRepository.deleteById(id);
		return "redirect:/admin/customers";
	}

}
