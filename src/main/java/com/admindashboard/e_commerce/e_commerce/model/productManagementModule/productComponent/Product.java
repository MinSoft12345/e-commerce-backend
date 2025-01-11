package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent;

import com.admindashboard.e_commerce.e_commerce.allenum.ProductStatus;
import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent.ProductType;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent.ProductVariants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EC_PRODUCT")
public class Product {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private ProductType category;

    @Enumerated(EnumType.STRING)
    @Column(name = "prod_status")
    private ProductStatus prodStatus;

    @Column(name = "base_price")
    private Integer basePrice;

    @Column(name = "vat")
    private Integer vat;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "length")
    private Integer length;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "prod_current_price")
    private Integer prodCurrPrice;

    @Column(name = "stock_amount")
    private Integer stockAmount;

    @Column(name = "bar_code")
    private String barcode;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "sku")
    private String sku;

    @Column(name = "score")
    private Integer score;

    @Column(name = "meta_tag_title")
    private String metaTagTitle;

    @Column(name = "meta_tag_description")
    private String metaTagDescription;

    @Column(name = "meta_tag_keyword")
    private String metaTagKeyword;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(name = "product_thumbnail_url")
    private String productThumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by", nullable = false)
    private User updatedBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariants> productVariantsList = new ArrayList<>();
}
