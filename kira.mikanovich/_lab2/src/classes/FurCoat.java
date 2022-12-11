package classes ;

import classes.Outerwear;

import java.util.Objects;

public class FurCoat extends Outerwear {
    private int length;

    public FurCoat(String season, String colour, String print, int size, String brand, int length) {
        super(season, colour, print, size, brand);
        this.length = length;
    }

    @Override
    public void info () {
        System.out.println("Шуба — тёплая верхняя одежда из натурального либо искусственного меха...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nLength: " + length;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof FurCoat clothes)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return length == clothes.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), length);
    }

}

