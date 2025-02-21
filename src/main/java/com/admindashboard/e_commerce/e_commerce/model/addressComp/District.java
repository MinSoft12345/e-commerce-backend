package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "EC_DISTRICT")
@Builder
public class District {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "district_name",unique = true)
    private String districtName;

    @Column(name = "district_code",unique = true)
    private String districtCode;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "division_id")
    private Division division;
}
