package reader;

import models.Fight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class ReaderFile {
    private static final String FILE_PATH = "vladislav.bulgakov/lab5/lab4/src/resources/fights.txt";
    private static final String LOG_PATH = "vladislav.bulgakov/lab5/lab4/logs/logs.txt";
    private static final Path PATH = Paths.get(FILE_PATH);
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] getSplittedString(String line) {
        return line.split(";");
    }

    public static List<Fight> getFightsListFromFile() {
        List<Fight> fights = new ArrayList<>();

        try (Stream<String> stringsStream = Files.newBufferedReader(PATH).lines()) {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);

            List<String> strings = stringsStream.toList();
            strings.forEach(s -> {
                String[] splittedInfo = getSplittedString(s);
                Fight currentFight = new Fight(splittedInfo[0],
                        splittedInfo[1],
                        splittedInfo[2],
                        splittedInfo[3],
                        splittedInfo[4],
                        splittedInfo[5]);
                fights.add(currentFight);
            });

            logger.log(Level.INFO, "Информация из файла успешно считана");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении из файла: ", e);
        }

        return fights;
    }
}
