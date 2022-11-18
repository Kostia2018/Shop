package com.home.springshope.Model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    private static final String SEQ = "user_seq";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ)
    private Long id;

    private String name;

    private String password;

    private String mail;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    @OneToOne(cascade = CascadeType.REMOVE)
    private Bucket bucket;



























    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }
}
