package com.startup.domain.aggregates.snack;

public interface Promotion {

    int weightLeft = 3;
    int weightRight = 2;

    void next(Promotion promotion);

    void discount();
}
