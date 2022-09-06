package com.furnity.furnity.web.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;

    private Long addressId;
    private String email;
    private String password;

    public UserRegistrationDto(String firstName, String lastName, Long addressId, String email, String password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = null;
        this.email = email;
        this.password = password;
    }

}
