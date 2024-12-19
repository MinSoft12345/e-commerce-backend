package com.admindashboard.e_commerce.e_commerce.model.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "EC_PRODUCT_VARIANTS")
public class ProductVariants {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "product_size")
//    private ProductSize size;

    @Column(name = "product_weight")
    private Double weight;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_image_id")
//    private ProductImage productImage;

    @Column(name = "stock_keeping_unit", nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column
    private BigDecimal discount;

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
