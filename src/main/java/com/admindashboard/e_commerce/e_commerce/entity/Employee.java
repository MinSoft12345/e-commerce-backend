package com.admindashboard.e_commerce.e_commerce.entity;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "EC_EMPLOYEE")
public class Employee implements Serializable {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

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


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", referencedColumnName = "id")
    private User user;
}
