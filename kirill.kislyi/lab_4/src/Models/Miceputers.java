package Models;


import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Miceputers {
    private static final Logger logger = Logger.getLogger(Miceputers.class.getName());
    private final List<Supermiceputer> mputers = new ArrayList<>();

    public void fileToMiceputers(String path) {
        File file = new File(path);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Supermiceputer smputer = Supermiceputer.convertStringToSPM(line);
                if (mputers == null) {
                    logger.log(Level.SEVERE, "Supermiceputer is Null");
                    break;
                }
                mputers.add(smputer);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "Файл не найден", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка чтения", e);
        }
    }

    //Найти все ответы, которые были выданы 3-мя и более суперкомпьютерами
    //Для каждого ответа найти суммарное время его вычисления
    //Вывести суперкомпьютеры в порядке возрастания их времени работы

    public HashMap<String, Integer> getAnswersMore3() {
        if (mputers.isEmpty()) {
            logger.log(Level.INFO, "Miceputers is Null");
            return null;
        }


        HashMap<String, Integer> countOfMiceputers = new HashMap<>();

        for (var miceputer : mputers) {
            Integer count = countOfMiceputers.get(miceputer.getAnswer());
            countOfMiceputers.put(miceputer.getAnswer(), 1 + (count == null ? 0 : count));
        }



        return countOfMiceputers;
    }

    public HashMap<String, Integer> getTimeForAnswers() {
        if (mputers.isEmpty()) {
            logger.log(Level.INFO, "Miceputers is Null");
            return null;
        }

        HashMap<String, Integer> answersTime = new HashMap<>();

        for (var miceputer : mputers) {
            Integer time = answersTime.get(miceputer.getAnswer());
            answersTime.put(miceputer.getAnswer(), miceputer.getTime() + (time == null ? 0 : time));
        }

        return answersTime;
    }

    public HashMap<String, Integer> getTimeForMiceputers() {
        if (mputers.isEmpty()) {
            logger.log(Level.INFO, "Miceputers is Null");
            return null;
        }
        HashMap<String, Integer> miceputersTime = new HashMap<>();
        for (var miceputer : mputers) {
            Integer time = miceputersTime.get(miceputer.getName());
            miceputersTime.put(miceputer.getName(), miceputer.getTime() + (time == null ? 0 : time));
        }

        return miceputersTime; //Здесь супермышепутеры хранятся ещё неупорядоченные
    }
}