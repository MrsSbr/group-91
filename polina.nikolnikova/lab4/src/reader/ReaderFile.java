package reader;

import classes.Olympiad;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderFile {
    private static final String DATA_PATH = "C:/пары/3 курс/1 семестр/Java/group-91/" +
            "polina.nikolnikova/lab4/files/information.txt";
    private static final String LOGGER_PATH = "C:/пары/3 курс/1 семестр/Java/group-91/" +
            "polina.nikolnikova/lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());
    private static final int SIZE = 10000;

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    public static Map<Integer, Olympiad> readFile() {
        Map<Integer, Olympiad> olympiads = new HashMap<>();
        File file = new File(DATA_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fileHandler = new FileHandler(LOGGER_PATH);
            logger.addHandler(fileHandler);

            for (int i = 0; i < SIZE; i++) {
                String line = reader.readLine();
                String[] lineParts = splitLine(line);

                int yearInWhichTheOlympiadWasHeld = Integer.parseInt(lineParts[0]);
                String subjectOnWhichTheOlympiadWasHeld = lineParts[1];
                int classForWhichTheOlympiadWasIntended = Integer.parseInt(lineParts[2]);
                String studentWhoTookTheFirstPlace = lineParts[3];
                String studentWhoTookTheSecondPlace = lineParts[4];
                String studentWhoTookTheThirdPlace = lineParts[5];

                Olympiad olympiad = new Olympiad(yearInWhichTheOlympiadWasHeld, subjectOnWhichTheOlympiadWasHeld,
                        classForWhichTheOlympiadWasIntended, studentWhoTookTheFirstPlace, studentWhoTookTheSecondPlace,
                        studentWhoTookTheThirdPlace);

                olympiads.put(i, olympiad);
            }

        } catch (IOException | NumberFormatException ioException) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом", ioException);
        }
        return olympiads;
    }
}