package com.advent.day6;

public class LongRace {

    private final long time;
    private final long distance;

    public LongRace(long time, long distance) {
        this.time = time;
        this.distance = distance;
    }

    public long getTime() { return time; }

    public long getDistance() { return distance; }
}
