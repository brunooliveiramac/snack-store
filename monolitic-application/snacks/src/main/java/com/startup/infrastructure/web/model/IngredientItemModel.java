package com.startup.infrastructure.web.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startup.domain.aggregates.snack.IngredientItem;

import java.io.Serializable;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientItemModel implements Serializable {

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("ingredient")
    private IngredientModel ingredientModel;

    public IngredientItemModel() {
    }

    public IngredientItemModel(int quantity, IngredientModel ingredientModel) {
        this.quantity = quantity;
        this.ingredientModel = ingredientModel;
    }

    public int getQuantity() {
        return quantity;
    }

    public IngredientModel getIngredientModel() {
        return ingredientModel;
    }

    public static IngredientItemModel createIngredientItem(IngredientItem ingredientItem) {
        return new IngredientItemModel(ingredientItem.quantity(), IngredientModel.createIngredient(ingredientItem.ingredient()));
    }
}
