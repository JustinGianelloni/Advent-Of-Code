package com.advent.day5;

import java.util.ArrayList;

public class SeedMap {

    private final Seed[] seeds;
    private int pointer = 0;

    public SeedMap(String values) {
        String[] split = values.split(": ")[1].split(" ");
        ArrayList<Seed> list = new ArrayList<>(split.length/2);
        int i = 0;
        while (i < split.length) {
            list.add(new Seed(Long.parseLong(split[i++]), Long.parseLong(split[i++])));
        }
        seeds = list.toArray(new Seed[0]);
    }

    public boolean hasNext() {
        if (!seeds[pointer].hasNext()) {
            if (pointer == seeds.length) {
                return false;
            }
            return true;
        }
        return pointer < seeds.length;
    }

    public long getNext() {
        if (!seeds[pointer].hasNext()) {
            pointer++;
        }
        return seeds[pointer].getNext();
    }
}
