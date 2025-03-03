package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
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
@RequestMapping("/api/country")
public class CountryController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addCountry(@RequestBody AddressDto addressDto) {

        if (addressDto.getCountry() == null || addressDto.getCountryCode() == null ) {
            return new ResponseEntity<>(new MessageResponse("Invalid request: Missing required fields.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }

        try {
            AddressDto response = addressService.addCountry(addressDto);
            return ResponseEntity.ok(response);
        } catch (NumberFormatException ex) {
            return new ResponseEntity<>(new MessageResponse("Invalid postal code format.", ResponseType.E), HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
