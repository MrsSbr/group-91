package classes;

import java.util.Objects;

public class PersonLoser extends PersonCreature {

    private final int amountOfTearsDropped;
    private final int amountOfOweMoney;

    public PersonLoser(int amountOfTearsDropped, int amountOfOweMoney, String name, int amountOfPokemons, int placeInRating) {
        super(name, amountOfPokemons, placeInRating);
        if (amountOfTearsDropped > 0) {
            this.amountOfTearsDropped = amountOfTearsDropped;
        } else {
            this.amountOfTearsDropped = 10;
        }
        if (amountOfOweMoney > 0) {
            this.amountOfOweMoney = amountOfOweMoney;
        } else {
            this.amountOfOweMoney = 1000;
        }
    }


    @Override
    public void inviteForABattle() {
        System.out.println("Меня зовут " + name + ". Я вызываю последнее место рейтинга на бой за еду!");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        PersonLoser person = (PersonLoser) obj;

        return person.placeInRating == this.placeInRating && Objects.equals(person.name, this.name)
                && person.amountOfTearsDropped == this.amountOfTearsDropped;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amountOfTearsDropped, amountOfOweMoney);
    }

    @Override
    public String toString() {
        return super.toString() + "Выплакано слез за сегодня: " + amountOfTearsDropped + ". Денег должен: " + amountOfOweMoney + ".";
    }
}
