package com.advent.day5;

import java.util.ArrayList;

public class FarmMap {

    private final Range[] ranges;

    public FarmMap(String[] lines) {
        ArrayList<Range> list = new ArrayList<>(lines.length);
        for (String line : lines) {
            String[] split = line.split(" ");
            list.add(new Range(Long.parseLong(split[0]), Long.parseLong(split[1]), Long.parseLong(split[2])));
        }
        ranges = list.toArray(new Range[0]);
    }

    public long getDestination(long source) {
        for (Range range : ranges) {
            if (range.isSourceInRange(source)) {
                return range.getDestination(source);
            }
        }
        return source;
    }
}
