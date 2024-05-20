package com.advent.day5;

public class Seed {

    private final long seed;
    private final long length;
    private long pointer;

    public Seed(long seed, long length) {
        this.seed = seed;
        this.length = length;
        this.pointer = seed;
    }

    public boolean hasNext() {
        return pointer < seed + length;
    }

    public long getNext() {
        return pointer++;
    }
}