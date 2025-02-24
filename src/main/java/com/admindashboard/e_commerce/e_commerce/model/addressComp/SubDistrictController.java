package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@RequestMapping("/api/sub-district")
public class SubDistrictController {

    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public ResponseEntity<?>addSubDistrict(@RequestBody AddressDto addressDto)
    {
        try {
            return ResponseEntity.ok(addressService.addSubDistrict(addressDto));
        }catch (Exception ex){
            return new ResponseEntity<>(new MessageResponse("Internal error or bad request.", ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }
}
