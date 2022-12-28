package helper;

import loger.Messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reader {

    private static final File file = new File("C:\\Users\\HUAWEI\\Desktop\\ИНСТИТУТ\\3 курс 1 семестр\\Lab_4\\src\\loger\\Messages.txt");
    private static final Logger logger = Logger.getLogger(Reader.class.getName());

    public static void readFile(Messages messages) {

        try (FileReader fr = new FileReader(file); BufferedReader reader = new BufferedReader(fr)) {

            String line = reader.readLine();

            while (line != null) {

                messages.add(line);
                line = reader.readLine();

            }

        } catch (IOException e) {
            logger.log(Level.SEVERE, "File error!", e);
        }

    }

}
