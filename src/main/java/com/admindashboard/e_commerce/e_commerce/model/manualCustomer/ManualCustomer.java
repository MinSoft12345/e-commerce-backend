package com.admindashboard.e_commerce.e_commerce.model.manualCustomer;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "EC_MANUAL_CUSTOMER")
@Builder
public class ManualCustomer {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    private String name;

    @Column(name = "phone_number",unique = true)
    private String phoneNumber;
}
