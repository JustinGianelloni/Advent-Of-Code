//Solution for 2023-4:2 Advent of Code
//https://adventofcode.com/2023/day/4
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day4pt2 {

    public static void main(String[] args) throws Exception {
        URL resource = Day4pt2.class.getClassLoader().getResource("day4.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static int parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        ArrayList<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            list.add(scanner.nextLine());
        }
        String[] lines = list.toArray(new String[list.size()]);
        int[] cards = new int[lines.length];
        Arrays.fill(cards, 1);
        int currentCard = 0;
        for (String line : lines) {
            parseLine(line, cards, currentCard++);
        }
        return sumCards(cards);
    }

    private static void parseLine(String line, int[] cards, int currentCard) {
        String[] card = line.split(" \\| ");
        int[] draws = parseNumbers(card[0].split(": ")[1]);
        int[] picks = parseNumbers(card[1]);
        int score = getScore(draws, picks);
        addCards(cards, score, currentCard);
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
        return count;
    }

    private static void addCards(int[] cards, int score, int currentCard) {
        int i = 1;
        while (i <= score && currentCard + i < cards.length) {
            cards[currentCard + i++] += cards[currentCard];
        }
    }

    private static int sumCards(int[] cards) {
        int total = 0;
        for (int card : cards) {
            total += card;
        }
        return total;
    }
}
