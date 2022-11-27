package com.home.springshope.Dto;

import com.home.springshope.Model.Product;

import java.math.BigDecimal;

public class BucketDetailDto {

    private String title;

    private Long productId;

    private BigDecimal price;

    private BigDecimal amount;

    private BigDecimal sum;


    public BucketDetailDto() {

    }

    public BucketDetailDto(Product product) {

        this.title = product.getTitle();

        this.productId = product.getId();

        this.price = product.getPrice();

        this.amount = new BigDecimal(1.0);

        this.sum = product.getPrice();

    }


    public BucketDetailDto(String title, Long productId, BigDecimal price, BigDecimal amount, BigDecimal sum) {
        this.title = title;
        this.productId = productId;
        this.price = price;
        this.amount = amount;
        this.sum = sum;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
