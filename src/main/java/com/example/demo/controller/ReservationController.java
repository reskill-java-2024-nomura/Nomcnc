package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Plan;
import com.example.demo.entity.Reservation;
import com.example.demo.model.Account;
import com.example.demo.repository.PlanRepository;
import com.example.demo.repository.ReservationRepository;

@Controller
public class ReservationController {

	@Autowired
	Account account;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@GetMapping("reservation/input/{planId}")
	public String reserve(
			@PathVariable("planId") Integer planId,
			Model model) {

		Plan plan = planRepository.findById(planId).get();
		model.addAttribute("plan", plan);

		return "reservationInput";
	}

	@PostMapping("reservation/confirm/{planId}")
	public String confirm(
			@PathVariable("planId") Integer planId,
			@RequestParam("checkinDate") LocalDate checkinDate,
			@RequestParam("checkoutDate") LocalDate checkoutDate,
			@RequestParam("roomCount") Integer roomCount,
			@RequestParam("note") String note,
			Model model) {

		LocalDate reservationDate = LocalDate.now();
		Reservation reservation = new Reservation(account.getId(), planId, reservationDate, checkinDate, checkoutDate,
				roomCount, false, note);
		model.addAttribute("reservation", reservation);

		Plan plan = planRepository.findById(planId).get();
		model.addAttribute("plan", plan);

		return "reservationConfirm";
	}

	@PostMapping("/reserved/{planId}")
	public String store(
			@PathVariable("planId") Integer planId,
			@RequestParam("checkinDate") LocalDate checkinDate,
			@RequestParam("checkoutDate") LocalDate checkoutDate,
			@RequestParam("roomCount") Integer roomCount,
			@RequestParam("note") String note,
			Model model) {

		LocalDate reservationDate = LocalDate.now();
		Reservation reservation = new Reservation(account.getId(), planId, reservationDate, checkinDate, checkoutDate,
				roomCount, false, note);
		reservationRepository.save(reservation);

		Reservation newReservation = reservationRepository.findTopByOrderByIdDesc();
		model.addAttribute("newReservation", newReservation);

		return "reserved";
	}

}
