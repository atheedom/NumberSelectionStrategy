package com.alextheedom;


/**
 * Created by atheedom on 27/07/15.
 */
public class Chunk implements Comparable<Chunk> {

    public enum Status {
        LIVE,
        IN_USE,
        DEAD;
    }

    private Integer idx;
    private Integer startOfChunk;
    private Integer endOfChunk;
    private Integer previousNumber;
    private Status status;

    public Chunk(){

    }

    public Chunk(Integer idx, Integer startOfChunk, Integer endOfChunk, Integer previousNumber, Status status ) {
        this.idx = idx;
        this.startOfChunk = startOfChunk;
        this.endOfChunk = endOfChunk;
        this.previousNumber = previousNumber;
        this.status = status;

    }


    public int compareTo(Chunk chunk) {
        return this.getIdx().compareTo(chunk.getIdx());
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

    public Integer getStartOfChunk() {
        return startOfChunk;
    }

    public void setStartOfChunk(Integer startOfChunk) {
        this.startOfChunk = startOfChunk;
    }

    public Integer getEndOfChunk() {
        return endOfChunk;
    }

    public void setEndOfChunk(Integer endOfChunk) {
        this.endOfChunk = endOfChunk;
    }


}
