import classes.SalesAccounting;
import classes.BedSheetInfo;
import reader.MyFileReader;

import java.io.IOException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(MyFileReader.class.getName());
    private static final String LOG_PATH = "ekaterina.biryukova/lab4/logs/logs.txt";

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            SalesAccounting accounting = new SalesAccounting();
            List<BedSheetInfo> info = MyFileReader.getBedSheetInfoListFromFile();
            logger.log(Level.INFO, "Информация из файла успешно передана и конвертирована");
            accounting.task(info);
            logger.log(Level.INFO, "Необходимая информация успешно выведена");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при обработке последовательности данных", e);
        }
        logger.log(Level.INFO, "Завершение работы");
    }
}