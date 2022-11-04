package classes;

import java.util.Objects;

public class PokemonWithoutOwner extends PokemonCreature {

    private final String areaOfLiving;
    private final int amountOfFriends;

    public PokemonWithoutOwner(String areaOfLiving, int amountOfFriends, int id, String name, int damage) {
        super(id, name, damage);
        this.areaOfLiving = areaOfLiving;
        if (amountOfFriends < 1) {
            this.amountOfFriends = 1;
        } else {
            this.amountOfFriends = amountOfFriends;
        }
    }

    @Override
    public void makeSuperCombo() {
        System.out.println(this.name + " и " + amountOfFriends + " ему подобных избили охотника на покемонов.");
    }

    @Override
    public String toString() {
        return super.toString() + "Место обитания: " + areaOfLiving + ". В стае обычно не менее " + amountOfFriends + 1 + " особей.";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        PokemonWithoutOwner pokemon = (PokemonWithoutOwner) obj;

        return pokemon.id == this.id && Objects.equals(pokemon.areaOfLiving, this.areaOfLiving);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, areaOfLiving, amountOfFriends);
    }
}
