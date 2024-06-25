package com.example.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Account;

@Aspect
@Component
public class CheckLoginAspect {

	@Autowired
	Account account;

	@Around("execution(* com.example.demo.controller.ReservationController.*(..))")
	public Object checkLoginReservation(
			ProceedingJoinPoint jp) throws Throwable {
		if (account == null || account.getName() == null || account.getName().length() == 0) {
			return "redirect:/login?error=reservation";
		}
		return jp.proceed();
	}

	@Around("execution(* com.example.demo.controller.AccountController.mypageIndex(..))")
	public Object checkLoginMypage(
			ProceedingJoinPoint jp) throws Throwable {
		if (account == null || account.getName() == null || account.getName().length() == 0) {
			return "redirect:/login?error=mypage";
		}
		return jp.proceed();
	}
}
