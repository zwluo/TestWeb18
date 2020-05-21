package com.example.TestWeb18.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TblOrders extends AbstractIndex
{
    private String orderId;

    private Integer storeId;

    private Integer orderNumber;

    private String address;

    private Date orderDate;

    private String shipName;

    private String shipAddress1;

    private String shipAddress2;

    private String shipCity;

    private String shipState;

    private String shipZipCode;

    private String shipCountryCode;

    private String shipPhone;

    private String shipMethod;

    private Double orderTotal;

    private Integer orderStatus;

    private String payPalTxID;

    private String createBy;

    private Date createDate;

    private String updateBy;

    private Date updateDate;

    @Override
    public Map<String, Object> getJsonMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", getOrderId());
        map.put("storeId", getStoreId());
        map.put("orderNumber", getOrderNumber());
        map.put("address", getAddress());
        map.put("orderDate",getOrderDate());
        map.put("shipName",getShipName());
        map.put("shipAddress1",getShipAddress1());
        map.put("shipAddress2",getShipAddress2());
        map.put("shipCity",getShipCity());
        map.put("shipState",getShipState());
        map.put("shipZipCode",getShipZipCode());
        map.put("shipCountryCode",getShipCountryCode());
        map.put("shipPhone",getShipPhone());
        map.put("shipMethod",getShipMethod());
        map.put("orderTotal",getOrderTotal());
        map.put("orderStatus",getOrderStatus());
        map.put("payPalTxID",getPayPalTxID());
        map.put("createBy",getCreateBy());
        map.put("createDate",getCreateDate());
        map.put("updateBy",getUpdateBy());
        map.put("updateDate",getUpdateDate());

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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress1() {
        return shipAddress1;
    }

    public void setShipAddress1(String shipAddress1) {
        this.shipAddress1 = shipAddress1;
    }

    public String getShipAddress2() {
        return shipAddress2;
    }

    public void setShipAddress2(String shipAddress2) {
        this.shipAddress2 = shipAddress2;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipState() {
        return shipState;
    }

    public void setShipState(String shipState) {
        this.shipState = shipState;
    }

    public String getShipZipCode() {
        return shipZipCode;
    }

    public void setShipZipCode(String shipZipCode) {
        this.shipZipCode = shipZipCode;
    }

    public String getShipCountryCode() {
        return shipCountryCode;
    }

    public void setShipCountryCode(String shipCountryCode) {
        this.shipCountryCode = shipCountryCode;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipMethod() {
        return shipMethod;
    }

    public void setShipMethod(String shipMethod) {
        this.shipMethod = shipMethod;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayPalTxID() {
        return payPalTxID;
    }

    public void setPayPalTxID(String payPalTxID) {
        this.payPalTxID = payPalTxID;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
