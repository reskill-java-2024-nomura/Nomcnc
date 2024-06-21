package com.example.demo.controller;

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
	public String index(Model model) {
		List<Customer> customerList = customerRepository.findAll();
		model.addAttribute("customers", customerList);
		return "adminCustomer";
	}

	@GetMapping("/admin/customers/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		Customer customer = customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "editUser";
	}

	@PostMapping("/admin/customers/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "postal", defaultValue = "") String postal,
			@RequestParam(name = "address", defaultValue = "") String address,
			@RequestParam(name = "tel", defaultValue = "") String tel,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "birthday", defaultValue = "") String birthday,
			@RequestParam(name = "registerDate", defaultValue = "") String registerDate,
			@RequestParam(name = "withdrawDate", defaultValue = "") String withdrawDate,
			@RequestParam(name = "password", defaultValue = "") String password) {
		Customer customer = new Customer(id, name, postal, address, tel, email, birthday, registerDate, withdrawDate,
				password);
		customerRepository.save(customer);
		return "redirect:/users";
	}
}
