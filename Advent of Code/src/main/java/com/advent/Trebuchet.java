//Solution for 2023-1 Advent of Code
//https://adventofcode.com/2023/day/1

package com.advent;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Trebuchet {

    public static void main(String[] args) throws Exception {
        URL resource = Trebuchet.class.getClassLoader().getResource("trebuchet.txt");
        File file = new File(resource.toURI());
        Scanner scanner = new Scanner(file);
        int answer = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            char[] chars = line.toCharArray();
            ArrayList<Character> digits = new ArrayList<Character>();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    digits.add(c);
                }
            }
            char[] comb = {digits.getFirst(), digits.getLast()};
            int num = Integer.parseInt(new String(comb));
            System.out.println("Adding " + num + " to " + answer + ": " + (num + answer));
            answer += num;
        }
        scanner.close();
    }
}
