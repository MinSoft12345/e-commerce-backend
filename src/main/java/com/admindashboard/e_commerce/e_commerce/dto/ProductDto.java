package com.admindashboard.e_commerce.e_commerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String id;
    private String categoryName;
    private String productName;
    private Integer prodPrice;
    private Integer stockAmount;
    private String prodStatus;
    private Date createdAt;
}
