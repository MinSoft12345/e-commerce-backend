package com.admindashboard.e_commerce.e_commerce.authorization.service;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.authorization.jwt.JwtService;
import com.admindashboard.e_commerce.e_commerce.dto.AuthenticationRequest;
import com.admindashboard.e_commerce.e_commerce.dto.AuthenticationResponse;
import com.admindashboard.e_commerce.e_commerce.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        String photoPath = null;

//        if (request.getPhoto() != null && !request.getPhoto().isEmpty()) {
//            try {
//                String uploadDir = "uploads/photos/";
//                File directory = new File(uploadDir);
//                if (!directory.exists()) {
//                    directory.mkdirs();
//                }
//                String fileName = UUID.randomUUID().toString() + "_" + request.getPhoto().getOriginalFilename();
//                File file = new File(uploadDir + fileName);
//                request.getPhoto().transferTo(file);
//                photoPath = uploadDir + fileName;
//            } catch (IOException e) {
//                throw new RuntimeException("Failed to store photo", e);
//            }
//        }

        var user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .tenantId(request.getTenantId())
                .adminName(request.getAdminName())
                .email(request.getEmail())
                .phoneNo(request.getPhoneNo())
                .nidNo(request.getNidNo())
                .photoPath(photoPath)
                .createdDate(new Date())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
