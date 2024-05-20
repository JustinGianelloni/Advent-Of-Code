package com.advent.day4;

import com.advent.day3.GearRatiosPt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Day4pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = GearRatiosPt1.class.getClassLoader().getResource("day4.txt");
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
        String[] card = line.split(" \\| ");
        int[] draws = parseNumbers(card[0].split(": ")[1]);
        int[] picks = parseNumbers(card[1]);
        return getScore(draws, picks);
    }

    private static int[] parseNumbers(String numbers) {
        String[] split = numbers.trim().split("\\s+");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    private static int getScore(int[] draws, int[] picks) {
        int count = 0;
        for (int pick : picks) {
            for (int draw : draws) {
                if (pick == draw) {
                    count++;
                }
            }
        }
        if (count <= 1) {
            return count;
        }
        return (int) Math.pow(2, count - 1);
    }
}


