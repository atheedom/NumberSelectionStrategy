package com.alextheedom;


/**
 * Created by atheedom on 27/07/15.
 */
public class Block implements Comparable<Block> {

    public enum Status {
        LIVE,
        IN_USE,
        DEAD;
    }

    private Integer idx;
    private Integer startOfBlock;
    private Integer endOfBlock;
    private Integer previousNumber;
    private Status status;

    public Block(){}

    public Block(Integer idx, Integer startOfBlock, Integer endOfBlock, Integer previousNumber, Status status) {
        this.idx = idx;
        this.startOfBlock = startOfBlock;
        this.endOfBlock = endOfBlock;
        this.previousNumber = previousNumber;
        this.status = status;
    }

    public int compareTo(Block block) {
        return this.getIdx().compareTo(block.getIdx());
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPreviousNumber() {
        return previousNumber;
    }

    public void setPreviousNumber(Integer previousNumber) {
        this.previousNumber = previousNumber;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public Integer getStartOfBlock() {
        return startOfBlock;
    }

    public void setStartOfBlock(Integer startOfBlock) {
        this.startOfBlock = startOfBlock;
    }

    public Integer getEndOfBlock() {
        return endOfBlock;
    }

    public void setEndOfBlock(Integer endOfBlock) {
        this.endOfBlock = endOfBlock;
    }

}
