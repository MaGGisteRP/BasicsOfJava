public class LibraryTest
{
    public static void main(String[] args)
    {
        Library library = new Library();

        // Добавление книг
        Book book1 = new Book("1984", "George Orwell", 1949);
        Book book2 = new Book("Animal Farm", "George Orwell", 1945);
        Book book3 = new Book("The Hobbit", "J.R.R. Tolkien", 1937);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Вывод всех книг
        System.out.println("Все книги:");
        library.printAllBooks();

        // Уникальные авторы
        System.out.println("\nУникальные авторы:");
        library.printUniqueAuthors();

        // Статистика по авторам
        System.out.println("\nСтатистика авторов:");
        library.printAuthorStatistics();

        // Удаление книги
        library.removeBook(book2);
        System.out.println("\nПосле удаления 'Animal Farm':");
        library.printAllBooks();

        // Поиск книг по автору
        System.out.println("\nКниги George Orwell:");
        library.findBooksByAuthor("George Orwell").forEach(System.out::println);

        // Поиск книг по году
        System.out.println("\nКниги 1937 года:");
        library.findBooksByYear(1937).forEach(System.out::println);
    }
}