package com.qingshixun.project.eshop.dto;

public class OrderChannel {

    private String code;

    private String name;

    public OrderChannel(String code) {
        this.code = code;
    }

    public OrderChannel() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
