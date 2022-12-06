package reader;

import classes.SportsmenInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyFileReader {
    private static final String path = "pavel.likhman/lab4/src/resources/info.txt";
    private static final Logger logger = Logger.getLogger(MyFileReader.class.getName());

    private static String[] getSplitString(String line) {
        return line.split(";");
    }

    public static List<SportsmenInfo> getSportsmanInfoListFromFile() {
        List<SportsmenInfo> info = new ArrayList<>();
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            FileHandler fh = new FileHandler("pavel.likhman/lab4/logs/logs.txt");
            logger.addHandler(fh);

            String fileLine = reader.readLine();
            while (fileLine != null) {
                String[] splitInfo = getSplitString(fileLine);
                SportsmenInfo newInfo = new SportsmenInfo(splitInfo[0], splitInfo[1], splitInfo[2], Integer.parseInt(splitInfo[3]));
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