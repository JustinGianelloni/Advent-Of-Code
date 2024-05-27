//Solution for 2023-7:1 Advent of Code
//https://adventofcode.com/2023/day/7
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day7pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = Day7pt1.class.getClassLoader().getResource("day7.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static int parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Hand[] hands = buildHands(scanner);
        Hand[] rankedHands = rankHands(hands);
        int winnings = 0;
        for (int i = 0; i < rankedHands.length; i++) {
            winnings += rankedHands[rankedHands.length - i - 1].getBid() * (i + 1);
        }
        return winnings;
    }

    private static Hand[] rankHands(Hand[] hands) {
        LinkedList<Hand> rankedHands = new LinkedList<>();
        rankedHands.add(hands[0]);
        for (int i = 1; i < hands.length; i++) {
            insertHand(hands[i], rankedHands);
        }
        return rankedHands.toArray(new Hand[0]);
    }

    private static void insertHand(Hand hand, LinkedList<Hand> rankedHands) {
        for (int i = 0; i < rankedHands.size(); i++) {
            if (hand.getHandType().getValue() > rankedHands.get(i).getHandType().getValue()) {
                rankedHands.add(i, hand);
                return;
            }
            if (hand.getHandType() == rankedHands.get(i).getHandType()
                    && tieBreaker(hand, rankedHands.get(i), 0)) {
                rankedHands.add(i, hand);
                return;
            }
        }
        rankedHands.addLast(hand);
    }

    private static boolean tieBreaker(Hand hand, Hand rankedHand, int position) {
        if (hand.getPositionValue(position) > rankedHand.getPositionValue(position)) {
            return true;
        }
        if (hand.getPositionValue(position) == rankedHand.getPositionValue(position)) {
            return tieBreaker(hand, rankedHand, position + 1);
        }
        return false;
    }

    private static Hand[] buildHands(Scanner scanner) {
        ArrayList<Hand> hands = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split("\\s");
            hands.add(new Hand(split[0], Integer.parseInt(split[1])));
        }
        return hands.toArray(new Hand[0]);
    }
}