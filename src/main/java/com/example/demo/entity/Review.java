package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reviews")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "hotel_id")
	private Integer hotelId;
	@Column(name = "plan_id")
	private Integer planId;
	@Column(name = "user_age")
	private Integer userAge;
	@Column(name = "stay_month")
	private Integer stayMonth;
	@Column(name = "stay_days")
	private Integer stayDays;
	private Integer point;
	private String review;

	public Review() {

	}

	public Review(Integer customerId, Integer hotelId, Integer planId, Integer userAge, Integer stayMonth,
			Integer stayDays, Integer point,
			String review) {
		this.customerId = customerId;
		this.hotelId = hotelId;
		this.planId = planId;
		this.userAge = userAge;
		this.stayMonth = stayMonth;
		this.stayDays = stayDays;
		this.point = point;
		this.review = review;
	}

	public Integer getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public Integer getHotelId() {
		return hotelId;
	}

	public Integer getPlanId() {
		return planId;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public void setStayMonth(Integer stayMonth) {
		this.stayMonth = stayMonth;
	}

	public void setStayDays(Integer stayDays) {
		this.stayDays = stayDays;
	}

	public void setPoint(Integer point) {
		this.point = point;
	}

	public void setReview(String review) {
		this.review = review;
	}

}
