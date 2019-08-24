package com.qingshixun.project.eshop.dto;

public class OrderStatus {

    private String name;

    private String code;

    public OrderStatus() {
    }

    public OrderStatus(String code) {
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
