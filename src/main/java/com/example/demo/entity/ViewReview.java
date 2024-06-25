package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_reviews")
public class ViewReview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "hotel_id")
	private Integer hotelId;
	@Column(name = "hotel_name")
	private String hotelName;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "plan_name")
	private String planName;
	@Column(name = "user_age")
	private Integer userAge;
	@Column(name = "stay_month")
	private Integer stayMonth;
	@Column(name = "stay_days")
	private Integer stayDays;
	private Integer point;
	private String review;

	public Integer getId() {
		return id;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public String getHotelName() {
		return hotelName;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public String getPlanName() {
		return planName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public Integer getStayMonth() {
		return stayMonth;
	}

	public Integer getStayDays() {
		return stayDays;
	}

	public Integer getPoint() {
		return point;
	}

	public String getReview() {
		return review;
	}

}
