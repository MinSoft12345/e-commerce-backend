package com.admindashboard.e_commerce.e_commerce.utils;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.util.Date;

@Data
public class UserBase {
    @Column(name = "FULL_NAME", length = 128, nullable = false)
    private String fullName;

    @Column(name = "COMMUNICATION_EMAIL", length = 128)
    private String communicationEmail;

    @Column(name = "CONTACT_NUMBER", length = 16)
    private String contactNumber;

    @Column(name = "MOBILE_NUMBER", length = 16)
    private String mobileNumber;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
