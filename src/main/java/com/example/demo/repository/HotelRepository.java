package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByCategoryId(Integer categoryId);

	List<Hotel> findByNameContaining(String keyword);

	List<Hotel> findByAddressContaining(String keywordAddress);

	List<Hotel> findByCategoryIdAndNameContaining(Integer categoryId, String keyword);

	List<Hotel> findByCategoryIdAndAddressContaining(Integer categoryId, String keywordAddress);
}
