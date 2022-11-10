package classes;

import java.util.Objects;

public class Dwarf extends Humanoid {

    private double beardLength;

    public Dwarf(String name, int height, double beardLength) {
        super(name, height);
        this.beardLength = beardLength;
    }

    @Override
    public void printDescription() {
        System.out.println("\nДварф по имени " + name + ", его рост " + height + ", а длина бороды " + beardLength);
    }

    @Override
    public String toString() {
        return super.toString() + "\nДлина бороды: " + beardLength;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!super.equals(obj)) {
            return false;
        }

        Dwarf dwarfObject = (Dwarf) obj;
        return name.equals(dwarfObject.name) &&
                height == dwarfObject.height &&
                beardLength == dwarfObject.beardLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), beardLength);
    }
}
