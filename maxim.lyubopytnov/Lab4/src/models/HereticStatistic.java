package models;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;

public class HereticStatistic {
    private List<HereticNote> hereticNotes;

    private Map<String, Integer> getMapNumConfusionByTool() {
        Map<String, Integer> numConfusionTool = new HashMap<>();
        String nameSufferTool;
        for (var hn : hereticNotes) {
            if (hn.getIsConfession()) {
                nameSufferTool = hn.getSufferTool();
                if (!numConfusionTool.containsKey(nameSufferTool)) {
                    numConfusionTool.put(nameSufferTool, 1);
                } else {
                    numConfusionTool.put(nameSufferTool, numConfusionTool.get(nameSufferTool) + 1);
                }
            }
        }
        return numConfusionTool;
    }

    private Map<String, LocalTime> getTimeSufferForEverySuspect() {
        Map<String, LocalTime> timeSufferForEverySuspect = new HashMap<>();
        String nameSuspect;
        LocalTime timeSuffer = LocalTime.of(0, 0, 0);
        for (HereticNote hn : hereticNotes) {
            nameSuspect = hn.getNameSuspect();
            if (!timeSufferForEverySuspect.containsKey(nameSuspect)) {
                timeSufferForEverySuspect.put(nameSuspect, timeSuffer.plus(Duration.ofSeconds(hn.getSufferTime().getLong(ChronoField.SECOND_OF_DAY))));
                timeSuffer = LocalTime.of(0, 0, 0);
            } else {
                timeSufferForEverySuspect.put(nameSuspect, timeSufferForEverySuspect.get(nameSuspect).plus(Duration.ofSeconds(hn.getSufferTime().getLong(ChronoField.SECOND_OF_DAY))));
            }
        }
        return timeSufferForEverySuspect;
    }

    private int getAllSufferTools() {
        Set<String> namesTools = new HashSet<>();
        for (HereticNote hn : hereticNotes) {
            namesTools.add(hn.getSufferTool());
        }
        return namesTools.size();
    }

    private Set<String> getAllSuffersWithoutConfusion() {
        Set<String> heretics = new HashSet<>();
        int toolCount = getAllSufferTools();
        for (HereticNote hn1 : hereticNotes) {
            String hereticName = hn1.getNameSuspect();
            if (!heretics.contains(hereticName) && hn1.getIsConfession()) {
                Set<String> toolsForHeretic = new HashSet<>();
                for (HereticNote hn2 : hereticNotes) {
                    if (hereticName.equals(hn2.getNameSuspect())) {
                        toolsForHeretic.add(hn2.getSufferTool());
                    }
                }
                if (toolsForHeretic.size() == toolCount) {
                    heretics.add(hereticName);
                }
            }
        }
        return heretics;
    }

    public void mainProcess(List<HereticNote> hereticNotes) {
        this.hereticNotes = hereticNotes;

        Map<String, Integer> numConfusionByTool = getMapNumConfusionByTool();
        for (Map.Entry<String, Integer> it : numConfusionByTool.entrySet()) {
            System.out.println("Инструмент: " + it.getKey() + " Кол-во признаний: " + it.getValue());
        }

        System.out.println("---");

        Map<String, LocalTime> timeSufferForEverySuspect = getTimeSufferForEverySuspect();
        for (Map.Entry<String, LocalTime> it : timeSufferForEverySuspect.entrySet()) {
            System.out.println("Подозреваемый: " + it.getKey() + " Время пыток: " + it.getValue().toString());
        }

        System.out.println("---");

        Set<String> allSuffersWithoutConfusion = getAllSuffersWithoutConfusion();
        System.out.println("Имена подозреваемых, которых пытали всемя инструментами, но так и не добились признания:");
        for (String it : allSuffersWithoutConfusion) {
            System.out.println(it);
        }
    }
}
