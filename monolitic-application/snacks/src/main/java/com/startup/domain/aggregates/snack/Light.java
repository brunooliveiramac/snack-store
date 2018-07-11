package com.startup.domain.aggregates.snack;

public class Light implements Promotion {

    private static final String LETTUCE = "lettuce";
    private static final String BACON = "bacon";

    private Snack snack;
    private Promotion next;

    public Light(Snack snack) {
        this.snack = snack;
    }

    @Override
    public void next(Promotion promotion) {
        this.next = promotion;
    }

    @Override
    public void discount() {
        boolean isLight = snack.ingredients().stream().anyMatch(ingredientItem -> ingredientItem.ingredient().name().equalsIgnoreCase(LETTUCE))
                && snack.ingredients().stream().noneMatch(ingredientItem -> ingredientItem.ingredient().name().equalsIgnoreCase(BACON));
        if (isLight) {
            snack.discountByPercentage(0.10);
        }
        next.discount();
    }
}
