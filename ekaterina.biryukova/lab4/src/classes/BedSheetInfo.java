package classes;

import java.time.LocalDate;
import java.util.Objects;

public class BedSheetInfo {
    private LocalDate date;
    private String name;
    private int size;
    private String color;
    private String material;

    public BedSheetInfo(LocalDate date, String name, int size, String color, String material) {
        this.date = date;
        this.name = name;
        this.size = size;
        this.color = color;
        this.material = material;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        BedSheetInfo info = (BedSheetInfo) obj;
        return size == info.size && name.equals(info.name) && color.equals(info.color)
                && material.equals(info.material) && date.equals(info.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, name, size, color, material);
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Name: " + name + ", Size: " + size + ", Color: " + color + ", Material: " + material;
    }

}
