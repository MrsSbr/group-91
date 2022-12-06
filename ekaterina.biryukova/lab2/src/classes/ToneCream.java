package classes;

import java.util.Objects;

public class ToneCream extends DecorativeCosmetic {
    private int tone;

    public ToneCream(int expiration_date, String texture, String appointment, int weight, String brand, int tone) {
        super(expiration_date, texture, appointment, weight, brand);
        this.tone = tone;
    }

    public void buyToneCream() {
        System.out.println("Вы купили тональный крем " + brand);
    }

    @Override
    public void info() {
        System.out.println("Тональный крем — средство декоративной косметики для выравнивания тона кожи и коррекции её недостатков...");
    }

    @Override
    public String toString() {
        return super.toString() + "\nTone: " + tone;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToneCream cosmetic)) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        return tone == cosmetic.tone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tone);
    }
}
