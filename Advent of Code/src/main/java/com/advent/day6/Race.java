package com.advent.day6;

public class Race {

    private final int time;
    private final int distance;

    public Race(int time, int distance) {
        this.time = time;
        this.distance = distance;
    }

    public int getTime() { return time; }

    public int getDistance() { return distance; }
}
