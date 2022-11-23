package reader;

import models.Box;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;


public class ReaderFile {
    private static final String path = "Анастасия.Сысуева/lab4/src/Info/BeattyBox.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    private static String[] splitProductLine(String line) {
        return line.split(",");
    }

    public static Map<Integer, Box> readFile() throws IOException {
        FileHandler fh = new FileHandler("Анастасия.Сысуева/lab4/logs/logs.txt");
        logger.addHandler(fh);

        Map<Integer, Box> beautyBoxes = new HashMap<>();
        File file = new File(path);
        AtomicBoolean flag = new AtomicBoolean(false);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            AtomicReference<Box> boxNew = new AtomicReference<>(new Box());
            String line = reader.readLine();
            int count = 0;

            while (line != null) {
                String[] lineParts = splitLine(line);
                String[] productLineParts = splitProductLine(lineParts[2]);

                int finalCount = count;

                if (beautyBoxes.isEmpty()) {
                    Box box = new Box();
                    for (String productLinePart : productLineParts) {
                        box.addProduct(productLinePart);
                    }
                    DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                    Date date = new Date();

                    try {
                        date = formatter.parse(lineParts[0]);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    Calendar c = Calendar.getInstance();
                    c.setTime(date);

                    box.addDataCountStatistics(c, Integer.parseInt(lineParts[1]));
                    beautyBoxes.put(finalCount, box);
                    count++;

                } else {
                    flag.set(false);
                    beautyBoxes.forEach((key, value) -> {
                        if (Arrays.equals((value.getProductList().toArray(new String[0])), productLineParts) && !flag.get()) {
                            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                            Date date = new Date();
                            try {
                                date = formatter.parse(lineParts[0]);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            Calendar c = Calendar.getInstance();
                            c.setTime(date);

                            value.addDataCountStatistics(c, Integer.parseInt(lineParts[1]));
                            flag.set(true);
                        }
                    });


                    if (!flag.get()) {
                        Box box = new Box();
                        for (String productLinePart : productLineParts) {
                            box.addProduct(productLinePart);
                        }
                        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
                        Date date = new Date();
                        try {
                            date = formatter.parse(lineParts[0]);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        Calendar c = Calendar.getInstance();
                        c.setTime(date);

                        box.addDataCountStatistics(c, Integer.parseInt(lineParts[1]));
                        boxNew.set(box);
                        count++;
                        beautyBoxes.put(finalCount, boxNew.get());
                    }

                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом ", e);
        }

        return beautyBoxes;
    }
}
