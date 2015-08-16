package com.alextheedom;


public enum NumberStrategy {

    ORDERED(new OrderedStrategy()),
    INTERLACED(new InterlacedStrategy());

    private Strategy<Integer> strategy;

    NumberStrategy(Strategy<Integer> strategy) {
        this.strategy = strategy;
    }

    public Strategy<Integer> getStrategy() {
        return strategy;
    }
}
