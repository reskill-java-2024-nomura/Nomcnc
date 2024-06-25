package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Plan;
import com.example.demo.entity.Reservation;
import com.example.demo.entity.ViewReservation;
import com.example.demo.model.Account;
import com.example.demo.repository.PlanRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.ViewReservationRepository;

@Controller
public class ReservationController {

	@Autowired
	Account account;

	@Autowired
	PlanRepository planRepository;

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ViewReservationRepository viewReservationRepository;

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

		//roomCountによるエラー出力
		if (roomCount > plan.getRoomCount()) {
			model.addAttribute("error", plan.getRoomCount() + 1 + "部屋以上予約できません。");
			return "reservationInput";
		}
		//チェックインとチェックアウトによるエラー出力
		else if (checkinDate.compareTo(checkoutDate) > 0) {
			model.addAttribute("error", "チェックアウト日がチェックイン日より前です。");
			return "reservationInput";
		}
		//今日の日付より過去の場合のエラー出力
		else if (checkinDate.compareTo(reservationDate) < 0 || checkoutDate.compareTo(reservationDate) < 0) {
			model.addAttribute("error", "選択した日付が過去の日付です。");
			return "reservationInput";
		}
		//会員数が５件以上のエラー出力
		List<Reservation> reservations = reservationRepository.findByCustomerId(account.getId());
		if (reservations.size() > 5) {
			model.addAttribute("error", "プラン予約可能数は最大５件までです。");
			return "reservedList";
		}
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

	@GetMapping("/reservations")
	public String getReserved(
			Model model) {
		List<ViewReservation> reservations = viewReservationRepository.findByCustomerId(account.getId());
		model.addAttribute("reservations", reservations);

		return "reservedList";
	}

}
