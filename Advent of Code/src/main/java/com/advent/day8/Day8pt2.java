//Solution for 2023-8:2 Advent of Code
//https://adventofcode.com/2023/day/8
//Author: Justin Gianelloni
//Date: May, 2024

package com.advent.day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day8pt2 {

    public static void main(String[] args) throws Exception {
        URL resource = Day8pt2.class.getClassLoader().getResource("day8.txt");
        File file = new File(resource.toURI());
        long answer = parsePage(file);
        System.out.println("The answer is: " + answer);
    }

    public static long parsePage(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Instructions instructions = new Instructions(scanner.nextLine());
        scanner.nextLine(); //skip blank line
        HashMap<String, Node> network = new HashMap<>();
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] split = line.split(" = ");
            network.put(split[0], new Node(split[1]));
        }
        ArrayList<Long> cycles = new ArrayList<>();
        for (Map.Entry<String, Node> set : network.entrySet()) {
            if (set.getKey().endsWith("A")) {
                cycles.add(traverseNetwork(instructions, network, set.getKey(), 0, false));
                instructions.resetPointer();
            }
        }
        return lcm(cycles.toArray(new Long[0]));
    }

    public static long lcm(Long[] cycles) {
        long lcm = cycles[0];
        for (int i = 1; i < cycles.length; i++) {
            long currentNumber = cycles[i];
            lcm = (lcm * currentNumber) / gcd(lcm, currentNumber);
        }
        return lcm;
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static Long traverseNetwork(Instructions instructions,
                                       HashMap<String, Node> network,
                                       String node,
                                       long steps,
                                       boolean check) {
        String nextNode = network.get(node).getElement(instructions.next());
        if (nextNode.endsWith("Z")) {
            if (!check) {
                check = true;
                steps = 0;
            } else {
                return steps;
            }
        }
        return traverseNetwork(instructions, network, nextNode, ++steps, check);
    }
}
