package com.advent.day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

public class Day6pt2 {

    public static void main(String[] args) throws Exception {
        URL resource = Day6pt2.class.getClassLoader().getResource("day6.txt");
        File file = new File(resource.toURI());
        LongRace race = parsePage(file);
        int answer = runRace(race);
        System.out.println("The answer is: " + answer);
    }

    private static LongRace parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        String time = scanner.nextLine().split(":")[1].trim().replaceAll("\\s", "");
        String distance = scanner.nextLine().split(":")[1].trim().replaceAll("\\s", "");
        return new LongRace(Long.parseLong(time), Long.parseLong(distance));
    }

    private static int runRace(LongRace race) {
        int waysToWin = 0;
        for (long speed = 1; speed < race.getTime(); speed++) {
            if ((race.getTime() - speed) * speed > race.getDistance()) {
                waysToWin++;
            }
        }
        return waysToWin;
    }
}