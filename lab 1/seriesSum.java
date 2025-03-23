import java.util.Scanner;

public class seriesSum
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество чисел
        System.out.print("Введите любое натуральное (n > 0) число n: ");
        int n = scanner.nextInt();

        if (n <= 0)
        {
            System.out.println("Число n должно быть больше 0.");
            return;
        }

        int sum = 0;

        // Считываем числа и вычисляем знакочередующуюся сумму
        System.out.println("Введите " + n + " чисел:");
        for (int i = 0; i < n; i++)
        {
            int number = scanner.nextInt();

            // Если индекс четный, прибавляем, если нечетный - вычитаем
            if (i % 2 == 0)
            {
                sum += number;
            }
            else
            {
                sum -= number;
            }
        }

        System.out.println("Знакочередующаяся сумма: " + sum);
    }
}