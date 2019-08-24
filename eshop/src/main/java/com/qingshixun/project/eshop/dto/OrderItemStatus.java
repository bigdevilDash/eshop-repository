package com.qingshixun.project.eshop.dto;

public class OrderItemStatus {

    private String name;

    private String code;

    public OrderItemStatus() {
    }

    public OrderItemStatus(String code) {
        this.code = code;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
