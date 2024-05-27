package com.advent.day10;

public class Start extends Pipe {

    public Start(Coordinates location) {
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

    @Override
    public Coordinates getWest() {
        return location.getWest();
    }

    @Override
    public Coordinates getEast() {
        return location.getEast();
    }
}
