package repository;

import models.DepartmentEmployees;
import models.Employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReaderFile {
    private static final String path = "ksenia.kanischeva/lab5/lab5_lab4/src/resource/info.txt";
    private static final Logger logger = Logger.getLogger(ReaderFile.class.getName());

    private static String[] splitLine(String line) {
        return line.split(";");
    }

    public static Map<Integer, DepartmentEmployees> readFile() throws IOException {
        FileHandler fh = new FileHandler("ksenia.kanischeva/lab4/logs/logs.txt");
        logger.addHandler(fh);

        Map<Integer, DepartmentEmployees> departments = new HashMap<>();
        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line = reader.readLine();
            while (line != null) {
                String[] lineParts = splitLine(line);
                int numberDep = Integer.parseInt(lineParts[0]);

                Employee e = new Employee(lineParts[1], Integer.parseInt(lineParts[2]));
                if (departments.containsKey(numberDep)) {
                    departments.get(numberDep).addEmployee(e);
                } else {
                    DepartmentEmployees depEmp = new DepartmentEmployees();
                    depEmp.addEmployee(e);
                    departments.put(numberDep, depEmp);
                }

                line = reader.readLine();

            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка работы с файлом ", e);
        }

        return departments;

    }

}
