package classes;

import interfaces.Cosmetic;

import java.util.Objects;

public abstract class DecorativeCosmetic implements Cosmetic {
    protected int expiration_date; //срок годности
    protected String texture;
    protected String appointment; //назначение
    protected int weight;
    protected String brand;

    public DecorativeCosmetic(int expiration_date, String texture, String appointment, int weight, String brand) {
        this.expiration_date = expiration_date;
        this.texture = texture;
        this.appointment = appointment;
        this.weight = weight;
        this.brand = brand;
    }

    public abstract void info();

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public String toString() {
        return "Expiration date: " + expiration_date + "\nTexture: " + texture + "\nAppointment: " + appointment +
                "\nWeight: " + weight + "\nBrand: " + brand;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        DecorativeCosmetic cosmetic = (DecorativeCosmetic) obj;
        return expiration_date == cosmetic.expiration_date
                && Objects.equals(texture, cosmetic.texture)
                && Objects.equals(appointment, cosmetic.appointment)
                && weight == cosmetic.weight
                && Objects.equals(brand, cosmetic.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expiration_date, texture, appointment, weight, brand);
    }

}
