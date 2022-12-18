import classes.OlympicMedalStatistics;
import classes.SportsmenInfo;
import reader.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(MyFileReader.class.getName());
    private static final String LOG_PATH = "pavel.likhman/lab5/lab5_4/logs/logs.txt";

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            OlympicMedalStatistics statistics = new OlympicMedalStatistics();
            List<SportsmenInfo> info = MyFileReader.getSportsmanInfoListFromFile();
            logger.log(Level.INFO, "Информация из файла успешно передана и конвертирована");
            statistics.task(info);
            logger.log(Level.INFO, "Необходимая информация успешно выведена");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при обработке последовательности данных", e);
        }
        logger.log(Level.INFO, "Завершение работы");
    }
}