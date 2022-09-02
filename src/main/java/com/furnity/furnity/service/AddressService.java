package com.furnity.furnity.service;

import com.furnity.furnity.exception.AddressNotFoundException;
import com.furnity.furnity.exception.UserNotFoundException;
import com.furnity.furnity.model.Address;
import com.furnity.furnity.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddressService {

    private final AddressRepository addressRepository;

    // Constructor
    @Autowired
    public AddressService( AddressRepository addressRepository ) {
        this.addressRepository = addressRepository;
    }

    public Address saveNewAddress(Address newAddress ) {
        return addressRepository.save(newAddress);
    }

    public List<Address> findAllAddresses() {
        return addressRepository.findAll();
    }

    public Address findAddressById(Long id){
        return addressRepository.findAddressById(id)
                .orElseThrow(() -> new UserNotFoundException("Address by id " + id + " was not found in Data Base" ));
    }

    public void deleteAddressById(Long id){
        addressRepository.deleteAddressById(id);
    }

    public Address updateAddress(Address addressInfo){
        Address address = addressRepository.findAddressById(addressInfo.getId())
                .orElseThrow(() -> new AddressNotFoundException("Address by id " + addressInfo.getId() +
                        " was not found in DataBase" ));

        address.setStreet(addressInfo.getStreet());
        address.setTown(addressInfo.getTown());
        address.setCountry(addressInfo.getCountry());


        Address updatedAddress = addressRepository.save(address);

        return addressRepository.save(updatedAddress);
    }
}
