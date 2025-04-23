package ui;

import model.User;
import service.AuthService;

import java.util.Scanner;

public class LoginMenu
{
    private final AuthService authService;
    private final Scanner scanner;

    public LoginMenu(AuthService authService)
    {
        this.authService = authService;
        this.scanner = new Scanner(System.in);
    }

    public User showMenu()
    {
        while (true)
        {
            System.out.println("\n=== Кинотеатральная билетная система ===");
            System.out.println("1. Вход");
            System.out.println("2. Регистрация");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice)
            {
                case 1:
                    System.out.print("Логин: ");
                    String username = scanner.nextLine();
                    System.out.print("Пароль: ");
                    String password = scanner.nextLine();

                    User user = authService.login(username, password);
                    if (user != null)
                    {
                        return user;
                    } else
                    {
                        System.out.println("Неверный логин или пароль!");
                    }
                    break;
                case 2:
                    System.out.print("Придумайте логин: ");
                    String newUsername = scanner.nextLine();
                    System.out.print("Придумайте пароль: ");
                    String newPassword = scanner.nextLine();

                    if (authService.register(newUsername, newPassword))
                    {
                        System.out.println("Регистрация прошла успешно! Теперь вы можете войти.");
                    } else
                    {
                        System.out.println("Пользователь с таким логином уже существует!");
                    }
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор!");
            }
        }
    }
}