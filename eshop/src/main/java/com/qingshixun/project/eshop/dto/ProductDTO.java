/*****************************************************************************
 * Copyright (c) 2017, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.dto;

import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.core.Constants;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * 商品实体类
 *
 * @author QingShiXun
 * @version 1.0
 */

public class ProductDTO extends BaseDTO {

    // 商品名称
    private String name;

    // 商品价格
    private Double price = 0d;

    // 市场价格
    private Double marketPrice;

    // 商品库存数量
    private Integer store;

    // 积分
    private Integer point;

    // 是否上架
    private Boolean isMarketable;

    // 是否为精品商品
    private Boolean isBest;

    // 是否为新品商品
    private Boolean isNew;

    // 是否为热销商品
    private Boolean isHot;

    // 描述
    private String description;

    // 商品图片路径存储
    private String productImage;

    // 商品分类
    private ProductCategoryDTO productCategory;

    // 品牌
    private BrandDTO brand;

    // 商品类型
    private ProductTypeDTO productType;

    // 购物车项
    private Set<CartItemDTO> cartItemSet;

    // 商品截图图片名字
    private String screenshots;

    // 评论数量
    private Integer evaluateCount;

    // 商品类型属性列表
    private List<ProductTypeValueDTO> values = Lists.newArrayList();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Boolean getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(Boolean isMarketable) {
        this.isMarketable = isMarketable;
    }

    public Boolean getIsBest() {
        return isBest;
    }

    public void setIsBest(Boolean isBest) {
        this.isBest = isBest;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(Boolean isHot) {
        this.isHot = isHot;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategoryDTO getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryDTO productCategory) {
        this.productCategory = productCategory;
    }

    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }

    public ProductTypeDTO getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeDTO productType) {
        this.productType = productType;
    }

    public Set<CartItemDTO> getCartItemSet() {
        return cartItemSet;
    }

    public void setCartItemSet(Set<CartItemDTO> cartItemSet) {
        this.cartItemSet = cartItemSet;
    }

    public String getProductImage() {
        return String.format("%s/%s", Constants.PRODUCT_IMAGE_PATH, productImage);
    }

    public String getSmallProductImage() {
        return String.format("%s/small_%s", Constants.PRODUCT_IMAGE_PATH, productImage);
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String[] getThumbnails() {
        if (screenshots != null) {
            return screenshots.split(",");
        }

        return null;
    }

    public String getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(String screenshots) {
        this.screenshots = screenshots;
    }

    public Integer getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(Integer evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public List<ProductTypeValueDTO> getValues() {
        return values;
    }

    public void setValues(List<ProductTypeValueDTO> values) {
        this.values = values;
    }
}
