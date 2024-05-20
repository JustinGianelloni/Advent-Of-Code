package com.advent.day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class CubeConundrumPt2 {

    public static void main(String[] args) throws Exception {
        URL resource = CubeConundrumPt2.class.getClassLoader().getResource("cube_conundrum.txt");
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
        String[] game = line.split(": ");
        String[] reveals = game[1].split("; ");
        HashMap<String, Integer> colorCount = getInitialColorCount();
        for (String reveal : reveals) {
            parseReveal(colorCount, reveal);
        }
        return colorCount.get("red") * colorCount.get("green") * colorCount.get("blue");
    }

    private static void parseReveal(HashMap<String, Integer> colorCount, String reveal) {
        for (String color : reveal.split(", ")) {
            parseColor(colorCount, color);
        }
    }

    private static void parseColor(HashMap<String, Integer> colorCount, String color) {
        String[] split = color.split(" ");
        if (Integer.parseInt(split[0]) > colorCount.get(split[1])) {
            colorCount.put(split[1], Integer.valueOf(split[0]));
        }
    }

    private static HashMap<String, Integer> getInitialColorCount() {
        HashMap<String, Integer> colors = new HashMap<>();
        colors.put("red", 0);
        colors.put("green", 0);
        colors.put("blue", 0);
        return colors;
    }
}
