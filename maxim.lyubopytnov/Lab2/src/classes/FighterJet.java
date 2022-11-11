package classes;

import interfaces.AttackCity;

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
                "Процент скрытности %f\n" + // TODO: нужно добавить знак процента
                "Скорость %f\n" +
                isTakeOffToString(),
                name,
                obscurity,
                movementSpeed
        );
    }
}
