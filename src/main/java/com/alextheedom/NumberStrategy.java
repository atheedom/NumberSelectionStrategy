package com.alextheedom;

/**
 * Created by atheedom on 24/07/15.
 */
public enum NumberStrategy {

    SEQUENTIAL(new SequentialStrategy()),
    CHUNK(new ChunkStrategy());

    private Strategy<Integer> strategy;

    NumberStrategy(Strategy<Integer> strategy) {
        this.strategy = strategy;
    }

    public Strategy<Integer> getStrategy() {
        return strategy;
    }
}
