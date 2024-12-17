package com.farmbackend.farmbackend.DTO;

import com.farmbackend.farmbackend.Entities.OrderDetail;
public class OrderDetailsRequest {
    private OrderDetail orderdetail; // Đúng với key trong JSON

    public OrderDetail getOrderdetail() {
        return orderdetail;
    }

    public void setOrderdetail(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
    }

    private int product_id;
    private int order_id;

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}


