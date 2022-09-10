package com.furnity.furnity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.furnity.furnity.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	@Id
	@Column(name = "id", unique = true, nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "street_name", nullable = false)
	private String street_name;

	@Column(name = "street_number")
	private String streetNumber;

	@Column(name = "flat_number")
	private String flatNumber;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "country", nullable = false)
	@Enumerated(EnumType.STRING)
	private Country country;

}
