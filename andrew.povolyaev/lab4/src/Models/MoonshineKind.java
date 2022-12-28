package Models;

import java.util.ArrayList;
import java.util.List;

public class MoonshineKind {
    private final List<MoonshineBottle> moonshineBottleList = new ArrayList<>();

    public void addMoonshineBootle(MoonshineBottle m){
        moonshineBottleList.add(m);
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof MoonshineKind){
            return moonshineBottleList.equals(((MoonshineKind) o).moonshineBottleList);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return moonshineBottleList.hashCode();
    }

    public String getInfo(){
        StringBuilder info = new StringBuilder();
        for (MoonshineBottle m : moonshineBottleList){
            info.append(m.toString()).append("\n");
        }
        return info.toString();
    }

    public double getSumVolume(){
        double sum = 0;
        for (MoonshineBottle m : moonshineBottleList){
            sum += m.getVolume();
        }
        return sum;
    }

    public double getAverageTimeInDays(){
        double sum = 0;
        for (MoonshineBottle m : moonshineBottleList){
            sum += m.getTimeInDays();
        }
        return sum/moonshineBottleList.size();
    }

    public Double getMaxIngredients(){
        double max = 0.0;
        for (MoonshineBottle m : moonshineBottleList){
            int check = m.getIngredients().length();
            if (check > max){
                max = check;
            }
        }
        return max;
    }
}
