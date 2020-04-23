package com.example.TestWeb18.entity;

import java.util.HashMap;
import java.util.Map;

public class TblOrders extends AbstractIndex
{
    private String orderId;

    private Integer storeId;

    private Integer orderNumber;

    private String address;

    @Override
    public Map<String, Object> getJsonMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", this.orderId);
        map.put("storeId", this.storeId);
        map.put("orderNumber", this.orderNumber);
        map.put("address", this.address);
        return map;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
