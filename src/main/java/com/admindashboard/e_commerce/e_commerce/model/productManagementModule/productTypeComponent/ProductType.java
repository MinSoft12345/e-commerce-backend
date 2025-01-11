package com.admindashboard.e_commerce.e_commerce.model.productManagementModule.productTypeComponent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EC_PRODUCT_TYPE")
public class ProductType {
    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @Column(nullable = false,unique = true)
    private String typeName;

    @Column(name = "description")
    private String description;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;
}
