package com.advent.day10;

public class SouthEast extends Pipe {

    public SouthEast(Coordinates location) {
        super(location);
    }

    @Override
    public Coordinates getSouth() {
        return location.getSouth();
    }

    @Override
    public Coordinates getEast() {
        return location.getEast();
    }
}
