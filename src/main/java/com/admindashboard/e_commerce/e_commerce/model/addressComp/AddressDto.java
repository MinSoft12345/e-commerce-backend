package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressDto {
    private String id;
    private String addressLine;
    private Integer postCode;
    private String state;
    private String city;
    private String country;
    private String divisionName;
    private String districtName;
    private String subDistrictName;
    private String divisionCode;
    private String districtCode;
    private String subDistrictCode;
    private String divisionId;
    private String districtId;
    private String subDistrictId;


}
