package com.advent.day10;

public class NorthWest extends Pipe {

    public NorthWest(Coordinates location) {
        super(location);
    }

    @Override
    public Coordinates getNorth() {
        return location.getNorth();
    }

    @Override
    public Coordinates getWest() {
        return location.getWest();
    }
}
