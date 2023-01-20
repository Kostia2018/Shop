package com.home.springshope.Model;


import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category {

    private static final String SEQ = "category_seq";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ,allocationSize = 1)
    private Long id;


    private String title;













    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
