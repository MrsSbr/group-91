package reader;

import classes.BedSheetInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFileReader {
    private static final Logger logger = Logger.getLogger(MyFileReader.class.getName());
    private static final String FILE_PATH = "ekaterina.biryukova/lab4/src/resources/info.txt";
    private static final String LOG_PATH = "ekaterina.biryukova/lab4/logs/logs.txt";

    private static String[] getSplitString(String line) {
        return line.split(";");
    }

    public static List<BedSheetInfo> getBedSheetInfoListFromFile() {
        List<BedSheetInfo> info = new ArrayList<>();
        File file = new File(FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler(LOG_PATH);
            logger.addHandler(fh);

            String fileLine = reader.readLine();
            while (fileLine != null) {
                String[] splitInfo = getSplitString(fileLine);
                BedSheetInfo newInfo = new BedSheetInfo(LocalDate.parse(splitInfo[0]), splitInfo[1],
                        Integer.parseInt(splitInfo[2]), splitInfo[3], splitInfo[4]);
                info.add(newInfo);
                fileLine = reader.readLine();
            }
            logger.log(Level.INFO, "Информация из файла успешно считана");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка при чтении из файла: ", e);
        }
        return info;
    }
}
