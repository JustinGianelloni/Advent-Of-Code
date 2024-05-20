//Solution for 2023-2 Advent of Code
//https://adventofcode.com/2023/day/2

package com.advent.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class CubeConundrumPt1 {

    private enum Colors {
        red(12),
        green(13),
        blue(14);

        private final int max;

        Colors(final int color) {
            this.max = color;
        }

        public int getMax() {
            return max;
        }
    }

    public static void main(String[] args) throws Exception {
        URL resource = CubeConundrumPt1.class.getClassLoader().getResource("cube_conundrum.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static int parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int number = 0;
        while (scanner.hasNextLine()) {
            number += parseLine(scanner.nextLine());
        }
        return number;
    }

    private static int parseLine(String line) {
        String[] split = line.split(": ");
        String[] game = split[0].split(" ");
        if (isGamePossible(split[1])) {
            return Integer.parseInt(game[1]);
        }
        return 0;
    }

    private static boolean isGamePossible(String game) {
        for (String reveal : game.split("; ")) {
            if (!isRevealPossible(reveal)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isRevealPossible(String reveal) {
        for (String color : reveal.split(", ")) {
            if (!isColorPossible(color)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColorPossible(String color) {
        String[] split = color.split(" ");
        if (Integer.parseInt(split[0]) > Colors.valueOf(split[1]).getMax()) {
            return false;
        }
        return true;
    }
}