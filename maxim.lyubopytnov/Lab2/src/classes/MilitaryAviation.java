package classes;

import interfaces.Aviation;

import java.util.Objects;

// Реализация интерфейса
public abstract class MilitaryAviation implements Aviation {
    // Имя воздушного средства передвижения
    protected final String name;

    // Проверяем, был ли вылет или нет (2 раза вылета не может быть, иначе не хватит топлива)
    protected boolean isTakeOff;

    public MilitaryAviation(String name) {
        this.name = name;
        isTakeOff = false;
    }

    protected String isTakeOffToString() {
        return isTakeOff ?
                "Был вылет\n" :
                "Не было вылета\n";
    }

    @Override
    public void showInfo() {
        System.out.print(this);
    }

    @Override
    public void refueling() {
        showInfo();
        if (isTakeOff) {
            System.out.println("В воздушном судне нет топлива");
        } else {
            System.out.println("В воздушном судне есть топливо");
            isTakeOff = true;
        }
    }

    @Override
    public abstract String toString();

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(this.getClass())) {
            return false;
        }
        return ((Plane) obj).name.equalsIgnoreCase(name);
    }
}
