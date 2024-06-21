package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String postal;
	private String address;
	private String tel;
	private String email;
	private LocalDate birthday;
	@Column(name = "register_date")
	private LocalDate registerDate;
	@Column(name = "withdraw_date")
	private LocalDate withdrawDate;
	private String password;

	public Customer() {
	}

	public Customer(String name, String postal, String address, String tel, String email, LocalDate birthday,
			LocalDate registerDate, LocalDate withdrawDate, String password) {
    		this.name = name;
		this.postal = postal;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.withdrawDate = withdrawDate;
		this.password = password; 
}
	public Customer(Integer id, String name, String postal, String address, String tel, String email,
			LocalDate birthday,
			LocalDate registerDate, LocalDate withdrawDate, String password) {
		super();
		this.id = id;
		this.name = name;
		this.postal = postal;
		this.address = address;
		this.tel = tel;
		this.email = email;
		this.birthday = birthday;
		this.registerDate = registerDate;
		this.withdrawDate = withdrawDate;
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostal() {
		return postal;
	}

	public void setPostal(String postal) {
		this.postal = postal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(LocalDate registerDate) {
		this.registerDate = registerDate;
	}

	public LocalDate getWithdrawDate() {
		return withdrawDate;
	}

	public void setWithdrawDate(LocalDate withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
