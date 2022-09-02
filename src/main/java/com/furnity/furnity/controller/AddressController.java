package com.furnity.furnity.controller;

import com.furnity.furnity.model.Address;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")  // ???
@RequestMapping("/api/v1/")
public class AddressController {

    private final AddressService addressService;

    // Constructor
    public AddressController( AddressService addressService ) {
        this.addressService = addressService;
    }

    @GetMapping("/address")
    public ResponseEntity<List<Address>> getAllAdresses(){
        List<Address> allAddresses = addressService.findAllAddresses();
        return new ResponseEntity<>(allAddresses, HttpStatus.OK);
    }

    @GetMapping("/address/{id}")
    public ResponseEntity<Address> geAddressById(@PathVariable("id") Long id){
        Address addressToFindById = addressService.findAddressById(id);
        return new ResponseEntity<>(addressToFindById, HttpStatus.OK);
    }

    @PostMapping("/address")
    public ResponseEntity<Address> addNewAddress(@RequestBody Address address){
        Address newAddress = addressService.saveNewAddress(address);
        return new ResponseEntity<>(newAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<?> deleteAddressById( @PathVariable("id") Long id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/address/")
    public ResponseEntity<Address> updateAddress(@RequestBody Address addressInfo){
        Address updateAddress = addressService.updateAddress(addressInfo);
        return new ResponseEntity<>(updateAddress, HttpStatus.OK);
    }
}

