package service;

import model.Session;

public class TicketService {
    public boolean buyTicket(Session session, int row, int seat) {
        if (row < 1 || row > session.getHall().getRows() ||
                seat < 1 || seat > session.getHall().getSeatsPerRow()) {
            return false;
        }

        if (session.isSeatOccupied(row - 1, seat - 1)) {
            return false;
        }

        session.occupySeat(row - 1, seat - 1);
        return true;
    }

    public void viewHallLayout(Session session) {
        System.out.println("\nПлан зала (" + session.getHall().getName() + "):");
        System.out.println("Экран");
        System.out.println("┌" + "─".repeat(session.getHall().getSeatsPerRow() * 3 - 1) + "┐");

        for (int row = 0; row < session.getHall().getRows(); row++) {
            System.out.print("│");
            for (int seat = 0; seat < session.getHall().getSeatsPerRow(); seat++) {
                if (session.isSeatOccupied(row, seat)) {
                    System.out.print(" X ");
                } else {
                    System.out.print(" " + (seat + 1) + " ");
                }
            }
            System.out.println("│ Ряд " + (row + 1));
        }

        System.out.println("└" + "─".repeat(session.getHall().getSeatsPerRow() * 3 - 1) + "┘");
        System.out.println("X - занятые места");
    }
}