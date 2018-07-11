package com.startup.application;

import com.startup.domain.aggregates.snack.Snack;
import com.startup.domain.aggregates.snack.SnackDomainFactory;
import com.startup.infrastructure.persistence.repository.SnackRepository;
import com.startup.infrastructure.web.model.IngredientModel;
import com.startup.infrastructure.web.model.SnackModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SnackApplication {


    private SnackRepository snackRepository;

    private SnackDomainFactory snackDomainFactory;

    @Autowired
    public SnackApplication(SnackRepository snackRepository, SnackDomainFactory snackDomainFactory) {
        this.snackRepository = snackRepository;
        this.snackDomainFactory = snackDomainFactory;
    }

    public SnackModel customSnack(SnackModel snackModel) {
        Snack snack = snackDomainFactory.createSnack(snackModel);
        snack.calculateDiscount();
        return SnackModel.createSnackModelFromDomain(snack);
    }

    public List<SnackModel> obtainAllSnacks() {
        return SnackModel.createSnacks(snackRepository.findAllSnacks());
    }

    public List<IngredientModel> obtainAllIngredients() {
        return IngredientModel.createIngredients(snackRepository.findAllIngredients());
    }
}
