package task;

import data.*;
import enums.StationType;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistic {

    private ArrayList<StationType> countPowerMoreFifty;
    private double[] avgPower;
    private int sumPower;

    public Statistic() {
        recount();
    }

    public void recount() {
        List<Note> list = Generator.generateNotes(17435 );

        int countTypes = StationType.values().length;

        boolean[] types = new boolean[countTypes];
        avgPower = new double[countTypes];
        int[] avgCount = new int[countTypes];

        for (Note note : list) {
            if (note.getPower() > 50) {
                types[note.getType().ordinal()] = true;
            }

            LocalDate date = note.getDate();
            if (date.getMonthValue() > 9 && date.getYear() == 2022) {
                avgCount[note.getType().ordinal()]++;
                avgPower[note.getType().ordinal()] += note.getPower();
            }

            if (date.getYear() == 2022) {
                sumPower += note.getPower();
            }
        }

        for (int i = 0; i < countTypes; i++) {
            if (avgCount[i] > 0) {
                avgPower[i] /= avgCount[i];
            }
        }

        countPowerMoreFifty = new ArrayList<StationType>();

        for (int i = 0; i < countTypes; i++) {
            if (types[i]) {
                countPowerMoreFifty.add(StationType.values()[i]);
            }
        }
    }

    public ArrayList<StationType> getCountPowerMoreFifty() {
        return countPowerMoreFifty;
    }

    public double[] getAvgPower() {
        return avgPower;
    }

    public int getSumPower() {
        return sumPower;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "countPowerMoreFifty=" + countPowerMoreFifty +
                ", avgPower=" + Arrays.toString(avgPower) +
                ", sumPower=" + sumPower +
                '}';
    }
}
