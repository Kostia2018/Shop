package com.home.springshope.Model;




//import javax.persistence.*;


import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    private static final String SEQ = "user_seq";


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ)
    @SequenceGenerator(name = SEQ, sequenceName = SEQ,allocationSize = 1)
    private Long id;

    private String name;

    private String password;

    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;


    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
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

    public String getEmailMail() {
        return email;
    }

    public void setEmailMail(String mail) {
        this.email = mail;
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
