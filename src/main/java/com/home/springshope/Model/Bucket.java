package com.home.springshope.Model;


import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "buckets")
public class Bucket {

    private static final String SEQ = "bucket_seq";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ,allocationSize = 1)
    private Long id;


    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToMany
    @JoinTable(name = "bucket_products",
            joinColumns = @JoinColumn(name = "bucket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> Products;












    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }
}
