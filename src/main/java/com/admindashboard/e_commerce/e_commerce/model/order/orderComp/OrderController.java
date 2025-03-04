package com.admindashboard.e_commerce.e_commerce.model.order.orderComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto)
    {
        try{
            return ResponseEntity.ok(orderService.addOrder(orderDto));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new MessageResponse("Bad request or internal error."+ex.getMessage(), ResponseType.E), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<OrderDto>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
       PageRequest pageable = PageRequest.of(page, size);
       Page<OrderDto> orderList = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orderList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        Optional<OrderDto> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable String id, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrder(id, orderDto));
    }

//    @GetMapping("/search")
//    public ResponseEntity<List<OrderDto>> searchOrders(
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
//            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
//            @RequestParam(required = false) String orderPayType,
//            @RequestParam(required = false) String currentStatus) {
//        return ResponseEntity.ok(orderService.searchOrders(startDate, endDate, orderPayType, currentStatus));
//    }
//
//    @GetMapping("/history/{customerId}")
//    public ResponseEntity<List<OrderDto>> getOrderHistory(@PathVariable String customerId) {
//        return ResponseEntity.ok(orderService.getOrderHistoryForCustomer(customerId));
//    }

}
