package data;

import enums.StationType;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {

    public static List<Note> generateNotes(int count) {
        return Stream.generate(Generator::generateNote).limit(count).collect(Collectors.toList());
    }

    public static Note generateNote() {
        return new Note(generateDate(),
                StationType.values()[generateInt(0, StationType.values().length - 1)],
                generateInt(10, 51));
    }

    public static LocalDate generateDate() {
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