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
public class ProductRequest {
        private String userName;
        private String productName;
        private String description;
        private String productThumbnailId;
        private String categoryId;
        private ProductStatus prodStatus;
        private Integer basePrice;
        private Boolean isActive;
        private String sku;
        private String metaTagTitle;
        private String metaTagDescription;
        private String metaTagKeyword;



        private String createdBy;
        private String updatedBy;
        private String tenantId;
        private Integer score;
}
