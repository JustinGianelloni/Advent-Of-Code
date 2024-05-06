//Solution for 2023-1 Advent of Code
//https://adventofcode.com/2023/day/1

package com.advent;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Trebuchet {

    static final Map<String, Integer> words = Map.of(
            "one", 1,
            "two", 2,
            "three", 3,
            "four", 4,
            "five", 5,
            "six", 6,
            "seven", 7,
            "eight", 8,
            "nine", 9);

    public static void main(String[] args) throws Exception {
        URL resource = Trebuchet.class.getClassLoader().getResource("trebuchet.txt");
        File file = new File(resource.toURI());
        int part1 = parsePage(file, 1);
        System.out.println("Part 1 Answer: " + part1);
        int part2 = parsePage(file, 2);
        System.out.println("Part 2 Answer: " + part2);
    }

    private static int getNumber(char[] chars) {
        ArrayList<Character> digits = new ArrayList<>();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                digits.add(c);
            }
        }
        char[] result = {digits.getFirst(), digits.getLast()};
        return Integer.parseInt(new String(result));
    }

    private static int parsePage(File file, int part) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int number = 0;
        while (scanner.hasNextLine()) {
            number += parseLine(scanner.nextLine(), part);
        }
        return number;
    }

    private static int parseLine(String line, int part) {
        if (part == 2) {
            line = replaceWords(line.toLowerCase());
        }
        return getNumber(line.toCharArray());
    }

    private static String replaceWords(String line) {
        for (int i = 0; i < line.length(); i++) {
            for (Map.Entry<String, Integer> word : words.entrySet()) {
                if (line.indexOf(word.getKey()) == i) {
                    System.out.println("Line: " + line);
                    System.out.println("Replacing " + word.getKey() + " with: " + word.getValue());
                    line = line.replaceFirst(word.getKey(), word.getValue().toString());
                    System.out.println("New Line: " + line + "\n");
                }
            }
        }
        return line;
    }
}