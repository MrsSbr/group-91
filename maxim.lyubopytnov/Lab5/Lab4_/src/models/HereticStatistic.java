package models;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class HereticStatistic {
    private List<HereticNote> hereticNotes;

    private Map<String, Integer> getMapNumConfusionByTool() {
        return hereticNotes.stream()
                .collect(Collectors.groupingBy(HereticNote::getSufferTool, Collectors.summingInt(hn -> hn.getIsConfession() ? 1 : 0)));
    }

    private Map<String, Long> getTimeSufferForEverySuspect() {

        return hereticNotes.stream()
                .collect(Collectors.groupingBy(HereticNote::getNameSuspect, Collectors.summingLong(hn -> hn.getSufferTime().getLong(ChronoField.SECOND_OF_DAY))));
    }

    private int getAllSufferTools() {
        Set<String> namesTools = hereticNotes.stream().map(HereticNote::getSufferTool).collect(Collectors.toSet());
        return namesTools.size();
    }

    private Set<String> getAllSuffersWithoutConfusion() { /// conf == 0
        int toolCount = getAllSufferTools();

        return hereticNotes.stream()
                .filter(HereticNote::getIsConfession)
                .collect(Collectors.groupingBy(HereticNote::getNameSuspect, Collectors.groupingBy(HereticNote::getSufferTool)))
                .entrySet().stream().filter(hn -> hn.getValue().size() == toolCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public void mainProcess(List<HereticNote> hereticNotes) {
        this.hereticNotes = hereticNotes;

        Map<String, Integer> numConfusionByTool = getMapNumConfusionByTool();
        for (Map.Entry<String, Integer> it : numConfusionByTool.entrySet()) {
            System.out.println("Инструмент: " + it.getKey() + " Кол-во признаний: " + it.getValue());
        }

        System.out.println("---");

        Map<String, Long> timeSufferForEverySuspect = getTimeSufferForEverySuspect();
        for (Map.Entry<String, Long> it : timeSufferForEverySuspect.entrySet()) {
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
