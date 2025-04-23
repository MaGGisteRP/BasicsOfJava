import java.util.*;

public class Library
{
    private final List<Book> books = new ArrayList<>();
    private final Set<String> authors = new HashSet<>();
    private final Map<String, Integer> authorCount = new HashMap<>();

    public void addBook(Book book)
    {
        books.add(book);
        authors.add(book.getAuthor());
        authorCount.put(book.getAuthor(), authorCount.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book)
    {
        if (books.remove(book))
        {
            String author = book.getAuthor();
            updateAuthorsAndCount(author);
        }
    }

    private void updateAuthorsAndCount(String author)
    {
        boolean authorExists = books.stream().anyMatch(b -> b.getAuthor().equals(author));
        if (!authorExists)
        {
            authors.remove(author);
        }
        int count = authorCount.get(author) - 1;
        if (count == 0)
        {
            authorCount.remove(author);
        }
        else
        {
            authorCount.put(author, count);
        }
    }

    public List<Book> findBooksByAuthor(String author)
    {
        return books.stream()
                .filter(book -> book.getAuthor().equals(author))
                .toList();
    }

    public List<Book> findBooksByYear(int year)
    {
        return books.stream()
                .filter(book -> book.getYear() == year)
                .toList();
    }

    public void printAllBooks()
    {
        books.forEach(System.out::println);
    }

    public void printUniqueAuthors()
    {
        authors.forEach(System.out::println);
    }

    public void printAuthorStatistics()
    {
        authorCount.forEach((author, count) ->
                System.out.println(author + ": " + count + " books"));
    }
}