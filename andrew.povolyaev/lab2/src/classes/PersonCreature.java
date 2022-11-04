package classes;

import interfaces.Person;

import java.util.Objects;

public abstract class PersonCreature implements Person {

    protected String name;
    protected int amountOfPokemons;
    protected int placeInRating;

    public PersonCreature(String name, int amountOfPokemons, int placeInRating) {
        this.name = name;
        if (amountOfPokemons > 1) {
            this.amountOfPokemons = amountOfPokemons;
        } else {
            this.amountOfPokemons = 1;
        }
        if (placeInRating < 1) {
            this.placeInRating = 1000;
        } else {
            this.placeInRating = placeInRating;
        }
    }

    public void sayHello() {
        System.out.println("Привет! Меня зовут " + name + " и я стану лучшим ловцом покемонов в мире!");
    }

    public void doHarm() {
        System.out.println("УАХАХА. Я очень злой, ведь я на " + placeInRating + " месте в рейтинге.");
    }

    public void eat() {
        System.out.println(name + " и его " + amountOfPokemons + " покемон(ы) сели покушать.");
    }

    abstract void inviteForABattle();

    public String toString() {
        return "Имя: " + name + ". Место в рейтинге: " + placeInRating + ". Кол-во покемонов: " + amountOfPokemons + ".";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        PersonCreature person = (PersonCreature) obj;

        return person.placeInRating == this.placeInRating && Objects.equals(person.name, this.name);
    }

    public int hashCode() {
        return Objects.hash(name, placeInRating);
    }
}
