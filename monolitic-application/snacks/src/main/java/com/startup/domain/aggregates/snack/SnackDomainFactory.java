package com.startup.domain.aggregates.snack;

import com.startup.domain.exception.SnackBusinessException;
import com.startup.infrastructure.persistence.exception.SnackRepositoryException;
import com.startup.infrastructure.persistence.repository.SnackRepository;
import com.startup.infrastructure.web.model.IngredientItemModel;
import com.startup.infrastructure.web.model.IngredientModel;
import com.startup.infrastructure.web.model.SnackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SnackDomainFactory {

    private SnackRepository snackRepository;

    @Autowired
    public SnackDomainFactory(SnackRepository snackRepository) {
        this.snackRepository = snackRepository;
    }

    public Snack createSnack(SnackModel snackModel) {
        return new Snack(snackModel.getName(), SnackIdentifier.id(), createIngredientItemsDomain(snackModel.getIngredientItemModels()));
    }

    private List<IngredientItem> createIngredientItemsDomain(List<IngredientItemModel> ingredientItemModels) {
        return ingredientItemModels.stream().map(ingredientItemModel -> createIngredientItemDomain(ingredientItemModel))
                .collect(Collectors.toList());
    }

    private IngredientItem createIngredientItemDomain(IngredientItemModel ingredientItemModel) {
        return new IngredientItem(createIngredientDomain(ingredientItemModel.getIngredientModel()), ingredientItemModel.getQuantity());
    }

    private Ingredient createIngredientDomain(IngredientModel ingredientModel) {
        com.startup.infrastructure.persistence.model.Ingredient ingredient = null;
        try {
            ingredient = snackRepository.findIngredientById(ingredientModel.getId());
        } catch (SnackRepositoryException e) {
            throw new SnackBusinessException("NOT_FOUND", "Ingredient not found.");
        }
        return new Ingredient(ingredient.getName(), ingredient.getValue());
    }


}
