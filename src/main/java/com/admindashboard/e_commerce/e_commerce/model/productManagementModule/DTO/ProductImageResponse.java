package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.DTO;

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
public class ProductImageResponse {
    private String imageName;
    private String imageUrl;
    private Boolean isThumbnailImage;
    private String imageId;
    private String  poster;
}
