package com.advent.day10;

public class Field {

    protected final char[][] field;
    protected Coordinates startingLocation = null;

    public Field(String[] page) {
        this.field = new char[page.length][];
        for (int y = 0; y < page.length; y++) {
            field[y] = page[y].toCharArray();
            if (startingLocation != null) {
                continue;
            }
            for (int x = 0; x < field[y].length; x++) {
                if (field[y][x] == 'S') {
                    startingLocation = new Coordinates(y, x);
                    break;
                }
            }
        }
    }

    public Coordinates getStartingLocation() {
        return startingLocation;
    }

    public char getChar(Coordinates location) {
        return field[location.getY()][location.getX()];
    }
}
