package Service;

import Models.MoonshineKind;

import java.util.*;
import java.util.stream.Collectors;

public class Handler {
    private final Map<String, MoonshineKind> moonshines;

    public Handler(Map<String, MoonshineKind> moonshines) {
        this.moonshines = moonshines;
    }

    public String getStatement() {
        return moonshines.entrySet().stream()
                .map(pair -> "\nName" + pair.getKey() + "\n" + pair.getValue().getInfo())
                .reduce("", (partialInfoResult, info) -> partialInfoResult + info);
    }

    private static Set<String> getNamesWithParameter(Map<String, Double> info) {
        double max = info.values().stream()
                .max(Double::compareTo).orElse(0.0);
        return info.entrySet().stream()
                .filter(pair -> pair.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public Set<String> getSumVolume() {
        Map<String, Double> sums = moonshines.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, pair -> pair.getValue().getSumVolume()));
        return getNamesWithParameter(sums);
    }

    public Set<String> getMaxMonth() {
        Map<String, Double> sums = moonshines.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, pair -> pair.getValue().getMaxIngredients()));
        return getNamesWithParameter(sums);
    }

    public Set<String> getAverageDays() {
        Map<String, Double> avgs = moonshines.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, pair -> pair.getValue().getSumVolume()));
        return getNamesWithParameter(avgs);
    }
}
