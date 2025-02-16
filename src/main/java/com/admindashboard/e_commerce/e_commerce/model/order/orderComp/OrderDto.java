package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private String id;
    private String orderId;
    private Integer trackingNumber;
    private String shippedMethod;
    private String currentStatus;
    private LocalDateTime lastUpdatedDateTime;
    private String shippingAddress;
    private OffsetDateTime estimatedDeliveryDate;
    private String carrierDetails;
    private Boolean isDelivered;
}
