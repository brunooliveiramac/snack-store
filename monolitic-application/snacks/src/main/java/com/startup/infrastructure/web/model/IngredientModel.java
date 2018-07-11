package com.startup.infrastructure.web.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startup.infrastructure.persistence.model.Ingredient;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IngredientModel implements Serializable {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private double value;

    public IngredientModel() {
    }

    public IngredientModel(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public IngredientModel(String id, String name, double value) {
        this.id = id;
        this.name = name;
        this.value = value;
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

    public static IngredientModel createIngredient(com.startup.domain.aggregates.snack.Ingredient ingredient) {
        return new IngredientModel(ingredient.name(), ingredient.value());
    }

    public static List<IngredientModel> createIngredients(List<Ingredient> allIngredients) {
        return allIngredients.stream().map(ingredientEntity -> createIngredientFromEntity(ingredientEntity))
                .collect(Collectors.toList());
    }

    private static IngredientModel createIngredientFromEntity(Ingredient ingredientEntity) {
        return new IngredientModel(ingredientEntity.getId(), ingredientEntity.getName(), ingredientEntity.getValue());
    }
}
