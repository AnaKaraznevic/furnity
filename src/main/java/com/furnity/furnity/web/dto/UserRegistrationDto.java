package com.furnity.furnity.web.dto;


import com.furnity.furnity.model.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto {
    private String firstName;
    private String lastName;

    private Address address;
    private String email;
    private String password;

    public UserRegistrationDto( String firstName, String lastName, Address address, String email, String password ) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.password = password;
    }
}
