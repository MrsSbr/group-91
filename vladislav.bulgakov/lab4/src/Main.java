import models.FightsStatistics;
import models.Fight;
import reader.ReaderFile;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.List;

public class Main {
    private static final String logPath = "vladislav.bulgakov/lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler(logPath);
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            FightsStatistics fs = new FightsStatistics();
            List<Fight> fights = ReaderFile.getFightsListFromFile();
            logger.log(Level.INFO, "Информация из файла успешно передана и конвертирована");
            fs.mainProcessing(fights);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при обработке последовательности данных", e);
        }
        logger.log(Level.INFO, "Задача успешно выполнена. Завершение работы");
    }
}