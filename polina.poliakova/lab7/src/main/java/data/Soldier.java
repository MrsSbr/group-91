package data;

import data.annotations.PrimaryKey;
import data.annotations.Table;

@Table(name = "Солдаты")
public record Soldier(@PrimaryKey String firstName, @PrimaryKey String lastName, String title) {

}
