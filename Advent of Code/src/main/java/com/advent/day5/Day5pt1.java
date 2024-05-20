package com.advent.day5;

import com.advent.day3.GearRatiosPt1;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

public class Day5pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = GearRatiosPt1.class.getClassLoader().getResource("day5.txt");
        File file = new File(resource.toURI());
        long answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static long parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Set<Long> seeds = getSeeds(scanner.nextLine());
        scanner.nextLine(); //skip first blank line
        FarmMap seedToSoil = getFarmMap(scanner);
        FarmMap soilToFertilizer = getFarmMap(scanner);
        FarmMap fertilizerToWater = getFarmMap(scanner);
        FarmMap waterToLight = getFarmMap(scanner);
        FarmMap lightToTemperature = getFarmMap(scanner);
        FarmMap temperatureToHumidity = getFarmMap(scanner);
        FarmMap humidityToLocation = getFarmMap(scanner);
        long min = Long.MAX_VALUE;
        for (long seed : seeds) {
            long location = humidityToLocation.getDestination(temperatureToHumidity.getDestination(lightToTemperature.getDestination(waterToLight.getDestination(fertilizerToWater.getDestination(soilToFertilizer.getDestination(seedToSoil.getDestination(seed)))))));
            if (location < min) {
                min = location;
            }
        }
        return min;
    }

    private static Set<Long> getSeeds(String firstLine) {
        String[] split = firstLine.split(": ")[1].split(" ");
        Set<Long> seeds = new HashSet<>(split.length);
        for (String seed : split) {
            seeds.add(Long.parseLong(seed));
        }
        return seeds;
    }

    private static FarmMap getFarmMap(Scanner scanner) {
        ArrayList<String> list = new ArrayList<>();
        scanner.nextLine(); //skip header
        String line = scanner.nextLine();
        while (!line.isEmpty() && scanner.hasNextLine()) {
            list.add(line);
            line = scanner.nextLine();
        }
        return new FarmMap(list.toArray(new String[0]));
    }
}
