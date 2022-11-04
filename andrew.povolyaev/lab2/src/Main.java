
import classes.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonCreature person1 = new PersonChampion(10, 100000, "Ash", 123, 2);
        PersonCreature person2 = new PersonLoser(22, 1234, "Andrew", 1, 100);
        PersonCreature person3 = new PersonChampion(15, 45678, "Bob", 45, 3);

        PokemonCreature pokemon1 = new PokemonWithOwner("Ash", 3, 1, "Pikachu", 100);
        PokemonCreature pokemon2 = new PokemonWithoutOwner("Baikal", 3, 15, "qwe", 1000);
        PokemonCreature pokemon3 = new PokemonWithOwner("R...", 1.5, 9, "Myaut", 10);

        List<PersonCreature> people = new ArrayList<>();
        List<PokemonCreature> pokemons = new ArrayList<>();

        people.add(person1);
        people.add(person2);
        people.add(person3);

        pokemons.add(pokemon1);
        pokemons.add(pokemon2);
        pokemons.add(pokemon3);

        System.out.println("Info about people");

        for (PersonCreature person : people) {
            System.out.println(person.toString());
            person.sayHello();
            person.eat();
            if (person instanceof PersonLoser) {
                ((PersonLoser) person).inviteForABattle();
            }
        }

        System.out.println("Info about pokemons");

        for (PokemonCreature pokemon : pokemons) {
            System.out.println(pokemon.toString());
            pokemon.attack();
            pokemon.runAway();
            if (pokemon instanceof PokemonWithOwner) {
                ((PokemonWithOwner) pokemon).giveCriticalDamage();
            }
        }
    }
}
