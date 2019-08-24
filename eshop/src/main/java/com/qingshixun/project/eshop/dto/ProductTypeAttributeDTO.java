/********************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *********************************************/
package com.qingshixun.project.eshop.dto;

/**
 * 商品屬性实体类
 *
 * @author QingShiXun
 * @version 1.0
 */
public class ProductTypeAttributeDTO extends BaseDTO {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 1L;

    // 属性名称
    private String name;

    // 所属商品类型
    private ProductTypeDTO productType;

    // 属性类型（目前只有下拉跟多选两种类型）
    private String type;

    // 该属性具有哪些值
    private String value;

    // 该属性具有哪些值以数组形式显示
    private String[] values;

    // 商品选中的属性值
    private String selectValues;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String[] getValues() {
        if (value != null) {
            return value.split(",");
        }

        return null;
    }

    public void setValues(String[] values) {
        this.values = values;
    }

    public String getSelectValues() {
        return selectValues;
    }

    public void setSelectValues(String selectValues) {
        this.selectValues = selectValues;
    }

}
