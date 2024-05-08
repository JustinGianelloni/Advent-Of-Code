package com.advent.gearratios;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Grid {

    private char[][] data;
    private int[] pointer = { 0, 0 };

    public Grid(File file) throws FileNotFoundException {
        data = getEmptyCharGrid(file);
        populateGrid(file);
    }

    private char[][] getEmptyCharGrid(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int rows = 0;
        int cols = 0;
        while (scanner.hasNextLine()) {
            String row = scanner.nextLine();
            if (rows == 0) {
                cols = row.length();
            }
            rows++;
        }
        scanner.close();
        return new char[rows][cols];
    }

    private void populateGrid(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        for (int row = 0; scanner.hasNextLine(); row++) {
            data[row] = scanner.nextLine().toCharArray();
        }
        scanner.close();
    }

    public Character getNextChar() {
        if (pointer[1] == data[0].length - 1) {
            if (pointer[0] == data.length - 1) {
                return null;
            }
            pointer[0]++;
            pointer[1] = 0;
        } else {
            pointer[1]++;
        }
        return data[pointer[0]][pointer[1]];
    }

    public char getCurrentChar() {
        return data[pointer[0]][pointer[1]];
    }

    public Character getCharAt(int row, int col) {
        if (row < data.length && col < data[0].length && row > 0 && col > 0) {
            return data[row][col];
        }
        return null;
    }

    public int getRow() {
        return pointer[0];
    }

    public int getCol() {
        return pointer[1];
    }
}