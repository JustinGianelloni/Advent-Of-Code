package com.advent.day9;

import java.util.ArrayList;

public class History implements Runnable {

    protected final int[] initial_values;
    protected int extrapolatedValue;

    public History(String line) {
        String[] split = line.split(" ");
        int[] values = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            values[i] = Integer.parseInt(split[i]);
        }
        this.initial_values = values;
    }

    protected void analyzeHistory(ArrayList<int[]> values) {
        boolean finished = true;
        int[] bottomRow = values.getLast();
        int[] newRow = new int[bottomRow.length-1];
        for (int i = 0; i < bottomRow.length-1; i++) {
            newRow[i] = bottomRow[i+1] - bottomRow[i];
            if (newRow[i] != 0) {
                finished = false;
            }
        }
        values.add(newRow);
        if (!finished) {
            analyzeHistory(values);
        }
    }

    protected void extrapolateValues(ArrayList<int[]> values) {
        int value = 0;
        for (int i = values.size() - 2; i >= 0; i--) {
            value = values.get(i)[values.get(i).length-1] + value;
        }
        extrapolatedValue = value;
    }

    @Override
    public void run() {
        ArrayList<int[]> values = new ArrayList<>();
        values.add(initial_values);
        analyzeHistory(values);
        extrapolateValues(values);
    }

    public int getExtrapolatedValue() {
        return extrapolatedValue;
    }
}
