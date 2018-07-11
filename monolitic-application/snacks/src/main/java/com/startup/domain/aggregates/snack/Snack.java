package com.startup.domain.aggregates.snack;

import com.startup.domain.Entity;
import com.startup.domain.aggregates.AggregateRoot;
import com.startup.infrastructure.web.model.SnackModel;

import java.util.List;

/**
 * Entity: Has Identifier and Reference Equality. We do care about which of them we work with (Snack) because it has
 * a life cycle.
 * Mutable.
 */
public class Snack extends AggregateRoot implements Entity<Snack, SnackIdentifier> {

    private String name;
    private SnackIdentifier id;
    private List<IngredientItem> ingredients;
    private double total;

    public Snack(SnackIdentifier id, List<IngredientItem> ingredients) {
        this.id = id;
        this.ingredients = ingredients;
    }

    public Snack(String name, SnackIdentifier id, List<IngredientItem> ingredients) {
        this.name = name;
        this.id = id;
        this.ingredients = ingredients;
    }

    public String name() {
        return name;
    }

    public Snack() {

    }

    public List<IngredientItem> ingredients() {
        return ingredients;
    }

    public double total() {
        return total;
    }

    @Override
    public SnackIdentifier identity() {
        return id;
    }

    @Override
    public boolean sameIdentityAs(Snack other) {
        return other != null && identity()
                .equals(other.identity());
    }

    public void sumIngredients() {
        ingredients.forEach(IngredientItem::sumValue);
    }

    public void sumTotal() {
        this.total = ingredients.stream().mapToDouble(IngredientItem::value).sum();
    }


    public void calculateDiscount() {
        Cheese cheese = new Cheese(this);
        Meat meat = new Meat(this);
        Light light = new Light(this);

        cheese.next(meat);
        meat.next(light);
        light.next(new NoDiscount());

        this.sumIngredients();
        this.sumTotal();
        cheese.discount();
    }


    public void discountByPercentage(double percentage) {
        total = total - (total * percentage);
    }


}
