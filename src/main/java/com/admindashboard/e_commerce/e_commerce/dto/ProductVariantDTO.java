package com.admindashboard.e_commerce.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantDTO {
    private String id;
    private String productName;
    private String variantName;
    private BigDecimal variantPrice;
    private Integer stockAmount;
    private String variantStatus;
    private String createdAt;
}
