package com.home.springshope.Model;


import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders_details")
public class OrderDetails {

    private static final String SEQ = "order_details_seq";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ, allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private BigDecimal amount;

    private BigDecimal price;

    public OrderDetails(Order order, Product product, Long amount) {
        this.order = order;
        this.product = product;
        this.amount = new BigDecimal(amount);
        this.price = product.getPrice();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
