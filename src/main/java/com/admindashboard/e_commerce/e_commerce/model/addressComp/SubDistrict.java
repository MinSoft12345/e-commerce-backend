package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "EC_SUB_DISTRICT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubDistrict {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "sub_district_name",unique = true)
    private String subDistrictName;

    @Column(name = "sub_district_code",unique = true)
    private String subDistrictCode;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id")
    private District district;


}
