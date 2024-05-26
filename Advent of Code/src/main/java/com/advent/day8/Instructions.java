package com.advent.day8;

public class Instructions {

    protected final char[] instructions;
    protected int pointer = 0;

    public Instructions(String instructions) {
        this.instructions = instructions.toCharArray();
    }

    public char next() {
        if (pointer == instructions.length) {
            pointer = 0;
        }
        return instructions[pointer++];
    }

    public void resetPointer() {
        pointer = 0;
    }
}
