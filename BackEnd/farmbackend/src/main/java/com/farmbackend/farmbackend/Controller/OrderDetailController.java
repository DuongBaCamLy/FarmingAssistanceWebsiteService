package com.farmbackend.farmbackend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.OrderDetailsRequest;
import com.farmbackend.farmbackend.Entities.OrderDetail;
import com.farmbackend.farmbackend.Service.OrdersDetailService;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrdersDetailService orderDetailService;

    // Lấy tất cả OrderDetails
    @GetMapping
    public ResponseEntity<List<OrderDetail>> getAllOrderDetails() {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetail();
        return ResponseEntity.ok(orderDetails);
    }

    // Lấy OrderDetail theo ID
    @GetMapping("/{orderdetail_id}")
    public ResponseEntity<OrderDetail> getOrderDetailById(@PathVariable int orderdetail_id) {
        OrderDetail orderDetail = orderDetailService.getOrderDetailById(orderdetail_id);
        return ResponseEntity.ok(orderDetail);
    }
    @PostMapping("/product/{product_id}")
    public ResponseEntity<OrderDetail> createOrderDetail(
            @RequestBody OrderDetailsRequest params,
            @PathVariable int product_id) {
    
        // Lấy order_id từ request body
        int order_id = params.getOrder_id();
    
        // Gọi service với đủ 3 tham số
        OrderDetail savedOrderDetail = orderDetailService.createOrderDetail(params, order_id, product_id);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrderDetail);
    }
    

    
    // Cập nhật OrderDetail theo ID
    @PutMapping("/{orderdetail_id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable int orderdetail_id, @RequestBody OrderDetailsRequest params) {
        OrderDetail updatedOrderDetail = orderDetailService.updateOrderDetail(orderdetail_id, params);
        return ResponseEntity.ok(updatedOrderDetail);
    }

    // Xóa OrderDetail theo ID
    @DeleteMapping("/{orderdetail_id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable int orderdetail_id) {
        orderDetailService.deleteOrdersDetail(orderdetail_id);
        return ResponseEntity.noContent().build();
    }

    // Lấy danh sách OrderDetails theo Product ID
    @GetMapping("/product/{product_id}")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetailsByProductId(@PathVariable int product_id) {
        List<OrderDetail> orderDetails = orderDetailService.getAllOrderDetailByProduct_id(product_id);
        return ResponseEntity.ok(orderDetails);
    }
}
