//Solution for 2023-3:1 Advent of Code
//https://adventofcode.com/2023/day/3
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3pt1 {

    private final static List<Character> symbols = Arrays.asList('#', '*', '$', '%', '+', '@', '&', '-', '=', '/');

    public static void main(String[] args) throws Exception {
        URL resource = Day3pt1.class.getClassLoader().getResource("day3.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static int parsePage(File file) throws FileNotFoundException {
        Grid grid = new Grid(file);
        int result = 0;
        while (grid.getNextChar() != null) {
            if (Character.isDigit(grid.getCurrentChar())) {
                result += handleNumber(grid);
            }
        }
        return result;
    }

    private static int handleNumber(Grid grid) {
        int[] start = {grid.getRow(), grid.getCol()};
        String number = getNumberAsString(grid);
        if (checkLeft(grid, start)) {
            return Integer.parseInt(number);
        }
        for (int i = 0; i < number.length(); i++) {
            if (checkMiddle(grid, start, i)) {
                return Integer.parseInt(number);
            }
        }
        if (checkRight(grid, start, number.length())) {
            return Integer.parseInt(number);
        }
        return 0;
    }

    private static boolean checkRight(Grid grid, int[] start, int offset) {
        if (symbols.contains(grid.getCharAt(start[0] + 1, start[1] + offset))) {
            return true;
        }
        if (symbols.contains(grid.getCharAt(start[0], start[1] + offset))) {
            return true;
        }
        if (symbols.contains(grid.getCharAt(start[0] - 1, start[1] + offset))) {
            return true;
        }
        return false;
    }

    private static boolean checkMiddle(Grid grid, int[] start, int offset) {
        if (symbols.contains(grid.getCharAt(start[0] + 1, start[1] + offset))) {
            return true;
        }
        if (symbols.contains(grid.getCharAt(start[0] - 1, start[1] + offset))) {
            return true;
        }
        return false;
    }

    private static boolean checkLeft(Grid grid, int[] start) {
        if (symbols.contains(grid.getCharAt(start[0] + 1, start[1] - 1))) {
            return true;
        }
        if (symbols.contains(grid.getCharAt(start[0], start[1] - 1))) {
            return true;
        }
        if (symbols.contains(grid.getCharAt(start[0] - 1, start[1] - 1))) {
            return true;
        }
        return false;
    }

    private static String getNumberAsString(Grid grid) {
        ArrayList<Character> digits = new ArrayList<>();
        digits.add(grid.getCurrentChar());
        while (Character.isDigit(grid.getNextChar())) {
            digits.add(grid.getCurrentChar());
        }
        StringBuilder number = new StringBuilder(digits.size());
        for (char ch : digits) {
            number.append(ch);
        }
        return number.toString();
    }
}