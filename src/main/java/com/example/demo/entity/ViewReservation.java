package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "view_reservations")
public class ViewReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "customer_id")
	private Integer customerId;
	@Column(name = "reservation_date")
	private LocalDate reservationDate;
	@Column(name = "checkin_date")
	private LocalDate checkinDate;
	@Column(name = "checkout_date")
	private LocalDate checkoutDate;
	@Column(name = "room_count")
	private Integer roomCount;
	private Integer payment;
	@Column(name = "plan_name")
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

	public Integer getRoomCount() {
		return roomCount;
	}

	public Integer getPayment() {
		return payment;
	}

	public String getPlanName() {
		return planName;
	}

	public LocalDate getReservationDate() {
		return reservationDate;
	}

}
