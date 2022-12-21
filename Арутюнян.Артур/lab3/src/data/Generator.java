package data;

import enums.StationType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;

public class Generator {
    public static List<Note> generateNotesArray(int count) {
        List<Note> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateNote());
        }
        return list;
    }

    public static List<Note> generateNotesList(int count) {
        List<Note> list = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            list.add(generateNote());
        }
        return list;
    }

    public static Note generateNote() {
        return new Note(generateDate(),
                StationType.values()[generateInt(0, StationType.values().length - 1)],
                generateInt(10, 51));
    }

    public static LocalDate generateDate() {
        LocalDate date = LocalDate.now();
        return LocalDate.ofYearDay(generateInt(2010, 2022), generateInt(1, 365));
    }

    public static int generateInt(int min, int max) {
        if (max < min) {
            throw new IllegalArgumentException();
        }
        Random random = new Random();
        int randomNum = random.nextInt(max - min + 1);
        return min + randomNum;
    }
}