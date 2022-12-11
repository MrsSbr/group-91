package classes;

import interfaces.Clothes;

import javax.swing.text.View;
import java.util.Objects;

public abstract class Outerwear implements Clothes{
    protected String season;
    protected String colour;
    protected String print;
    protected int size;
    protected String brand;

    public Outerwear(String season, String colour, String print, int size, String brand) {
        this.season = season;
        this.colour = colour;
        this.print = print;
        this.size = size;
        this.brand = brand;
    }

    public abstract void info();

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Season: " + season + "\nColour: " + colour + "\nPrint: " + print +
                "\nSize: " + size + "\nBrand: " + brand;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        Outerwear clothes = (Outerwear) obj;
        return Objects.equals(season, clothes.season) && Objects.equals(colour, clothes.colour) && Objects.equals(print, clothes.print) &&
                size == clothes.size && Objects.equals(brand, clothes.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(season, colour, print, size, brand);
    }
}