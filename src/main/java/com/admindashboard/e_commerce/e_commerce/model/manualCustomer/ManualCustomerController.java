package com.admindashboard.e_commerce.e_commerce.model.manualCustomer;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.model.addressComp.AddressDto;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
@RequestMapping("/api/manual-customer")
public class ManualCustomerController {

    @Autowired
    private ManualCustomerService manualCustomerService;

    @PostMapping("/add")
    public ResponseEntity<?> addManualCustomer(@RequestBody ManualCustomerDto manualCustomerDto)
    {
        try{
            return ResponseEntity.ok(manualCustomerService.addManualCustomer(manualCustomerDto));
        }catch(Exception ex){
            return new ResponseEntity<>(new MessageResponse("Some internal error or bad request.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }
}
