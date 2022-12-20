package service;

import Movies.ComedyMovie;
import Movies.Movie;
import Movies.ScaryMovie;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    final List<Movie> movies;

    public Cinema() {

        movies = new ArrayList<Movie>();

    }

    public List<Movie> getMovies() {

        return movies;

    }


    public boolean isEmpty() {

        if (movies.size() == 0) {
            System.out.println("Кино нет!");
            return false;
        }

        return true;

    }

    public void showScary() {

        if (isEmpty()) {

            for (var movie : movies) {

                if (movie.getClass() == ScaryMovie.class) {

                    ((ScaryMovie) movie).getFear();

                }

            }
            System.out.println("Ужасов нет!");


        }
    }

    public void showComedy() {

        if (isEmpty()) {

            for (var movie : movies) {

                if (movie.getClass() == ComedyMovie.class) {

                    movie.show();
                    return;

                }

            }
            System.out.println("Комедий нет!");

        }

    }


    public void addComedy() {

        Movie movie = new ComedyMovie();
        movie.create();
        movies.add(movie);

    }

    public void addScary() {

        Movie movie = new ScaryMovie();
        movie.create();
        movies.add(movie);

    }
}
