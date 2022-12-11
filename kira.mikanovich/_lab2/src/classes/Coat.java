package classes;

import java.util.Objects;

public class Coat extends Outerwear{
    private String classification;

    public Coat(String season, String colour, String print, int size, String brand, String classification) {
        super(season, colour, print, size, brand);
        this.classification = classification;
    }

    @Override
    public void info() {
        System.out.println("Пальто — разновидность мужского и женского верхнего платья для прохладной и холодной погоды....");
    }

    @Override
    public String toString() {
        return super.toString() + "\nClassification: " + classification;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coat clothes)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return Objects.equals(classification, clothes.classification);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), classification);
    }
}

