package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductVariantsRequest {

    private String userName;
    private String productId;
    private String variantName;
    private String variantDescription;
    private Double weight;
    private Integer height;
    private Integer width;
    private Integer length;
    private String barCode;
    private Integer quantity;
    private BigDecimal basePrice;
    private BigDecimal currPrice;
    private String variantStatus;
    private String imageId;
    private String colorCode;


    private BigDecimal discount;
    private Double productScore;
}
