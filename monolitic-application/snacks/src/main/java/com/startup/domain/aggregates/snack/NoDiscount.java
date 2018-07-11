package com.startup.domain.aggregates.snack;

public class NoDiscount implements Promotion {


    @Override
    public void next(Promotion promotion) {
        System.out.println(" No Next ");
    }

    @Override
    public void discount() {
        System.out.println(" Last discount ");
    }
}
