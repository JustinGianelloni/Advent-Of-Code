package com.advent.day8;

public class Node {

    protected final String leftElement;
    protected final String rightElement;

    public Node(String elements) {
        String[] split = elements.replaceAll("[()]", "").split(", ");
        leftElement = split[0];
        rightElement = split[1];
    }

    public String getElement(char direction) {
        if (direction == 'L') {
            return leftElement;
        }
        return rightElement;
    }
}