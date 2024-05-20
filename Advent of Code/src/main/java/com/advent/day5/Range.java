package com.advent.day5;

public class Range {

    private final long destination;
    private final long source;
    private final long length;

    public Range(long destination, long source, long length) {
        this.destination = destination;
        this.source = source;
        this.length = length;
    }

    public boolean isSourceInRange(long value) {
        return value >= source && value <= source + length;
    }

    public long getDestination(long value) {
        return destination + (value - source);
    }
}
