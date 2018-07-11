package com.startup.infrastructure.web.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.startup.infrastructure.persistence.model.IngredientItem;
import com.startup.infrastructure.persistence.model.Snack;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SnackModel implements Serializable {

    @JsonProperty
    private String name;

    @JsonProperty("ingredientItems")
    private List<IngredientItemModel> ingredientItemModels;

    @JsonProperty("total")
    private double total;

    public List<IngredientItemModel> getIngredientItemModels() {
        return ingredientItemModels;
    }

    public double getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }

    public SnackModel() {
    }

    public SnackModel(List<IngredientItemModel> ingredientItemModels, double total) {
        this.ingredientItemModels = ingredientItemModels;
        this.total = total;
    }

    public SnackModel(String name, List<IngredientItemModel> ingredientItemModels, double total) {
        this.name = name;
        this.ingredientItemModels = ingredientItemModels;
        this.total = total;
    }

    public static SnackModel createSnackModelFromDomain(com.startup.domain.aggregates.snack.Snack snack) {
        List<IngredientItemModel> ingredientItemModels = snack.ingredients().stream()
                .map(ingredientItem -> IngredientItemModel.createIngredientItem(ingredientItem))
                .collect(Collectors.toList());
        return new SnackModel(snack.name(), ingredientItemModels, snack.total());
    }

    public static List<SnackModel> createSnacks(List<Snack> snacks) {
        return snacks.stream().map(snackEntity -> createSnackModelFromRepository(snackEntity))
                .collect(Collectors.toList());
    }

    private static SnackModel createSnackModelFromRepository(Snack snackEntity) {
        return new SnackModel(snackEntity.getName(), createIngredientItemsModelFromEntity(snackEntity.getIngredientItemEntity()), snackEntity.getTotal());
    }

    private static List<IngredientItemModel> createIngredientItemsModelFromEntity(List<IngredientItem> ingredientItemEntity) {
        return ingredientItemEntity.stream().map(item -> createIngredientItemModelFromEntity(item)).collect(Collectors.toList());
    }

    private static IngredientItemModel createIngredientItemModelFromEntity(IngredientItem item) {
        return new IngredientItemModel(item.getQuantity(), createIngredientFromRepository(item.getIngredient()));
    }

    private static IngredientModel createIngredientFromRepository(com.startup.infrastructure.persistence.model.Ingredient ingredient) {
        return new IngredientModel(ingredient.getId(), ingredient.getName(), ingredient.getValue());
    }
}
