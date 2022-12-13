package reader;

import models.Box;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;


public class ReaderFile {
    private static final Path PATH_DATA_FILE_CONST = Path.of("Анастасия.Сысуева/lab5/lab5_lab4/src/Info/BeattyBox.txt");
    private static final String LOGGER_PATH_CONST = "Анастасия.Сысуева/lab5/lab5_lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    private static String[] splitProductLine(String line) {
        return line.split(",");
    }

    public static Map<Integer, Box> readFile() {
        Map<Integer, Box> beautyBoxes = new HashMap<>();
        final boolean[] flag = new boolean[1];

        try (Stream<String> lineStream = Files.lines(PATH_DATA_FILE_CONST)) {
            FileHandler fh = new FileHandler(LOGGER_PATH_CONST);
            logger.addHandler(fh);
            List<String> lines = lineStream.collect(Collectors.toList());
            final int[] count = {0};

            lines.forEach(line -> {
                String[] lineParts = splitLine(line);
                String[] productLineParts = splitProductLine(lineParts[2]);

                int finalCount = count[0];

                if (beautyBoxes.isEmpty()) {
                    Box box = new Box();
                    for (String productLinePart : productLineParts) {
                        box.addProduct(productLinePart);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    box.getStock().addDataCountStatistics(LocalDate.parse(lineParts[0], formatter), Integer.parseInt(lineParts[1]));
                    beautyBoxes.put(finalCount, box);
                    count[0]++;

                } else {
                    flag[0] = false;
                    beautyBoxes.forEach((key, value) -> {
                        if (value.compareProduct(productLineParts) && !flag[0]) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            value.getStock().addDataCountStatistics(LocalDate.parse(lineParts[0], formatter), Integer.parseInt(lineParts[1]));
                            flag[0] = true;
                        }
                    });


                    if (!flag[0]) {
                        Box box = new Box();
                        for (String productLinePart : productLineParts) {
                            box.addProduct(productLinePart);
                        }
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                        box.getStock().addDataCountStatistics(LocalDate.parse(lineParts[0], formatter), Integer.parseInt(lineParts[1]));
                        count[0]++;
                        beautyBoxes.put(finalCount, box);
                    }

                }
            });
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом ", e);
        }

        return beautyBoxes;
    }
}
