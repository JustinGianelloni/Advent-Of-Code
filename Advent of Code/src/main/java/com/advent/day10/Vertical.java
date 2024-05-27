package com.advent.day10;

public class Vertical extends Pipe {

    public Vertical(Coordinates location) {
        super(location);
    }

    @Override
    public Coordinates getNorth() {
        return location.getNorth();
    }

    @Override
    public Coordinates getSouth() {
        return location.getSouth();
    }
}
