package task;

import data.*;
import enums.StationType;

import java.time.LocalDate;
import java.util.*;

public class Statistic {

    private Set<StationType> countPowerMoreFifty;
    private double[] avgPower;
    private int sumPower;

    public Statistic() {
        long time = System.currentTimeMillis();
        recount(Generator.generateNotesList(17435));
        System.out.print("\nLinkedList: " + (System.currentTimeMillis() - time) + "\n");
        System.out.print(this);
        time = System.currentTimeMillis();
        recount(Generator.generateNotesArray(17435));
        System.out.print("\nArrayList: " + (System.currentTimeMillis() - time) + "\n");
        System.out.print(this);
    }

    public void recount(List<Note> list) {
        recountCountMoreFifty(list);
        recountAvgPowerForLastThreeMonth(list);
        recountLastYearSumPower(list);
    }

    public void recountCountMoreFifty(List<Note> list) {
        countPowerMoreFifty = new HashSet<StationType>();
        for (Note note : list) {
            if (note.getPower() > 50) {
                countPowerMoreFifty.add(note.getType());
            }
        }
    }

    public void recountAvgPowerForLastThreeMonth(List<Note> list) {
        int countTypes = StationType.values().length;
        avgPower = new double[countTypes];
        int[] avgCount = new int[countTypes];
        for (Note note : list) {
            LocalDate date = note.getDate();
            if (date.getMonthValue() > 9 && date.getYear() == 2022) {
                avgCount[note.getType().ordinal()]++;
                avgPower[note.getType().ordinal()] += note.getPower();
            }
        }

        for (int i = 0; i < countTypes; i++) {
            if (avgCount[i] > 0) {
                avgPower[i] /= avgCount[i];
            }
        }
    }

    public void recountLastYearSumPower(List<Note> list){
        for (Note note : list) {
            if (note.getDate().getYear() == 2022) {
                sumPower += note.getPower();
            }
        }
    }

    public Set<StationType> getCountPowerMoreFifty() {
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
