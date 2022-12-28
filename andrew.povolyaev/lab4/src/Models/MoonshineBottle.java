package Models;

import java.time.LocalDate;
import java.util.Objects;

public class MoonshineBottle {
    private final LocalDate date;
    private final String ingredients;
    public final double volume;
    public final int timeInDays;

    public MoonshineBottle(LocalDate date, String ingredients, double volume, int timeInDays){
        this.date = date;
        this.ingredients = ingredients;
        this.volume = volume;
        this.timeInDays = timeInDays;
    }

    @Override
    public boolean equals(Object o){
        if (o instanceof MoonshineBottle){
            return ingredients.equals(((MoonshineBottle) o).ingredients) && date.equals(((MoonshineBottle) o).date)
                    && volume == ((MoonshineBottle) o).volume && timeInDays == ((MoonshineBottle) o).timeInDays;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(date, ingredients, volume, timeInDays);
    }

    @Override
    public String toString(){
        return date.toString() + ";" + ingredients + ";" + volume + ";" + timeInDays;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getVolume() {
        return volume;
    }

    public int getTimeInDays() {
        return timeInDays;
    }

    public String getIngredients() {
        return ingredients;
    }
}
