package ui;

import model.*;
import service.CinemaService;
import service.TicketService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class UserMenu
{
    private final CinemaService cinemaService;
    private final TicketService ticketService;
    private final Scanner scanner;

    public UserMenu(CinemaService cinemaService, TicketService ticketService)
    {
        this.cinemaService = cinemaService;
        this.ticketService = ticketService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu()
    {
        while (true)
        {
            System.out.println("\n=== Меню пользователя ===");
            System.out.println("1. Найти ближайший сеанс");
            System.out.println("2. Купить билет");
            System.out.println("3. Просмотреть план зала");
            System.out.println("4. Просмотреть все кинотеатры");
            System.out.println("5. Выйти из аккаунта");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    findNearestSession();
                    break;
                case 2:
                    buyTicket();
                    break;
                case 3:
                    viewHallLayout();
                    break;
                case 4:
                    viewAllCinemas();
                    break;
                case 5:
                    return;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private void findNearestSession()
    {
        if (cinemaService.getMovies().isEmpty())
        {
            System.out.println("Нет доступных фильмов!");
            return;
        }

        System.out.println("Выберите фильм:");
        for (int i = 0; i < cinemaService.getMovies().size(); i++)
        {
            System.out.println((i + 1) + ". " + cinemaService.getMovies().get(i).getTitle());
        }
        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Movie selectedMovie = cinemaService.getMovies().get(movieIndex);
        Session nearestSession = null;
        Hall nearestHall = null;
        Cinema nearestCinema = null;

        for (Cinema cinema : cinemaService.getCinemas())
        {
            for (Hall hall : cinema.getHalls())
            {
                for (Session session : hall.getSessions())
                {
                    if (session.getMovie().equals(selectedMovie) &&
                            session.getDateTime().isAfter(LocalDateTime.now()) &&
                            session.hasAvailableSeats())
                    {

                        if (nearestSession == null ||
                                session.getDateTime().isBefore(nearestSession.getDateTime()))
                        {
                            nearestSession = session;
                            nearestHall = hall;
                            nearestCinema = cinema;
                        }
                    }
                }
            }
        }

        if (nearestSession == null)
        {
            System.out.println("Нет доступных сеансов для выбранного фильма!");
        }
        else
        {
            System.out.println("\nБлижайший сеанс:");
            System.out.println("Фильм: " + nearestSession.getMovie().getTitle());
            System.out.println("Кинотеатр: " + nearestCinema.getName());
            System.out.println("Зал: " + nearestHall.getName());
            System.out.println("Дата и время: " +
                    nearestSession.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.println("Цена билета: " + nearestSession.getPrice() + " руб.");
            System.out.println("Свободных мест: " + nearestSession.getAvailableSeatsCount());
        }
    }

    private void buyTicket()
    {
        if (cinemaService.getCinemas().isEmpty())
        {
            System.out.println("Нет доступных кинотеатров!");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemaService.getCinemas().size(); i++)
        {
            System.out.println((i + 1) + ". " + cinemaService.getCinemas().get(i).getName());
        }
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Cinema cinema = cinemaService.getCinemas().get(cinemaIndex);

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.getHalls().size(); i++)
        {
            System.out.println((i + 1) + ". " + cinema.getHalls().get(i).getName());
        }
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Hall hall = cinema.getHalls().get(hallIndex);

        System.out.println("Выберите сеанс:");
        for (int i = 0; i < hall.getSessions().size(); i++)
        {
            Session session = hall.getSessions().get(i);
            System.out.println((i + 1) + ". " + session.getMovie().getTitle() +
                    ", " + session.getDateTime().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                    ", свободных мест: " + session.getAvailableSeatsCount());
        }
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Session session = hall.getSessions().get(sessionIndex);

        ticketService.viewHallLayout(session);

        System.out.print("Введите номер ряда: ");
        int row = scanner.nextInt();
        System.out.print("Введите номер места: ");
        int seat = scanner.nextInt();
        scanner.nextLine();

        if (ticketService.buyTicket(session, row, seat))
        {
            System.out.println("Билет успешно куплен!");
            System.out.println("Фильм: " + session.getMovie().getTitle());
            System.out.println("Кинотеатр: " + cinema.getName());
            System.out.println("Зал: " + hall.getName());
            System.out.println("Дата и время: " +
                    session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.println("Ряд: " + row + ", место: " + seat);
            System.out.println("Цена: " + session.getPrice() + " руб.");
        }
        else
        {
            System.out.println("Не удалось купить билет. Возможно, место уже занято или указаны неверные данные.");
        }
    }

    private void viewHallLayout()
    {
        if (cinemaService.getCinemas().isEmpty())
        {
            System.out.println("Нет доступных кинотеатров!");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemaService.getCinemas().size(); i++)
        {
            System.out.println((i + 1) + ". " + cinemaService.getCinemas().get(i).getName());
        }
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Cinema cinema = cinemaService.getCinemas().get(cinemaIndex);

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.getHalls().size(); i++)
        {
            System.out.println((i + 1) + ". " + cinema.getHalls().get(i).getName());
        }
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Hall hall = cinema.getHalls().get(hallIndex);

        System.out.println("Выберите сеанс:");
        for (int i = 0; i < hall.getSessions().size(); i++)
        {
            Session session = hall.getSessions().get(i);
            System.out.println((i + 1) + ". " + session.getMovie().getTitle() +
                    ", " + session.getDateTime().format(
                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
        }
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Session session = hall.getSessions().get(sessionIndex);
        ticketService.viewHallLayout(session);
    }

    private void viewAllCinemas()
    {
        if (cinemaService.getCinemas().isEmpty())
        {
            System.out.println("Нет доступных кинотеатров!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (Cinema cinema : cinemaService.getCinemas())
        {
            System.out.println("\nКинотеатр: " + cinema.getName());
            System.out.println("Залы:");

            if (cinema.getHalls().isEmpty())
            {
                System.out.println("  Нет залов");
            } else {
                for (Hall hall : cinema.getHalls())
                {
                    System.out.println("  " + hall.getName() +
                            " (рядов: " + hall.getRows() +
                            ", мест в ряду: " + hall.getSeatsPerRow() + ")");

                    if (!hall.getSessions().isEmpty())
                    {
                        System.out.println("    Сеансы:");
                        for (Session session : hall.getSessions())
                        {
                            System.out.println("    - " + session.getMovie().getTitle() +
                                    ", " + session.getDateTime().format(
                                    DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) +
                                    ", цена: " + session.getPrice() + " руб.");
                        }
                    }
                }
            }
        }
    }
}