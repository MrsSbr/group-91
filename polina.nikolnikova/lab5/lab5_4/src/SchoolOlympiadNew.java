import classes.ArchiveOfOlympiads;
import classes.Olympiad;
import enums.ListOfSubjects;
import reader.ReaderFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolOlympiadNew {

    private static final Logger logger = Logger.getLogger(SchoolOlympiadNew.class.getName());
    private static final String loggerPath = "polina.nikolnikova/lab5/lab5_4/logs/logs.txt";

    public static void main(String[] args) {

        try {
            FileHandler fileHandler = new FileHandler(loggerPath);
            logger.addHandler(fileHandler);
            logger.log(Level.INFO, "Начало работы");

            Map<Integer, Olympiad> olympiadMap = ReaderFile.readFile();

            ArchiveOfOlympiads archiveOfOlympiads = new ArchiveOfOlympiads(olympiadMap);
            List<String> olympiadMapList =
                    archiveOfOlympiads.creatingListOfStudentsWhoHaveTakenPlacesEachYearOfTheirStudiesAtTheSchool();

            System.out.println("Список учеников, которые занимали места каждый год своего обучения в школе");

            if (olympiadMapList.size() > 0) {
                olympiadMapList.forEach(System.out::println);
            } else {
                System.out.println("Таких учеников нет");
            }

            for (int i = 0; i < ListOfSubjects.values().length; i++) {
                olympiadMapList = archiveOfOlympiads.creatingListOfStudentsWhoHaveOccupiedPlacesInTheLast10Years(i);
                System.out.println("Для предмета " + ListOfSubjects.getById(i) + " список всех учеников, которые" +
                        "занимали места за последние 10 лет");

                if (olympiadMapList.size() > 0) {
                    olympiadMapList.forEach(System.out::println);
                }
            }

            for (int i = 2000; i < 2023; i++) {
                olympiadMapList = archiveOfOlympiads.
                        creatingListOfStudentsWhoParticipatedInLargeNumberOfOlympiads(i);

                System.out.println("Список учеников, участвовавших в олимпиадах по наибольшему количеству предметов в "
                        + i + "-м году");

                if (olympiadMapList.size() > 0) {
                    olympiadMapList.forEach(System.out::println);
                }
            }

        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом", ioException);
        }

        logger.log(Level.INFO, "Завершение работы");
    }
}