package com.test.demo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    public User() {
    }

    public User(String username, String userpwd) {
        this.username = username;
        this.userpwd = userpwd;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter(AccessLevel.PUBLIC)
    private long userId;


    @Getter
    @Setter(AccessLevel.PUBLIC)
    @Column(name="username")
    private String username;


    @Getter
    @Setter(AccessLevel.PUBLIC)
    @Column(name = "userpwd")
    private String userpwd;

}
