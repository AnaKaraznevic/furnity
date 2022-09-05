package com.furnity.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private int user_id;
	private String fname;
	private String lname;
	private String phone;
	private String email;
	private String pass;
	private String gender;
	private String street;
	private String city;
	private String country;
	private String postalcode;
	private String created;
	private String updated;
	
	
}
