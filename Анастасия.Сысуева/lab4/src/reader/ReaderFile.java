package reader;

import src.models.Box;

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
    private static final String path = "Анастасия.Сысуева/lab4/src/Info/BeattyBox.txt";
    private static final String loggerPath = "Анастасия.Сысуева/lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    private static String[] splitProductLine(String line) {
        return line.split(",");
    }

    public static Map<Integer, Box> readFile() {
        Map<Integer, Box> beautyBoxes = new HashMap<>();
        File file = new File(path);
        boolean flag;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler(loggerPath);
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
                    flag = false;
                    for (Box value : beautyBoxes.values()) {
                        if (value.compareProduct(productLineParts) && !flag) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                            value.getStock().addDataCountStatistics(LocalDate.parse(lineParts[0], formatter), Integer.parseInt(lineParts[1]));
                            flag = true;
                        }
                    }


                    if (!flag) {
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
