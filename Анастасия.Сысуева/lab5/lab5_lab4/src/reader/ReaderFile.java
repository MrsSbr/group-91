package reader;

import models.Box;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReaderFile {
    private static final String PATH_DATA_FILE_CONST = "Анастасия.Сысуева/lab5/lab5_lab4/src/Info/BeattyBox.txt";
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
        File file = new File(PATH_DATA_FILE_CONST);
        final boolean[] flag = new boolean[1];

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler(LOGGER_PATH_CONST);
            logger.addHandler(fh);
            String line = reader.readLine();
            int count = 0;

            while (line != null) {
                String[] lineParts = splitLine(line);
                String[] productLineParts = splitProductLine(lineParts[2]);

                int finalCount = count;

                if (beautyBoxes.isEmpty()) {
                    Box box = new Box();
                    for (String productLinePart : productLineParts) {
                        box.addProduct(productLinePart);
                    }
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    box.getStock().addDataCountStatistics(LocalDate.parse(lineParts[0], formatter), Integer.parseInt(lineParts[1]));
                    beautyBoxes.put(finalCount, box);
                    count++;

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
                        count++;
                        beautyBoxes.put(finalCount, box);
                    }

                }
                line = reader.readLine();
            }
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом ", e);
        }

        return beautyBoxes;
    }
}
