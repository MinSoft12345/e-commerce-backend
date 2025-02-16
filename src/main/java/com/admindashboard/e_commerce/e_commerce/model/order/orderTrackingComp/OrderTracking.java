package com.admindashboard.e_commerce.e_commerce.model.order.orderTrackingComp;

import com.admindashboard.e_commerce.e_commerce.model.order.orderComp.Order;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "EC_ORDER_TRACKING")
public class OrderTracking {

    @Id
    @GenericGenerator(name = "idGen", strategy = "uuid.hex")
    @GeneratedValue(generator = "idGen")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "tracking_number")
    private Integer trackingNumber;

    @Column(name = "shipped_method")
    private String shippedMethod;

    @Column(name = "current_status")
    private String currentStatus;

    @UpdateTimestamp
    @Column(name = "last_updated_date_time")
    private LocalDateTime lastUpdatedDateTime;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "estimated_delivery_date")
    private OffsetDateTime EstimatedDeliveryDate;

    @Column(name = "carrier_details")
    private String carrierDetails;

    @Column(name = "is_delivered")
    private Boolean isDelivered;


}
