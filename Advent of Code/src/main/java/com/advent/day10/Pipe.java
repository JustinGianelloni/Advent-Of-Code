package com.advent.day10;

import java.util.Map;

public abstract class Pipe {

    public final static Map<Character, String> validPipes = Map.of(
            '|', "Vertical",
            '-', "Horizontal",
            'L', "NorthEast",
            'J', "NorthWest",
            '7', "SouthWest",
            'F', "SouthEast",
            'S', "Start");

    protected final Coordinates location;

    public Pipe(Coordinates location) {
        this.location = location;
    }

    public Coordinates getNorth() {
        return null;
    }

    public Coordinates getSouth() {
        return null;
    }

    public Coordinates getWest() {
        return null;
    }

    public Coordinates getEast() {
        return null;
    }
}