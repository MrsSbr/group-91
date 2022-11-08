package Classes;

import java.util.Objects;

public class Elf extends Humanoid {

    public boolean commonLanguageProficiency;

    public Elf(String name, int height, boolean commonLanguageProficiency) {
        super(name, height);
        this.commonLanguageProficiency = commonLanguageProficiency;
    }

    @Override
    public void printDescription() {
        System.out.println("\nЭльф по имени " + name + ", рост " + height + ", а общим языком он ");
        if (!commonLanguageProficiency) {
            System.out.print("не ");
        }
        System.out.println("владеет");
    }

    @Override
    public String toString() {
        return super.toString() + "\nВладение общим языком: " + commonLanguageProficiency;
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

        Elf elfObject = (Elf) obj;
        return name.equals(elfObject.name) &&
                height == elfObject.height &&
                commonLanguageProficiency == elfObject.commonLanguageProficiency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commonLanguageProficiency);
    }
}
