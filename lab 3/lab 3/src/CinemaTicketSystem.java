import model.*;
import service.*;
import ui.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CinemaTicketSystem {
    public static void main(String[] args) {
        // Инициализация данных
        List<User> users = new ArrayList<>(Arrays.asList(
                new User("admin", "admin123", true),
                new User("user", "user123", false)
        ));

        List<Cinema> cinemas = new ArrayList<>();
        List<Movie> movies = new ArrayList<>();

        // Инициализация сервисов
        AuthService authService = new AuthService(users);
        CinemaService cinemaService = new CinemaService(cinemas, movies);
        TicketService ticketService = new TicketService();

        // Инициализация меню
        LoginMenu loginMenu = new LoginMenu(authService);
        AdminMenu adminMenu = new AdminMenu(cinemaService);
        UserMenu userMenu = new UserMenu(cinemaService, ticketService);

        // Главный цикл программы
        while (true) {
            User currentUser = loginMenu.showMenu();

            if (currentUser.isAdmin()) {
                adminMenu.showMenu();
            } else {
                userMenu.showMenu();
            }
        }
    }
}