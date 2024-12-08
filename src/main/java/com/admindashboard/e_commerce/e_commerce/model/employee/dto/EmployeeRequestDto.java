package com.admindashboard.e_commerce.e_commerce.model.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDto {
    private String userName;
    private String password;
    private String fullName;
    private String communicationEmail;
    private String contactNumber;
    private String mobileNumber;
}
