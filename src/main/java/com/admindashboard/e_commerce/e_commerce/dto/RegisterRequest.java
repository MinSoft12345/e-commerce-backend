package com.admindashboard.e_commerce.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    private String password;
    private String userCode;
    private String adminName;
    private String tenantId;
    private String email;
    private String phoneNo;
    private String nidNo;
    private MultipartFile photo;
}
