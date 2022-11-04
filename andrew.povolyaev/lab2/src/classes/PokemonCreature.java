package classes;

import interfaces.Pokemon;

import java.util.Objects;

public abstract class PokemonCreature implements Pokemon {

    protected int id;
    protected String name;
    protected int damage;

    public PokemonCreature(int id, String name, int damage) {
        this.id = id;
        this.name = name;
        this.damage = damage;
    }

    public abstract void makeSuperCombo();

    public void makeSound() {
        System.out.println("Покемон издает какой-то звук");
    }

    public void attack() {
        System.out.println("Покемон " + this.name + " атакует и наносит " + this.damage + " урона");
    }

    public void runAway() {
        System.out.println("Покемон " + this.name + " испугался и куда-то убежал");
    }


    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        PokemonCreature pokemon = (PokemonCreature) obj;

        return pokemon.id == this.id;
    }

    public int hashCode() {
        return Objects.hash(id, name, damage);
    }

    public String toString() {
        return "Покемон " + this.name + "(порядковый номер в покедексе(" + this.id + ") может нанести " + this.damage + "урона. ";
    }
}
