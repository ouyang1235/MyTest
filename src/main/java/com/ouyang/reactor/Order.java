package com.ouyang.reactor;

public class Order {

    public Order(String orderNo) {
        this.orderNo = orderNo;
    }

    private String orderNo;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
