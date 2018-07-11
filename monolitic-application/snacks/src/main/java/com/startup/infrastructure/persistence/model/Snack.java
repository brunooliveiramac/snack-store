package com.startup.infrastructure.persistence.model;
import javax.persistence.*;
import java.util.List;

@Entity
public class Snack {

    @Id
    @Column(name = "id_snack")
    private String id;

    @Column
    private String name;

    /**
     * The best way to perform one to many is bidirectional
     */
    @OneToMany(mappedBy = "snack", fetch = FetchType.LAZY, cascade= CascadeType.ALL, orphanRemoval = true)
    private List<IngredientItem> IngredientItems;

    @Column
    private double total;

    public Snack() {
    }

    public String getId() {
        return id;
    }

    public List<IngredientItem> getIngredientItemEntity() {
        return IngredientItems;
    }

    public double getTotal() {
        return total;
    }

    public String getName() {
        return name;
    }
}
