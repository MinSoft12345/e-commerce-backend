package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@Controller
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addDistrict(@RequestBody AddressDto addressDto) {
        System.out.println(addressDto);
        try {
            AddressDto response = addressService.addDistrict(addressDto);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(
                    new MessageResponse("Internal error or bad request: " + ex.getMessage(), ResponseType.E),
                    HttpStatus.BAD_REQUEST
            );
        }
    }





}
