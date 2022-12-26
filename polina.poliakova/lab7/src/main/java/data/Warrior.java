package data;

import data.annotations.AgeRange;
import data.annotations.PrimaryKey;
import data.annotations.Table;


@Table(name = "Воины")
public record Warrior
        (@PrimaryKey int id, String name, @AgeRange int age, String weapon, Gender gender, boolean hasHorse) {

}
