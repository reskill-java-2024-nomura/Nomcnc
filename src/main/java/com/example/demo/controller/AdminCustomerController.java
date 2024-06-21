package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
