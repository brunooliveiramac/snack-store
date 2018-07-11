package com.startup.domain.aggregates.snack;

import java.util.UUID;

public final class SnackIdentifier {

    private final String id;

    private SnackIdentifier(String id) {
        this.id = id;
    }

    public static SnackIdentifier id() {
        return new SnackIdentifier(UUID.randomUUID().toString());
    }
}
