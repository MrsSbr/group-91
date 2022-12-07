import classes.OlympicMedalStatistics;
import classes.SportsmenInfo;
import reader.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(MyFileReader.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("pavel.likhman/lab4/logs/logs.txt");
            LOGGER.addHandler(fh);
            LOGGER.log(Level.INFO, "Начало работы");

            OlympicMedalStatistics statistics = new OlympicMedalStatistics();
            List<SportsmenInfo> info = MyFileReader.getSportsmanInfoListFromFile();
            LOGGER.log(Level.INFO, "Информация из файла успешно передана и конвертирована");
            statistics.task(info);
            LOGGER.log(Level.INFO, "Необходимая информация успешно выведена");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при обработке последовательности данных", e);
        }
        LOGGER.log(Level.INFO, "Завершение работы");
    }
}
