package classes;

import java.util.Objects;
import enums.TypeOfBook;

public final class ScientificLiterature extends Book {

    private final String scopeOfStudy;
    private final String difficultyLevel;

    public ScientificLiterature(String titleOfTheBook, String author, int availability, int price, String scopeOfStudy,
                         String difficultyLevel) {
        super(titleOfTheBook, author, availability, price);
        this.scopeOfStudy = scopeOfStudy;
        this.difficultyLevel = difficultyLevel;
        type = TypeOfBook.SCIENTIFIC;
    }

    @Override
    public String getType() {
        return "Тип книги: Научная литература\n";
    }

    @Override
    public String toString() {
        return getType() + "Название книги: " + titleOfTheBook + "\nАвтор: " + author + "\nСфера изучения: " +
                scopeOfStudy + "\nУровень сложности: " + difficultyLevel + "\nЦена: " + price + " руб" + "\nВ наличии " +
                availability + " шт";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ScientificLiterature book)) {
            return false;
        }

        return this.titleOfTheBook.equals(book.titleOfTheBook) && this.author.equals(book.author) &&
                this.scopeOfStudy.equals(book.scopeOfStudy) && this.difficultyLevel.equals(book.difficultyLevel) &&
                book.price == this.price && book.availability == this.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleOfTheBook, author, price, availability, scopeOfStudy, difficultyLevel);
    }
}