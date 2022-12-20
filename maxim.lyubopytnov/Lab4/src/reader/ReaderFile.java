package reader;

import models.HereticNote;

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
    private static final String FILE_PATH = "maxim.lyubopytnov/Lab4/src/resources/hereticNotes.txt";
    private static final String LOG_PATH = "maxim.lyubopytnov/Lab4/logs/logs.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] getSplitterString(String line) {
        return line.split(";");
    }

    public static List<HereticNote> getHereticNotesFromFile() {
        List<HereticNote> hereticNotes = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);

            String fileLine = reader.readLine();
            while (fileLine != null) {
                String[] splitterInfo = getSplitterString(fileLine);
                HereticNote currentHereticNote = new HereticNote(splitterInfo[0], splitterInfo[1], splitterInfo[2], splitterInfo[3]);
                hereticNotes.add(currentHereticNote);
                fileLine = reader.readLine();
            }

            logger.log(Level.INFO, "Информация из файла успешно считана");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении из файла: ", e);
        }

        return hereticNotes;
    }
}
