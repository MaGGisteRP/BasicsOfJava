package model;

import java.time.LocalDateTime;

public class Session {
    private Movie movie;
    private LocalDateTime dateTime;
    private double price;
    private Hall hall;
    private boolean[][] occupiedSeats;

    public Session(Movie movie, LocalDateTime dateTime, double price) {
        this.movie = movie;
        this.dateTime = dateTime;
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
        this.occupiedSeats = new boolean[hall.getRows()][hall.getSeatsPerRow()];
    }

    public boolean isSeatOccupied(int row, int seat) {
        return occupiedSeats[row][seat];
    }

    public void occupySeat(int row, int seat) {
        occupiedSeats[row][seat] = true;
    }

    public boolean hasAvailableSeats() {
        if (occupiedSeats == null) return true;

        for (boolean[] row : occupiedSeats) {
            for (boolean seat : row) {
                if (!seat) return true;
            }
        }
        return false;
    }

    public int getAvailableSeatsCount() {
        if (occupiedSeats == null) return hall.getRows() * hall.getSeatsPerRow();

        int count = 0;
        for (boolean[] row : occupiedSeats) {
            for (boolean seat : row) {
                if (!seat) count++;
            }
        }
        return count;
    }
}