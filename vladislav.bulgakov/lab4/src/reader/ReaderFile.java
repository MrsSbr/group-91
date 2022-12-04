package reader;

import models.Fight;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderFile {
    private static final String filePath = "vladislav.bulgakov/lab4/src/resources/fights.txt";
    private static final String logPath = "vladislav.bulgakov/lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] getSplittedString(String line) {
        return line.split(";");
    }

    public static List<Fight> getFightsListFromFile() {
        List<Fight> fights = new ArrayList<>();
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler(logPath);
            logger.addHandler(fh);

            String fileLine = reader.readLine();
            while (fileLine != null) {
                String[] splittedInfo = getSplittedString(fileLine);
                Fight currentFight = new Fight(splittedInfo[0], splittedInfo[1], splittedInfo[2], splittedInfo[3], splittedInfo[4], splittedInfo[5]);
                fights.add(currentFight);
                fileLine = reader.readLine();
            }

            logger.log(Level.INFO, "Информация из файла успешно считана");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении из файла: ", e);
        }

        return fights;
    }
}
