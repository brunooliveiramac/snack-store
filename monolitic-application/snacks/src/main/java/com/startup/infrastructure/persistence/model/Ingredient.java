package com.startup.infrastructure.persistence.model;

import javax.persistence.*;

@Entity
public class Ingredient {

    @Id
    @Column(name = "id_ingredient")
    private String id;

    @Column
    private String name;

    @Column
    private double value;


    public Ingredient() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
}
