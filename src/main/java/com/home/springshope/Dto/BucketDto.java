package com.home.springshope.Dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BucketDto {

    private int amountProduct;

    private Double sum;

    private List<BucketDetailDto> detailsBucket = new ArrayList<>();


    public BucketDto() {

    }

    public BucketDto(int amountProduct, Double sum, List<BucketDetailDto> detailsBucket) {
        this.amountProduct = amountProduct;
        this.sum = sum;
        this.detailsBucket = detailsBucket;
    }




    public void aggregate() {

        this.amountProduct = detailsBucket.size();

        this.sum = detailsBucket.stream().map(b -> b.getSum()).mapToDouble(b->b.doubleValue()).sum();


    }


    public int getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(int amountProduct) {
        this.amountProduct = amountProduct;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public List<BucketDetailDto> getDetailsBucket() {
        return detailsBucket;
    }

    public void setDetailsBucket(List<BucketDetailDto> detailsBucket) {
        this.detailsBucket = detailsBucket;
    }
}


