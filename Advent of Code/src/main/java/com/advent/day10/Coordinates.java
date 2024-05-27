package com.advent.day10;

public class Coordinates {

    protected final int x;
    protected final int y;

    public Coordinates (int y, int x) {
        this.x = x;
        this.y = y;
    }

    public Coordinates getEast() {
        return new Coordinates(y, x+1);
    }

    public Coordinates getWest() {
        return new Coordinates(y, x-1);
    }

    public Coordinates getSouth() {
        return new Coordinates(y+1, x);
    }

    public Coordinates getNorth() {
        return new Coordinates(y-1, x);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean equals(Coordinates coordinates) {
        return coordinates.getX() == x && coordinates.getY() == y;
    }
}
