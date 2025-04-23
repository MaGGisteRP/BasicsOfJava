package service;

import model.*;

import java.time.LocalDateTime;
import java.util.List;

public class CinemaService {
    private List<Cinema> cinemas;
    private List<Movie> movies;

    public CinemaService(List<Cinema> cinemas, List<Movie> movies) {
        this.cinemas = cinemas;
        this.movies = movies;
    }

    public void addCinema(String name) {
        cinemas.add(new Cinema(name));
    }

    public void addHallToCinema(int cinemaIndex, String hallName, int rows, int seatsPerRow) {
        if (cinemaIndex >= 0 && cinemaIndex < cinemas.size()) {
            cinemas.get(cinemaIndex).addHall(new Hall(hallName, rows, seatsPerRow));
        }
    }

    public void addMovie(String title, int duration) {
        movies.add(new Movie(title, duration));
    }

    public void createSession(int cinemaIndex, int hallIndex, int movieIndex,
                              LocalDateTime dateTime, double price) {
        if (cinemaIndex >= 0 && cinemaIndex < cinemas.size()) {
            Cinema cinema = cinemas.get(cinemaIndex);
            if (hallIndex >= 0 && hallIndex < cinema.getHalls().size()) {
                Hall hall = cinema.getHalls().get(hallIndex);
                if (movieIndex >= 0 && movieIndex < movies.size()) {
                    hall.addSession(new Session(movies.get(movieIndex), dateTime, price));
                }
            }
        }
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}