import UI.Menu;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Menu.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("andrew.povolyaev/lab4/Logs/logs.txt");
            logger.addHandler(fh);
            logger.log(Level.INFO, "Начало работы");

            Menu.mainMenu();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом");
        }
        logger.log(Level.INFO, "Завершение работы");
        }
}
