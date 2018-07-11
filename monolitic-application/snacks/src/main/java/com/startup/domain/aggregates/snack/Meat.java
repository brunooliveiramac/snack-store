package com.startup.domain.aggregates.snack;

public class Meat implements Promotion {

    private static final String MEAT = "meat";

    private Snack snack;
    private Promotion next;

    public Meat(Snack snack) {
        this.snack = snack;
    }

    @Override
    public void next(Promotion promotion) {
        this.next = promotion;
    }

    @Override
    public void discount() {
        boolean hasMeat = snack.ingredients().stream().anyMatch(ingredientItem -> ingredientItem.ingredient().name().equalsIgnoreCase(MEAT) &&
                ingredientItem.quantity() >= 3);
        if (hasMeat) {
            snack.ingredients().forEach(
                    ingredientItem -> {
                        if(ingredientItem.ingredient().name().equalsIgnoreCase(MEAT)){
                            ingredientItem.value((ingredientItem.quantity() * weightRight) / weightLeft * (ingredientItem.ingredient().value()));
                        }
                    }
            );
        }

        snack.sumTotal();
        next.discount();

    }
}
