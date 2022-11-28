package classes;

import java.util.Objects;

public class Helicopter extends MilitaryAviation {
    // Время задержки в воздухе
    private final float flightTime;

    public Helicopter(String name, float flightTime){
        super(name);
        this.flightTime=flightTime;
    }

    @Override
    public void refueling() {
        showInfo();
        System.out.println("На складе нет вертолетного топлива\n");
    }

    @Override
    public String toString() {
        return String.format(
                "Вертолет %s\n" +
                "Время полета %f\n",
                name,
                flightTime
        );
    }

    @Override
    public boolean equals(Object obj){
        if (!(obj instanceof Helicopter)) {
            return false;
        } else {
            Helicopter objHelicopter = (Helicopter) obj;
            return this.name.equalsIgnoreCase(objHelicopter.name)
                    && this.flightTime == objHelicopter.flightTime;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(flightTime);
    }
}
