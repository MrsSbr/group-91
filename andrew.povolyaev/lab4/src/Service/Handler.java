package Service;

import Models.MoonshineKind;
import java.util.*;

public class Handler {
    private final Map<String, MoonshineKind> moonshines;

    public Handler(Map<String, MoonshineKind> moonshines){
        this.moonshines = moonshines;
    }

    public String getStatement() {
        StringBuilder info = new StringBuilder();
        for (String name : moonshines.keySet()) {
            info.append("\nName ").append(name).append("\n").append(moonshines.get(name).getInfo());
        }
        return info.toString();
    }

    private static Set<String> getNamesWithParameter(Map<String,Double> info){
        double maxSum = Collections.max(info.values());
        Set<String> result = new HashSet<>();
        for (String name : info.keySet()){
            if (info.get(name) == maxSum){
                result.add((name));
            }
        }
        return result;
    }

    public Set<String> getSumVolume(){
        Map<String,Double> sums = new HashMap<>();
        for (String name : moonshines.keySet()){
            sums.put(name, moonshines.get(name).getSumVolume());
        }
        return getNamesWithParameter(sums);
    }

    public Set<String> getMaxMonth(){
        Map<String,Double> sums = new HashMap<>();
        for (String name : moonshines.keySet()){
            sums.put(name, moonshines.get(name).getMaxIngredients());
        }
        return getNamesWithParameter(sums);
    }

    public Set<Double> getAverageDays(){
        Set<Double> res= new HashSet<>();
        for (String name : moonshines.keySet()){
            res.add(moonshines.get(name).getAverageTimeInDays());
        }
        return res;
    }
}
