package com.advent.day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.net.URL;
import java.util.*;

public class Day5pt2 {
    
    public static void main(String[] args) throws Exception {
        URL resource = Day5pt2.class.getClassLoader().getResource("day5.txt");
        File file = new File(resource.toURI());
        long answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    private static long parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        SeedMap seeds = new SeedMap(scanner.nextLine());
        scanner.nextLine(); //skip first blank line
        FarmMap seedToSoil = getFarmMap(scanner);
        FarmMap soilToFertilizer = getFarmMap(scanner);
        FarmMap fertilizerToWater = getFarmMap(scanner);
        FarmMap waterToLight = getFarmMap(scanner);
        FarmMap lightToTemperature = getFarmMap(scanner);
        FarmMap temperatureToHumidity = getFarmMap(scanner);
        FarmMap humidityToLocation = getFarmMap(scanner);
        long min = Long.MAX_VALUE;
        while (seeds.hasNext()) {
            long location = humidityToLocation.getDestination(temperatureToHumidity.getDestination(lightToTemperature.getDestination(waterToLight.getDestination(fertilizerToWater.getDestination(soilToFertilizer.getDestination(seedToSoil.getDestination(seeds.getNext())))))));
            if (location < min) {
                System.out.println("New min found: " + location);
                min = location;
            }
        }
        return min;
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
