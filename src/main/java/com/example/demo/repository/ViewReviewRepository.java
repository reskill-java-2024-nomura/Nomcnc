package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ViewReview;

public interface ViewReviewRepository extends JpaRepository<ViewReview, Integer> {

	List<ViewReview> findByHotelIdOrderByIdAsc(Integer hotelId);

	List<ViewReview> findByCustomerId(Integer customerId);
}
