package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.dto.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    List<Order> findByManualCustomerId(String manualCustomerId);

    List<Order> findByStatus(OrderStatus status);

    @Query("SELECT o FROM Order o")  
    Page<Order> findAllOrders(Pageable pageable);

}
