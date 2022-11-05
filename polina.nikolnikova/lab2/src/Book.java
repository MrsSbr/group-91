import java.util.Objects;

public abstract class Book {

    protected String titleOfTheBook;
    protected String author;
    protected int availability;
    protected int price;
    protected TypeOfBook type;

    Book(String titleOfTheBook, String author, int availability, int price) {
        this.titleOfTheBook = titleOfTheBook;
        this.author = author;
        this.availability = availability;
        this.price = price;
        type = TypeOfBook.TYPE;
    }

    protected String getType() {
        return "";
    }

    @Override
    public String toString() {
        return getType() + "Название книги: " + titleOfTheBook + "\nАвтор: " + author + "\nЦена: " + price + " руб" +
                "\nВ наличии " + availability + " шт";
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Book book)) {
            return false;
        }

        return this.titleOfTheBook.equals(book.titleOfTheBook) && this.author.equals(book.author) &&
                book.price == this.price && book.availability == this.availability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleOfTheBook, author, price, availability);
    }
}