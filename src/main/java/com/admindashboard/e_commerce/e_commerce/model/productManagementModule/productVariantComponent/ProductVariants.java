package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productVariantComponent;

import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productComponent.Product;
import com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productImageComponent.ProductImage;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EC_PRODUCT_VARIANTS")
public class ProductVariants {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(name = "product_variant_name", nullable = false)
    private String variantName;

    @Column(name = "product_variant_description", nullable = false)
    private String variantDescription;


    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "product_size")
//    private ProductSize size;

    @Column(name = "product_weight")
    private Double weight;

    @Column(name = "product_height")
    private Integer height;

    @Column(name = "product_width")
    private Integer width;

    @Column(name = "product_length")
    private Integer length;

    @Column(name = "product_bar_code")
    private String barCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_image_id")
    private ProductImage productImage;

    @Column(name = "product_quantity", nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Column(nullable = false)
    private BigDecimal currPrice;

    @Column(name = "variant_status", nullable = false)
    private String variantStatus;

//    @Column
//    private BigDecimal discount;

    @Column(name = "sell_count")
    private Integer sellCount = 0;

    @Column(name = "product_score")
    private Double productScore = 0.0;

    @Column(name = "color_code")
    private String colorCode;

    @Column(name = "review_count")
    private Integer reviewCount = 0;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
