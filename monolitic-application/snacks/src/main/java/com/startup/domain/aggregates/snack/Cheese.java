package com.startup.domain.aggregates.snack;

public class Cheese implements Promotion {


    private static final String CHEESE = "cheese";

    private Snack snack;
    private Promotion promotion;

    public Cheese(Snack snack) {
        this.snack = snack;
    }

    @Override
    public void next(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public void discount() {
        boolean hasCheese = snack.ingredients().stream().anyMatch(ingredientItem -> ingredientItem.ingredient().name().equalsIgnoreCase(CHEESE) &&
                ingredientItem.quantity() >= 3);
        if (hasCheese) {
            snack.ingredients().forEach(
                    ingredientItem -> {
                        if (ingredientItem.ingredient().name().equalsIgnoreCase(CHEESE)) {
                            ingredientItem.value((ingredientItem.quantity() * weightRight) / weightLeft * (ingredientItem.ingredient().value()));
                        }
                    }
            );
        }
        snack.sumTotal();
        promotion.discount();
    }
}
