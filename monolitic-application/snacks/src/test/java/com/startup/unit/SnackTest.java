package com.startup.unit;

import com.startup.domain.aggregates.snack.Ingredient;
import com.startup.domain.aggregates.snack.IngredientItem;
import com.startup.domain.aggregates.snack.Snack;
import com.startup.domain.aggregates.snack.SnackIdentifier;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SnackTest {


    private static final String LETTUCE = "lettuce";
    private static final String MEAT = "meat";
    private static final String CHEESE = "cheese";

    @Test
    public void should_Apply10PercentageDiscount_When_HasLettuceButHasNoBacon() {
        Ingredient lettuce = new Ingredient(LETTUCE, 10);
        List<IngredientItem> items = Arrays.asList(new IngredientItem(lettuce, 2, 20));
        Snack snack = new Snack(SnackIdentifier.id(), items);
        snack.calculateDiscount();
        assertEquals(18, snack.total(), 0.0);
    }

    @Test
    public void should_Cost6_When_Has3Meat() {
        Ingredient meat = new Ingredient(MEAT, 3);
        List<IngredientItem> items = Arrays.asList(new IngredientItem(meat, 3, 9));
        Snack snack = new Snack(SnackIdentifier.id(), items);
        snack.calculateDiscount();
        assertEquals(6, snack.total(), 0.0);
    }

    @Test
    public void should_Cost9_When_Has3MeatAnd3Cheese() {
        Ingredient meat = new Ingredient(MEAT, 3);
        Ingredient cheese = new Ingredient(CHEESE, 1.50);
        List<IngredientItem> items = Arrays.asList(new IngredientItem(meat, 3, 9), new IngredientItem(cheese, 3, 4.50));
        Snack snack = new Snack(SnackIdentifier.id(), items);
        snack.calculateDiscount();
        assertEquals(9, snack.total(), 0.0);
    }

    @Test
    public void should_Cost450_When_NoRuleIsApplied() {
        Ingredient meat = new Ingredient(MEAT, 3);
        Ingredient cheese = new Ingredient(CHEESE, 1.50);
        List<IngredientItem> items = Arrays.asList(new IngredientItem(meat, 1, 3), new IngredientItem(cheese, 1, 1.50));
        Snack snack = new Snack(SnackIdentifier.id(), items);
        snack.calculateDiscount();
        assertEquals(4.50, snack.total(), 0.0);
    }

    @Test
    public void should_SumIngredientValue_When_HasTwoIngredients() {
        Ingredient meat = new Ingredient(MEAT, 3);
        Ingredient cheese = new Ingredient(CHEESE, 1.50);
        List<IngredientItem> items = Arrays.asList(new IngredientItem(meat, 1, 0), new IngredientItem(cheese, 1, 0));
        Snack snack = new Snack(SnackIdentifier.id(), items);
        snack.calculateDiscount();
        assertEquals(4.50, snack.total(), 0.0);
    }

}
