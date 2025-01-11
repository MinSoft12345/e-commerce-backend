package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EC_PRODUCT_IMAGE")
public class ProductImage {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_thumbnail_image")
    private Boolean isThumbnailImage = false;

    @Column(name = "poster")
    private String poster;

}
