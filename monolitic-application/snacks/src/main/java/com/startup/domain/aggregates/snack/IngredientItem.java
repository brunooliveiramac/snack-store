package com.startup.domain.aggregates.snack;


import com.startup.domain.Entity;

/**
 * Entity: Has Identifier and Reference Equality. We do care about which of them we work with (Snack) because it has
 * a life cycle.
 * Mutable.
 */
public class IngredientItem implements Entity<IngredientItem, IngredientItemIdentifier> {

    private IngredientItemIdentifier id;
    private Ingredient ingredient;
    private int quantity;
    private double value;

    public IngredientItem(Ingredient ingredient, int quantity, double value) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.value = value;
    }

    public IngredientItem(Ingredient ingredient, int quantity) {
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    public Ingredient ingredient() {
        return ingredient;
    }

    public int quantity() {
        return quantity;
    }

    public double value() {
        return value;
    }

    public void value(double value) {
        this.value = value;
    }

    @Override
    public IngredientItemIdentifier identity() {
        return id;
    }

    @Override
    public boolean sameIdentityAs(IngredientItem other) {
        return other != null && identity()
                .equals(other.identity());
    }

    public void sumValue() {
        this.value = ingredient.value() * quantity;
    }
}
