//Solution for 2023-9:2 Advent of Code
//https://adventofcode.com/2023/day/9
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day9;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Day9pt2 {

    public static void main(String[] args) throws Exception {
        URL resource = Day9pt2.class.getClassLoader().getResource("day9.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    public static int parsePage(File file) throws FileNotFoundException, ExecutionException, InterruptedException {
        Scanner scanner = new Scanner(file);
        int answer = 0;
        ArrayList<History> histories = new ArrayList<>();
        while (scanner.hasNextLine()) {
            histories.add(new PreHistory(scanner.nextLine()));
        }
        ArrayList<Thread> tasks = new ArrayList<>(histories.size());
        for (History history : histories) {
            tasks.add(Thread.ofVirtual().start(history));
        }
        for (Thread task : tasks) {
            task.join();
        }
        for (History history: histories) {
            answer += history.getExtrapolatedValue();
        }
        return answer;
    }
}