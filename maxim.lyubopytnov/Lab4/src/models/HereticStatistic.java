package models;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.*;

public class HereticStatistic {
    private List<HereticNote> hereticNotes;

    private int getNumConfusionByTool(String tool) {
        int toolNum = 0;
        for (HereticNote hn : hereticNotes) {
            if (hn.getSufferTool().equals(tool) && hn.getIsConfession() == 1) {
                toolNum++;
            }
        }
        return toolNum;
    }

    private Map<String, Integer> getMapNumConfusionByTool() {
        Map<String, Integer> numConfusionTool = new HashMap<>();
        String nameSufferTool;
        for (HereticNote hn : hereticNotes) {
            nameSufferTool = hn.getSufferTool();
            numConfusionTool.put(nameSufferTool, getNumConfusionByTool(nameSufferTool));
        }
        return numConfusionTool;
    }

    private LocalTime getNumTimeSuffer(String nameSuspect) {

        LocalTime timeSuffer = LocalTime.of(0, 0, 0);
        int timeInSec = 0;
        for (HereticNote hn : hereticNotes) {
            if (hn.getNameSuspect().equals(nameSuspect)) {
                timeInSec += hn.getSufferTime().getLong(ChronoField.SECOND_OF_DAY);
            }
        }
        return timeSuffer.plus(Duration.ofSeconds(timeInSec));
    }

    private Map<String, LocalTime> getTimeSufferForEverySuspect() {
        Map<String, LocalTime> timeSufferForEverySuspect = new HashMap<>();
        String nameSuspect;
        for (HereticNote hn : hereticNotes) {
            nameSuspect = hn.getNameSuspect();
            timeSufferForEverySuspect.put(nameSuspect, getNumTimeSuffer(nameSuspect)); //
        }
        return timeSufferForEverySuspect;
    }

    private Set<String> getAllSufferTools() {
        Set<String> namesTools = new HashSet<>();
        for (HereticNote hn : hereticNotes) {
            namesTools.add(hn.getSufferTool());
        }
        return namesTools;
    }

    private Set<String> getAllNamesSuspects() {
        Set<String> namesSuspects = new HashSet<>();
        for (HereticNote hn : hereticNotes) {
            namesSuspects.add(hn.getNameSuspect());
        }
        return namesSuspects;
    }

    private boolean isSufferAllTools(String name) {
        Set<String> listSufferToolsForEveryHeretic = new HashSet<>();
        for (HereticNote hn : hereticNotes) {
            if (hn.getNameSuspect().equals(name) && hn.getIsConfession() == 0) {
                listSufferToolsForEveryHeretic.add(hn.getSufferTool());
            }
        }
        return listSufferToolsForEveryHeretic.equals(getAllSufferTools());
    }

    private Set<String> getAllSuffersWithoutConfusion() {
        Set<String> resultNames = new HashSet<>();
        Set<String> names = getAllNamesSuspects();
        for (String name : names) {
            if (isSufferAllTools(name)) {
                resultNames.add(name);
            }
        }
        return resultNames;
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
