package com.startup.infrastructure.persistence.repository;

import com.startup.infrastructure.persistence.exception.SnackRepositoryException;
import com.startup.infrastructure.persistence.model.Ingredient;
import com.startup.infrastructure.persistence.model.Snack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class SnackRepository {

    @Autowired
    private EntityManager manager;

    public List<Snack> findAllSnacks() {
        return manager.createQuery("SELECT s FROM Snack s")
                .getResultList();
    }

    public List<Ingredient> findAllIngredients() {
        return manager.createQuery("SELECT i FROM Ingredient i")
                .getResultList();
    }

    public Ingredient findIngredientById(String id) {
        try {
            return (Ingredient) manager.createQuery("SELECT i FROM Ingredient i WHERE i.id = :id")
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new SnackRepositoryException("NOT_FOUND", "Ingredient not found");
        }
    }
}
