package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ViewReservation;

public interface ViewReservationRepository extends JpaRepository<ViewReservation, Integer> {
	List<ViewReservation> findByCustomerId(Integer customerId);

	List<ViewReservation> findByCustomerIdOrderByIdAsc(Integer customerId);
}
