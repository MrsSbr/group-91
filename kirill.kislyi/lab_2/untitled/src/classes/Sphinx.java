package classes;

import interfaces.Scratching;

public class Sphinx extends HomeCat implements Scratching {

    public Sphinx(String name, int age) {
        super(name, age);
        isBold = true;
    }
    public void scratch(String name) {
        showInfo();
        System.out.printf("\n%s был поцарапан лысиком.\n", name);
        //isCastrated = true;
    }

    public String toString() {
        return String.format(
          "Лысый кот %s\n" +
          "Возрастом %s\n"+
          isCastratedToString() + isBoldToString(),
          name,
          age
        );
    }
}
