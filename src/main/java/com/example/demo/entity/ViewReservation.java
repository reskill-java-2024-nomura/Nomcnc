package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_resevations")
public class ViewReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "checkin_date")
	private LocalDate checkinDate;
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	private Integer payment;
	private String planName;

	public Integer getId() {
		return id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public LocalDate getCheckinDate() {
		return checkinDate;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public Integer getPayment() {
		return payment;
	}

	public String getPlanName() {
		return planName;
	}

}
