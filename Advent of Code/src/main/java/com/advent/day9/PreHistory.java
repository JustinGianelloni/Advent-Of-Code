package com.advent.day9;

import java.util.ArrayList;

public class PreHistory extends History {

    public PreHistory(String line) {
        super(line);
    }

    @Override
    protected void extrapolateValues(ArrayList<int[]> values) {
        int value = 0;
        for (int i = values.size() - 2; i >= 0; i--) {
            value = values.get(i)[0] - value;
        }
        extrapolatedValue = value;
    }

}
