package com.furnity.furnity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_no", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // foreign key !!!! - return later
    @Column(name = "address_id", nullable = false)
    private String addressId;

    @Column(name = "password", nullable = false)
    private String password;
//
//    @Column(name = "created_at")
//    private Date createdAt;
//
//    @Column(name = "updated_at")
//    private Date updatedAt;


}
