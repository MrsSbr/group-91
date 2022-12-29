import models.HereticNote;
import models.HereticStatistic;
import reader.ReaderFile;

import java.util.List;
import java.util.logging.Logger;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Main {
    private static final String LOG_PATH = "D:/Вгу МОиАИС/Java_practic/Lyubopytnov_Maxim/maxim.lyubopytnov/Lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            HereticStatistic hereticStatistic = new HereticStatistic();
            List<HereticNote> hereticNotes = ReaderFile.getHereticNotesFromFile();
            logger.log(Level.INFO, "Информация из файла успешно передана и конвертирована");
            hereticStatistic.mainProcess(hereticNotes);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при обработке данных", e);
        }
        logger.log(Level.INFO, "Задача выполнена. Завершение работы");
    }
}