package com.home.springshope.Dto;

import com.home.springshope.Model.OrderDetails;

import java.util.List;

public class IntegrationOrderDto {

    private Long orderId;
    private String username;
    private String address;
    private List<OrderDetailsDto> details;

    public IntegrationOrderDto() {
    }

    public IntegrationOrderDto(Long orderId, String username, String address, List<OrderDetailsDto> details) {
        this.orderId = orderId;
        this.username = username;
        this.address = address;
        this.details = details;
    }






    public static class OrderDetailsDto {
        private String product;
        private Double price;
        private Double amount;
        private Double sum;

        public OrderDetailsDto() {
        }

        public OrderDetailsDto(OrderDetails details) {
            this.product = details.getProduct().getTitle();
            this.price = details.getPrice().doubleValue();
            this.amount = details.getAmount().doubleValue();
            this.sum = details.getPrice().multiply(details.getAmount()).doubleValue();
        }




















        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
            this.amount = amount;
        }

        public Double getSum() {
            return sum;
        }

        public void setSum(Double sum) {
            this.sum = sum;
        }
    }






























    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderDetailsDto> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailsDto> details) {
        this.details = details;
    }
}
