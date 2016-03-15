package com.theironyard.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by MacLap on 3/14/16.
 */
public class Run {

    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    public String gameName;

    @Column(nullable = false)
    public int timeSeconds;

    @ManyToOne
    User user;

    public Run() {
    }

    public Run(String gameName, int timeSeconds, User user) {
        this.gameName = gameName;
        this.timeSeconds = timeSeconds;
        this.user = user;
    }
}
