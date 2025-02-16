package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressDto addAddress(AddressDto addressDto)
    {
        var address = Address.builder()
                .addressLine(addressDto.getAddressLine())
                .state(addressDto.getState())
                .postCode(addressDto.getPostCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        address = addressRepository.save(address);

        return AddressDto.builder()
                .id(address.getId())
                .addressLine(address.getAddressLine())
                .state(address.getState())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .country(address.getCountry())
                .build();
    }
}
