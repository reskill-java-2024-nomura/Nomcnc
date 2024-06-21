package com.example.demo.entity;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hotels")
public class Hotel {

	//ターゲット
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "category_id")
	private Integer categoryId;
	private String name;
	private String address;
	@Column(name = "checkin_time")
	private LocalTime checkinTime;
	@Column(name = "checkout_time")
	private LocalTime checkoutTime;

	//コンストラクタ
	public Hotel() {
	}

	public Hotel(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public LocalTime getCheckinTime() {
		return checkinTime;
	}

	public LocalTime getCheckoutTime() {
		return checkoutTime;
	}

}
