package com.startup.domain.aggregates.snack;

import java.util.UUID;

public final class IngredientItemIdentifier {

    private final String id;

    private IngredientItemIdentifier(String id) {
        this.id = id;
    }

    public static IngredientItemIdentifier id() {
        return new IngredientItemIdentifier(UUID.randomUUID().toString());
    }
}
