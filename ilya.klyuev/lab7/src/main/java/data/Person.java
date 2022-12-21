package data;

import logic.PrimaryKey;

public record Person(@PrimaryKey String firstName, @PrimaryKey String lastName) {
}
