package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.dto.OrderStatus;
import com.admindashboard.e_commerce.e_commerce.dto.ShippingMethod;
import com.admindashboard.e_commerce.e_commerce.model.order.orderItemComp.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "EC_ORDER")
public class Order {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    // later have to add with the customer entity
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "customer_id", nullable = false)
//    private Customer customer;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShippingMethod shippingMethod;

    // later will add this Address
//    @Embedded
//    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal shippingFee;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal taxAmount;

    @Column(precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(length = 1000)
    private String adminNotes;

    @Column(length = 1000)
    private String customerNotes;


    // later will add
//    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
//    private OrderTracking orderTracking;

    @PrePersist
    protected void onCreate() {
        orderDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        calculateTotalAmount();
    }

    private void calculateTotalAmount() {
        this.totalAmount = this.subtotal
                .add(this.shippingFee)
                .add(this.taxAmount)
                .subtract(this.discountAmount != null ? this.discountAmount : BigDecimal.ZERO);
    }

}
