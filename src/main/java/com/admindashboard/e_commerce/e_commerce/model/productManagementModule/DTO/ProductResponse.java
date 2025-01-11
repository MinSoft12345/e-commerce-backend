package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO;

import com.admindashboard.e_commerce.e_commerce.allenum.ProductStatus;
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
public class ProductResponse {
        private String productId;
        private String productName;
        private String description;
        private String thumbNailUrl;
        private String categoryName;
        private ProductStatus prodStatus;
        private Integer basePrice;
        private Integer vat;
        private Integer discount;
        private Integer width;
        private Integer height;
        private Integer length;
        private Integer weight;
        private Integer prodCurrPrice;
        private Integer stockAmount;
        private String barcode;
        private String sku;
        private Integer score;
        private String metaTagTitle;
        private String metaTagDescription;
        private String metaTagKeyword;
        private Boolean isActive;
        private String createdBy;
        private String updatedBy;
}
