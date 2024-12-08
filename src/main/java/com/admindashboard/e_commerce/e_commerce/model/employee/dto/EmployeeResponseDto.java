package com.admindashboard.e_commerce.e_commerce.model.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private String userName;
    private String fullName;
    private String communicationEmail;
    private String contactNumber;
    private String role;
    private String message;
}
