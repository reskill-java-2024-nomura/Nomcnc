package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	List<Customer> findByNameContaining(String keyword);

	List<Customer> findByTelContaining(String keywordphone);
}
