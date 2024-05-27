package com.advent.day10;

public class Horizontal extends Pipe {

    public Horizontal(Coordinates location) {
        super(location);
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
