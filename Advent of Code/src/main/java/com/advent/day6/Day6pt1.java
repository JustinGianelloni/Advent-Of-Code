//Solution for 2023-6:1 Advent of Code
//https://adventofcode.com/2023/day/6
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Day6pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = Day6pt1.class.getClassLoader().getResource("day6.txt");
        File file = new File(resource.toURI());
        Race[] races = parsePage(file);
        int answer = runRaces(races);
        System.out.println("The answer is: " + answer);
    }

    private static Race[] parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String[] times = scanner.nextLine().split(":")[1].trim().split("\\s+");
        String[] distances = scanner.nextLine().split(":")[1].trim().split("\\s+");
        return buildRaces(times, distances);
    }

    private static Race[] buildRaces(String[] times, String[] distances) {
        Race[] races = new Race[times.length];
        for (int i = 0; i < times.length; i++) {
            races[i] = new Race(Integer.parseInt(times[i]), Integer.parseInt(distances[i]));
        }
        return races;
    }

    private static int runRaces(Race[] races) {
        int answer = 1;
        for (Race race: races) {
            answer *= runRace(race);
        }
        return answer;
    }

    private static int runRace(Race race) {
        int waysToWin = 0;
        for (int speed = 1; speed < race.getTime(); speed++) {
            if ((race.getTime() - speed) * speed > race.getDistance()) {
                waysToWin++;
            }
        }
        return waysToWin;
    }
}
