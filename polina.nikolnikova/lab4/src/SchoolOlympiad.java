/*Результаты ежегодный школьной олимпиады записываются в файл в следующем формате:
        Год; предмет; класс; ученик, занявший первое место; второе место; третье место.
        Найти:
        1. Список учеников, которые занимали места каждый год своего обучения в школе.
        2. Для каждого предмета, найти всех учеников, которые занимали места за последние 10 лет.
        3. Найти ученика, участвовавшего в олимпиадах по наибольшему количеству предметов.
        В задаче запрещено использовать элементы функционального программирования*/

import classes.ArchiveOfOlympiads;
import classes.ListOlympiads;
import classes.Olympiad;
import reader.ReaderFile;
import enums.ListOfSubjects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SchoolOlympiad {
    private static final String dataPath = "C:/пары/3 курс/1 семестр/Java/group-91/polina.nikolnikova/lab4/" +
            "files/information.txt";
    private static final int SIZE = 10000;
    private static final Logger logger = Logger.getLogger(SchoolOlympiad.class.getName());
    private static final String loggerPath = "C:/пары/3 курс/1 семестр/Java/group-91/polina.nikolnikova/lab4/" +
            "files/logs.txt";
    private static final String dataLoggerPath = "C:/пары/3 курс/1 семестр/Java/group-91/polina.nikolnikova/lab4/" +
            "files/dataLogs.txt";

    public static void main(String[] args) throws IOException {

        ListOlympiads listOlympiads = new ListOlympiads(new ArrayList<>(SIZE));
        listOlympiads.fillingInTheList();

        FileWriter writer = new FileWriter(dataPath, false);
        try {
            FileHandler fileHandler = new FileHandler(dataLoggerPath);
            logger.addHandler(fileHandler);
            logger.log(Level.INFO, "Добавление данных в файл");

            try {
                for (int i = 0; i < SIZE; i++) {
                    String stringContainingDataAboutOneOlympiad =
                            listOlympiads.getListOlympiadByIndex(i).getYearInWhichTheOlympiadWasHeld() + ";" +
                                    listOlympiads.getListOlympiadByIndex(i).getSubjectOnWhichTheOlympiadWasHeld() + ";"
                                    + listOlympiads.getListOlympiadByIndex(i).getClassForWhichTheOlympiadWasIntended() +
                                    ";" + listOlympiads.getListOlympiadByIndex(i).getStudentWhoTookTheFirstPlace() + ";"
                                    + listOlympiads.getListOlympiadByIndex(i).getStudentWhoTookTheSecondPlace() + ";" +
                                    listOlympiads.getListOlympiadByIndex(i).getStudentWhoTookTheThirdPlace() + "\n";

                    writer.write(stringContainingDataAboutOneOlympiad);
                }
            } finally {
                writer.flush();
                writer.close();

                logger.log(Level.INFO, "Завершение сохранения данных в файл");
            }
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Ошибка добавления данных в файл");
        }

        try {
            FileHandler fileHandler = new FileHandler(loggerPath);
            logger.addHandler(fileHandler);
            logger.log(Level.INFO, "Начало работы");

            Map<Integer, Olympiad> olympiadMap = ReaderFile.readFile();

            ArchiveOfOlympiads archiveOfOlympiads = new ArchiveOfOlympiads(olympiadMap);
            List<String> olympiadMapList =
                    archiveOfOlympiads.creatingListOfStudentsWhoHaveTakenPlacesEachYearOfTheirStudiesAtTheSchool();
            System.out.println("Список учеников, которые занимали места каждый год своего обучения в школе");

            for (String people : olympiadMapList) {
                System.out.println(people);
            }

            for (int i = 0; i < ListOfSubjects.values().length; i++) {
                olympiadMapList = archiveOfOlympiads.creatingListOfStudentsWhoHaveOccupiedPlacesInTheLast10Years(i);
                System.out.println("Для предмета " + ListOfSubjects.getById(i) + " список всех учеников, которые" +
                        "занимали места за последние 10 лет");

                for (String people : olympiadMapList) {
                    System.out.println(people);
                }
            }

            for (int i = 2000; i < 2023; i++) {
                olympiadMapList = archiveOfOlympiads.creatingListOfStudentsWhoParticipatedInLargeNumberOfOlympiads(i);

                System.out.println("Список учеников, участвовавших в олимпиадах по наибольшему количеству предметов в "
                        + i + "-м году");

                for (String people : olympiadMapList) {
                    System.out.println(people);
                }
            }
        } catch (IOException ioException) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом", ioException);
        }

        logger.log(Level.INFO, "Завершение работы");
    }
}