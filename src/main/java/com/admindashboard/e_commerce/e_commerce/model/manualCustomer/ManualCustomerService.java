package com.admindashboard.e_commerce.e_commerce.model.manualCustomer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManualCustomerService {

    @Autowired
    private ManualCustomerRepository manualCustomerRepository;

    public ManualCustomerDto addManualCustomer(ManualCustomerDto manualCustomerDto)
    {
        var customer = ManualCustomer.builder()
                .name(manualCustomerDto.getName())
                .phoneNumber(manualCustomerDto.getPhoneNumber())
                .build();

        customer = manualCustomerRepository.save(customer);

        return ManualCustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .build();
    }
}
