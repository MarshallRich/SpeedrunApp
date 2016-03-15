package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by MacLap on 3/14/16.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false, unique = true)
    String name;

    @Column(nullable = false)
    String passwordHash;

    public String getPasswordHash()
    {
        return passwordHash;
    }

    public User() {

    }

    public User(String name, String passwordHash) {
        this.name = name;
        this.passwordHash = passwordHash;
    }
}
