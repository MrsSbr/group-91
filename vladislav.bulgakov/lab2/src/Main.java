import Classes.Humanoid;
import Classes.Human;
import Classes.Elf;
import Classes.Dwarf;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Humanoid Aragorn = new Human("Арагорн", 198, "Нуменорский");
        Humanoid Legolas = new Elf("Леголас", 184, true);
        Humanoid Boromir = new Human("Боромир", 193, "Нуменорский");
        Humanoid Gimli = new Dwarf("Гимли", 152, 60.2);

        List<Humanoid> characters = new ArrayList<>();
        characters.add(Aragorn);
        characters.add(Legolas);
        characters.add(Boromir);
        characters.add(Gimli);

        for (Humanoid chr : characters) {
            System.out.println(chr.toString());
            System.out.println("Хэш-код: " + chr.hashCode());
        }

        System.out.println("\nБоромир это Арагорн? " + Boromir.equals(Aragorn));

    }
}
