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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            return ResponseEntity.ok(service.authenticate(request));
        } catch (Exception e) {
            return new ResponseEntity(new MessageResponse("Password and username mismatch", ResponseType.S), HttpStatus.OK);
        }
    }
}
