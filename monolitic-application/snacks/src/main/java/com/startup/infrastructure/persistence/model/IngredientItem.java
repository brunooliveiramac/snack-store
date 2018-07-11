package com.startup.infrastructure.persistence.model;

import javax.persistence.*;

@Entity
public class IngredientItem {

    @Id
    @Column(name = "id_ingredient_item")
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ingredient")
    private Ingredient ingredient;

    @Column
    private int quantity;

    @Column
    private double value;

    /**
     * The best way to perform one to many is bidirectional
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_snack")
    private Snack snack;

    public IngredientItem() {
    }

    public String getId() {
        return id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getValue() {
        return value;
    }
}
