package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?>addAddress(@RequestBody AddressDto addressDto)
    {
        try{
            return ResponseEntity.ok(addressService.addAddress(addressDto));
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("Bad request or internal error.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-subDistricts/{districtName}")
    public ResponseEntity<?> subDistrictList(@PathVariable String districtName)
    {
        try {
            return ResponseEntity.ok(addressService.findSubDistrictList(districtName));
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-districts/{divisionName}")
    public ResponseEntity<?> districtList(@PathVariable String divisionName)
    {
        try {
            return ResponseEntity.ok(addressService.findDistrictList(divisionName));
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/find-divisions/{countryName}")
    public ResponseEntity<?> divisionList(@PathVariable String countryName)
    {
        try {
            return ResponseEntity.ok(addressService.findDivisionList(countryName));
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }
}
