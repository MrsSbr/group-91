package Repository;

import Models.MoonshineBottle;
import Models.MoonshineKind;

import java.io.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderFile {
    public static final String path = "andrew.povolyaev/lab4/src/Resource/info.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] splitLine(String line){
        return line.split(";");
    }

    public static Map<String, MoonshineKind> readFile() throws IOException{
        FileHandler fh = new FileHandler("andrew.povolyaev/lab4/Logs/logs.txt");
        logger.addHandler(fh);

        Map<String, MoonshineKind> moonshines = new HashMap<>();
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            while (line != null){
                String[] lineParts = splitLine(line);
                System.out.println(lineParts);
                String moonshineName = lineParts[1];

                LocalDate localDate = LocalDate.parse(lineParts[0]);
                MoonshineBottle mb = new MoonshineBottle(localDate, lineParts[2], Double.parseDouble(lineParts[3]), Integer.parseInt(lineParts[4]));
                if (moonshines.containsKey(moonshineName)){
                    moonshines.get(moonshineName).addMoonshineBootle(mb);
                } else {
                    MoonshineKind moonKind = new MoonshineKind();
                    moonKind.addMoonshineBootle(mb);
                    moonshines.put(moonshineName, moonKind);
                }
                line = reader.readLine();
            }
        }
        catch (IOException e){
                logger.log(Level.SEVERE, "file error", e);
        }
        return moonshines;
    }
}
