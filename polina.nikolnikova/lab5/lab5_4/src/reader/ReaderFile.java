package reader;

import classes.Olympiad;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReaderFile {
    private static final Path dataPath = Path.of("C:/пары/3 курс/1 семестр/Java/group-91/polina.nikolnikova/lab5/" +
            "lab5_4/files/information.txt");
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());
    private static final String loggerPath = "C:/пары/3 курс/1 семестр/Java/group-91/polina.nikolnikova/lab5/" +
            "lab5_4/files/logs.txt";
    private static final int SIZE = 10000;

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    public static Map<Integer, Olympiad> readFile() {
        Map<Integer, Olympiad> olympiads = new HashMap<>();

        try (Stream<String> lineStream = Files.lines(dataPath)) {

            FileHandler fileHandler = new FileHandler(loggerPath);
            logger.addHandler(fileHandler);
            List<String> lines = lineStream.collect(Collectors.toList());


            int[] i = {0};
            lines.forEach(line -> {

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

                olympiads.put(i[0], olympiad);
                i[0]++;
            });

        } catch (IOException | NumberFormatException ioException) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом", ioException);
        }
        return olympiads;
    }
}