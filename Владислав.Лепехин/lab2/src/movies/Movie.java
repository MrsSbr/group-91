package movies;

import java.util.Objects;

public abstract class Movie implements Movieble {
    protected double rating;
    protected double duration;
    protected int countShow;
    public Movie() {
        rating = 0;
        duration = 0;
        countShow = 0;
    }

    @Override
    public void create() {
        rating = 5;
        duration = 180;
    }

    @Override
    public void show() {
        if (duration != 0) {
            countShow++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Movie movie = (Movie) o;
        return Double.compare(movie.rating, rating) == 0 && Double.compare(movie.duration, duration) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rating, duration);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "rating=" + rating +
                ", duration=" + duration +
                '}';
    }
}
