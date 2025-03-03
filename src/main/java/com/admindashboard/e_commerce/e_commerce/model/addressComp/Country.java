package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "EC_COUNTRY")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "country_name",unique = true)
    private String countryName;

    @Column(name = "country_code",unique = true)
    private String countryCode;

}
