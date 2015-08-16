package com.alextheedom;

/**
 * Created by atheedom on 16/08/15.
 */
public class Config {

    int numberPerSelection;
    int nextNumber;
    int initialNumber;
    int lastNumber;
    int blockSize;

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getNumberPerSelection() {
        return numberPerSelection;
    }

    public void setNumberPerSelection(int numberPerSelection) {
        this.numberPerSelection = numberPerSelection;
    }

    public int getNextNumber() {
        return nextNumber;
    }

    public void setNextNumber(int nextNumber) {
        this.nextNumber = nextNumber;
    }

    public int getInitialNumber() {
        return initialNumber;
    }

    public void setInitialNumber(int initialNumber) {
        this.initialNumber = initialNumber;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
}
