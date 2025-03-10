package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "EC_DIVISION")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Division {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "division_name",unique = true)
    private String divisionName;

    @Column(name = "division_code",unique = true)
    private String divisionCode;

//    @Column(name = "postal_code",unique = true)
//    private Long postalCode;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;

}
