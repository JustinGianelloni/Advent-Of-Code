package com.advent.day10;

public class SouthWest extends Pipe {

    public SouthWest(Coordinates location) {
        super(location);
    }

    @Override
    public Coordinates getSouth() {
        return location.getSouth();
    }

    @Override
    public Coordinates getWest() {
        return location.getWest();
    }
}
