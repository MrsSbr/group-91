package classes;

import java.util.Objects;

public class PersonChampion extends PersonCreature {

    private final int amountOfTitles;
    private final int prizeMoney;

    public PersonChampion(int amountOfTitles, int prizeMoney, String name, int amountOfPokemons, int placeInRating) {
        super(name, amountOfPokemons, placeInRating);
        if (amountOfTitles > 0) {
            this.amountOfTitles = amountOfTitles;
        } else {
            this.amountOfTitles = 1;
        }
        if (prizeMoney > 0) {
            this.prizeMoney = prizeMoney;
        } else {
            this.prizeMoney = 1;
        }
    }


    @Override
    public void inviteForABattle() {
        System.out.println("Я - великий " + name + ", который выиграл уже " + prizeMoney + "$, вызываю на поединок первое место рейтинга.");
    }

    @Override
    public String toString() {
        return super.toString() + "Кол-во титулов: " + amountOfTitles + ". Призовые: " + prizeMoney + ".";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        PersonChampion person = (PersonChampion) obj;

        return person.placeInRating == this.placeInRating
                && Objects.equals(person.name, this.name)
                && person.prizeMoney == this.prizeMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, prizeMoney, amountOfTitles);
    }
}
