//Solution for 2023-8:1 Advent of Code
//https://adventofcode.com/2023/day/8
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class Day8pt1 {

    public static void main(String[] args) throws Exception {
        URL resource = Day8pt1.class.getClassLoader().getResource("day8.txt");
        File file = new File(resource.toURI());
        int answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    public static int parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Instructions instructions = new Instructions(scanner.nextLine());
        scanner.nextLine(); //skip blank line
        HashMap<String, Node> network = new HashMap<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" = ");
            network.put(split[0], new Node(split[1]));
        }
        return traverseNetwork(instructions, network, "AAA", 1);
    }

    public static int traverseNetwork(Instructions instructions, HashMap<String, Node> network, String node, int steps) {
        String nextNode = network.get(node).getElement(instructions.next());
        if (nextNode.equals("ZZZ")) {
            return steps;
        }
        return traverseNetwork(instructions, network, nextNode, ++steps);
    }
}
