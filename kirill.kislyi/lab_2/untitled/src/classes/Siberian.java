package classes;

import interfaces.Scratching;

public class Siberian extends HomeCat implements Scratching {
    private final String color;
    public Siberian(String color, String name, int age) {
        super(name, age);
        this.color = color;
    }
    public void scratch(String name) {
        showInfo();
        System.out.printf("\n%s был поцарапан настоящим сибирским котом.\n", name);
        //isCastrated = true;
    }

    public String toString() {
        return String.format(
                "Сибирский кот %s\n"+ "Возрастом %s\n" + "Цветом %s\n" + isCastratedToString() + isBoldToString(),
                name,
                age,
                color
        );
    }
}
