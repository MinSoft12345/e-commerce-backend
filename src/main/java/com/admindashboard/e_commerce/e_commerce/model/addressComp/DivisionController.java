package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/api/division")
public class DivisionController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addDivision(@RequestBody AddressDto addressDto) {
        System.out.println("Received AddressDto: " + addressDto);

        if (addressDto.getDivisionName() == null || addressDto.getDivisionCode() == null || addressDto.getPostCode() == null) {
            return new ResponseEntity<>(new MessageResponse("Invalid request: Missing required fields.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }

        try {
            AddressDto response = addressService.addDivision(addressDto);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException ex) {
            return new ResponseEntity<>(new MessageResponse("Invalid postal code format.", ResponseType.E), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
