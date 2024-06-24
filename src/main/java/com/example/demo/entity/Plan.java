package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "plans")
public class Plan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "hotel_id")
	private Integer hotelId;
	private String name;
	private Integer price;
	@Column(name = "room_count")
	private Integer roomCount;
	private String note;

	public Plan() {
	}

	public Plan(Integer id, Integer hotelId, String name, Integer price, Integer roomCount, String note) {
		super();
		this.id = id;
		this.hotelId = hotelId;
		this.name = name;
		this.price = price;
		this.roomCount = roomCount;
		this.note = note;
	}

	public Plan(Integer id, String name, Integer price, Integer roomCount, String note) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.roomCount = roomCount;
		this.note = note;
	}

	public Integer getId() {
		return id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public String getName() {
		return name;
	}

	public Integer getPrice() {
		return price;
	}

	public Integer getRoomCount() {
		return roomCount;
	}

	public String getNote() {
		return note;
	}

}
