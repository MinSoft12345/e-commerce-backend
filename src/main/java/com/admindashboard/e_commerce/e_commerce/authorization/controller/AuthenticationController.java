package com.admindashboard.e_commerce.e_commerce.authorization.controller;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.authorization.service.AuthenticationService;
import com.admindashboard.e_commerce.e_commerce.dto.AuthenticationRequest;
import com.admindashboard.e_commerce.e_commerce.dto.AuthenticationResponse;
import com.admindashboard.e_commerce.e_commerce.dto.RegisterRequest;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(
            RegisterRequest request
    ) {try {
        return ResponseEntity.ok(service.register(request));
    }catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Registration failed. Please check your input and try again.");
    }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity(new MessageResponse("Password and username mismatch", ResponseType.S), HttpStatus.UNAUTHORIZED);
        }
    }
}
