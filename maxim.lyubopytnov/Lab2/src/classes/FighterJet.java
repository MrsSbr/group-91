package classes;

import interfaces.AttackCity;

import java.util.Objects;

// Истребитель
public class FighterJet extends Plane implements AttackCity {
    // Скорость полета
    private final float movementSpeed;

    public FighterJet(String name, float obscurity, float movementSpeed) {
        super(name, obscurity);
        this.movementSpeed = movementSpeed;
    }

    @Override
    public void nameAttackCity(String cityName) {
        showInfo();
        if (isTakeOff){
            System.out.println("Вылета не было");
        }
        else {
            System.out.printf("Город %s был успешно атакован\n", cityName);
            isTakeOff = true;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "Истребитель %s\n" +
                "Процент скрытности %f\n" +
                "Скорость %f\n" +
                isTakeOffToString(),
                name,
                obscurity,
                movementSpeed
        );
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof FighterJet)) {
            return false;
        } else {

            return this.name.equalsIgnoreCase(((FighterJet) obj).name)
                    && this.obscurity == ((FighterJet) obj).obscurity
                    && this.movementSpeed == ((FighterJet) obj).movementSpeed;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(movementSpeed);
    }
}
