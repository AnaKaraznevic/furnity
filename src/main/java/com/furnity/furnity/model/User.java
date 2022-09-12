package com.furnity.furnity.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {

	
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String secondName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "id")
    private Address address;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    
  //  @OneToMany(mappedBy="userId") 
  //  private Set<Item> items;
    
    @ManyToMany
    private Set<Role> roles;



	/*
	 * @Enumerated(EnumType.STRING)
	 * 
	 * @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	 * 
	 * @CollectionTable(name = "roles") private Set<Role> roles = new HashSet<>();
	 */
    
	/*
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * return getRoles(); }
	 */


}
