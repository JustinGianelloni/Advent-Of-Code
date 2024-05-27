package com.advent.day10;

public class NorthEast extends Pipe {

    public NorthEast(Coordinates location) {
        super(location);
    }

    @Override
    public Coordinates getNorth() {
        return location.getNorth();
    }

    @Override
    public Coordinates getEast() {
        return location.getEast();
    }
}
