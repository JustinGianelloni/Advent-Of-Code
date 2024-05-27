//Solution for 2023-10:1 Advent of Code
//https://adventofcode.com/2023/day/10
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day10;

import java.io.File;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Day10pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = Day10pt1.class.getClassLoader().getResource("day10.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    public static int parsePage(File file) throws Exception {
        Scanner scanner = new Scanner(file);
        ArrayList<String> page = new ArrayList<>();
        while (scanner.hasNextLine()) {
            page.add(scanner.nextLine());
        }
        Field field = new Field(page.toArray(new String[0]));
        return countSteps(field);
    }

    public static int countSteps(Field field) throws Exception {
        Coordinates start = field.getStartingLocation();
        Coordinates currentLocation = field.getStartingLocation();
        Coordinates previousLocation = field.getStartingLocation();
        int steps = 0;
        do {
            Coordinates temp = currentLocation;
            currentLocation = getNextLocation(field, currentLocation, previousLocation);
            previousLocation = temp;
            steps++;
        } while(!start.equals(currentLocation));
        return steps / 2;
    }

    public static Pipe getPipe(Field field, Coordinates location) throws Exception {
        Class<?> c = Class.forName("com.advent.day10." + Pipe.validPipes.get(field.getChar(location)));
        Constructor<?> constructor = c.getConstructor(Coordinates.class);
        return (Pipe) constructor.newInstance(location);
    }

    public static Coordinates getNextLocation(Field field,
                                              Coordinates currentLocation,
                                              Coordinates previousLocation) throws Exception {
        Pipe pipe = getPipe(field, currentLocation);
        if (pipe.getWest() != null
                && !pipe.getWest().equals(previousLocation)
                && getPipe(field, pipe.getWest()).getEast() != null) {
            return pipe.getWest();
        }
        if (pipe.getEast() != null
                && !pipe.getEast().equals(previousLocation)
                && getPipe(field, pipe.getEast()).getWest() != null) {
            return pipe.getEast();
        }
        if (pipe.getNorth() != null
                && !pipe.getNorth().equals(previousLocation)
                && getPipe(field, pipe.getNorth()).getSouth() != null) {
            return pipe.getNorth();
        }
        if (pipe.getSouth() != null
                && !pipe.getSouth().equals(previousLocation)
                && getPipe(field, pipe.getSouth()).getNorth() != null) {
            return pipe.getSouth();
        }
        throw new Exception("Something terrible has happened.");
    }
}

