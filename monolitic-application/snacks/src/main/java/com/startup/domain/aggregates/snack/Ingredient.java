package com.startup.domain.aggregates.snack;

import com.startup.domain.ValueObject;

/**
 * ValueObject: Has Reference and Structural Equality. Do not necessary need a identifier field. If we have two equal
 * ingredients do not necessary matter which of them we work with.
 * Should be immutable are more light-weight.
 */
public final class Ingredient implements ValueObject<Ingredient> {

    private final String name;
    private final double value;

    public String name() {
        return name;
    }

    public double value() {
        return value;
    }


    public Ingredient(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean sameValueAs(Ingredient other) {
        return this.equals(other);
    }
}
