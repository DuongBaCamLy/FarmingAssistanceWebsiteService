package com.farmbackend.farmbackend.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.DTO.OrderDetailsRequest;
import com.farmbackend.farmbackend.DTO.OrderDetailsResponse;
import com.farmbackend.farmbackend.Entities.OrderDetail;
import com.farmbackend.farmbackend.Entities.Orders;
import com.farmbackend.farmbackend.Entities.Product;
import com.farmbackend.farmbackend.Repository.OrderDetailsRepository;
import com.farmbackend.farmbackend.Repository.OrderRepository;
import com.farmbackend.farmbackend.Repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdersDetailService {

    @Autowired
    private OrderDetailsRepository orderdetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository ordersRepository;
    // Lấy tất cả OrderDetails
    public List<OrderDetail> getAllOrderDetail() {
        return orderdetailRepository.findAll();
    }
   // Lấy OrderDetail theo ID
   public OrderDetail getOrderDetailById(int orderdetail_id) {
    return orderdetailRepository.findById(orderdetail_id)
            .orElseThrow(() -> new IllegalArgumentException("OrderDetail không tồn tại"));
}

@Transactional
public OrderDetail createOrderDetail(OrderDetailsRequest params, int order_id, int product_id) {
    Orders order = getOrderById(order_id);
    Product product = getProductById(product_id);

    OrderDetail orderDetail = params.getOrderdetail();
    if (orderDetail == null) {
        orderDetail = new OrderDetail();
    }

    // Thiết lập các giá trị cần thiết
    orderDetail.setOrder(order);
    orderDetail.setProduct(product);
    orderDetail.setQuantity(orderDetail.getQuantity());
    orderDetail.setPrice(orderDetail.getPrice());
    orderDetail.setCreatedate(new Date());

    // Xóa dòng này vì subtotal được tính tự động
    // orderDetail.setSubtotal(orderDetail.getSubtotal());

    return orderdetailRepository.save(orderDetail);
}


    // Xóa OrderDetail theo ID
    public void deleteOrdersDetail(int orderdetail_id) {
        orderdetailRepository.deleteById(orderdetail_id);
    }
    @Transactional
public OrderDetail updateOrderDetail(int orderdetail_id, OrderDetailsRequest params) {
    OrderDetail existingOrderDetail = orderdetailRepository.findById(orderdetail_id)
            .orElseThrow(() -> new IllegalArgumentException("OrderDetail không tồn tại với ID: " + orderdetail_id));

    OrderDetail updatedOrderDetail = params.getOrderdetail();
    if (updatedOrderDetail == null) {
        throw new IllegalArgumentException("OrderDetail không được để trống.");
    }

    if (updatedOrderDetail.getQuantity() > 0) {
        existingOrderDetail.setQuantity(updatedOrderDetail.getQuantity());
    }
    if (updatedOrderDetail.getPrice() != null) {
        existingOrderDetail.setPrice(updatedOrderDetail.getPrice());
    }

    // Không cần set subtotal vì nó tự động
    return orderdetailRepository.save(existingOrderDetail);
}


     // Lấy tất cả OrderDetails theo Product ID
    public List<OrderDetail> getAllOrderDetailByProduct_id(int product_id) {
        Product product = getProductById(product_id);
        return orderdetailRepository.findByProduct(product);
    }

    // Lấy OrderDetail Response
    public OrderDetailsResponse getOrderDetail(int orderdetail_id) {
        OrderDetail orderdetail = orderdetailRepository.findById(orderdetail_id)
                .orElseThrow(() -> new IllegalArgumentException("OrderDetail không tồn tại"));

        OrderDetailsResponse response = new OrderDetailsResponse();
        return response;
    }

    private Product getProductById(int product_id) {
        return productRepository.findById(product_id)
                .orElseThrow(() -> new IllegalArgumentException("Product không tồn tại với ID: " + product_id));
    }

    private Orders getOrderById(int order_id) {
        return ordersRepository.findById(order_id)
                .orElseThrow(() -> new IllegalArgumentException("Order không tồn tại với ID: " + order_id));
    }
}

