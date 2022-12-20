package models;
import java.time.LocalDate;
import java.util.Objects;

public class SellGame {
    private final String name;
    private final String genre;
    private final LocalDate date;
    private final double price;

    public SellGame(String name, String genre, LocalDate date, double price) {

        this.name = name;
        this.genre = genre;
        this.date = date;
        this.price = price;

    }



    public LocalDate getDate() {
        return date;
    }

    public String getGenre() {
        return genre;
    }
    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellGame sellGame = (SellGame) o;
        return Double.compare(sellGame.price, price) == 0 && Objects.equals(name, sellGame.name) && Objects.equals(genre, sellGame.genre) && Objects.equals(date, sellGame.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, genre, date, price);
    }

    @Override
    public String toString() {
        return "SellGame{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
