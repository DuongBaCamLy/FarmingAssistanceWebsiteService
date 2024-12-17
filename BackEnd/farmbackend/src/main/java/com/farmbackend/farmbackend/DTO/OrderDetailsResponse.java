package com.farmbackend.farmbackend.DTO;

import com.farmbackend.farmbackend.Entities.OrderDetail;

public class OrderDetailsResponse {
    private OrderDetail orderdetail;

    public OrderDetail getOrderDetail() {
        return orderdetail;
    }

    public void setOrders(OrderDetail orderdetail) {
        this.orderdetail = orderdetail;
    }

    public void setOrderDetail(OrderDetail orderdetail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
