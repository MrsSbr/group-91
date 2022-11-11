package classes;

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
}
