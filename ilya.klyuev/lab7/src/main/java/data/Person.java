package data;

import logic.PrimaryKey;
import logic.Table;

@Table(name = "Люди")
public record Person(@PrimaryKey String firstName, @PrimaryKey String lastName) {
}
