package ui;

import model.*;
import service.CinemaService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AdminMenu {
    private final CinemaService cinemaService;
    private final Scanner scanner;

    public AdminMenu(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n=== Меню администратора ===");
            System.out.println("1. Добавить кинотеатр");
            System.out.println("2. Добавить зал в кинотеатр");
            System.out.println("3. Добавить фильм");
            System.out.println("4. Создать сеанс");
            System.out.println("5. Просмотреть все кинотеатры");
            System.out.println("6. Выйти из аккаунта");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Введите название кинотеатра: ");
                    String cinemaName = scanner.nextLine();
                    cinemaService.addCinema(cinemaName);
                    System.out.println("Кинотеатр успешно добавлен!");
                    break;
                case 2:
                    addHall();
                    break;
                case 3:
                    System.out.print("Введите название фильма: ");
                    String movieTitle = scanner.nextLine();
                    System.out.print("Введите продолжительность фильма (в минутах): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    cinemaService.addMovie(movieTitle, duration);
                    System.out.println("Фильм успешно добавлен!");
                    break;
                case 4:
                    createSession();
                    break;
                case 5:
                    viewAllCinemas();
                    break;
                case 6:
                    return;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }

    private void addHall() {
        if (cinemaService.getCinemas().isEmpty()) {
            System.out.println("Сначала добавьте кинотеатр!");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemaService.getCinemas().size(); i++) {
            System.out.println((i + 1) + ". " + cinemaService.getCinemas().get(i).getName());
        }

        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        System.out.print("Введите количество рядов: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество мест в ряду: ");
        int seatsPerRow = scanner.nextInt();
        scanner.nextLine();

        cinemaService.addHallToCinema(cinemaIndex, hallName, rows, seatsPerRow);
        System.out.println("Зал успешно добавлен в кинотеатр!");
    }

    private void createSession() {
        if (cinemaService.getCinemas().isEmpty()) {
            System.out.println("Нет доступных кинотеатров!");
            return;
        }

        if (cinemaService.getMovies().isEmpty()) {
            System.out.println("Нет доступных фильмов!");
            return;
        }

        System.out.println("Выберите кинотеатр:");
        for (int i = 0; i < cinemaService.getCinemas().size(); i++) {
            System.out.println((i + 1) + ". " + cinemaService.getCinemas().get(i).getName());
        }
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Cinema cinema = cinemaService.getCinemas().get(cinemaIndex);

        System.out.println("Выберите зал:");
        for (int i = 0; i < cinema.getHalls().size(); i++) {
            System.out.println((i + 1) + ". " + cinema.getHalls().get(i).getName());
        }
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Выберите фильм:");
        for (int i = 0; i < cinemaService.getMovies().size(); i++) {
            System.out.println((i + 1) + ". " + cinemaService.getMovies().get(i).getTitle());
        }
        int movieIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Введите дату и время сеанса (формат: dd.MM.yyyy HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr,
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        System.out.print("Введите цену билета: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        cinemaService.createSession(cinemaIndex, hallIndex, movieIndex, dateTime, price);
        System.out.println("Сеанс успешно создан!");
    }

    private void viewAllCinemas() {
        if (cinemaService.getCinemas().isEmpty()) {
            System.out.println("Нет доступных кинотеатров!");
            return;
        }

        System.out.println("\nСписок кинотеатров:");
        for (Cinema cinema : cinemaService.getCinemas()) {
            System.out.println("\nКинотеатр: " + cinema.getName());
            System.out.println("Залы:");

            if (cinema.getHalls().isEmpty()) {
                System.out.println("  Нет залов");
            } else {
                for (Hall hall : cinema.getHalls()) {
                    System.out.println("  " + hall.getName() +
                            " (рядов: " + hall.getRows() +
                            ", мест в ряду: " + hall.getSeatsPerRow() + ")");

                    if (!hall.getSessions().isEmpty()) {
                        System.out.println("    Сеансы:");
                        for (Session session : hall.getSessions()) {
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