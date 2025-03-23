import java.util.Scanner;

public class doubleEvenNumber
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Ввод трехзначного числа
        System.out.print("Введите трехзначное число: ");
        int number = scanner.nextInt();

        // Проверка, что число действительно трехзначное
        if (number < 100 || number > 999)
        {
            System.out.println("Ошибка: введено не трехзначное число.");
            return;
        }

        // Разбиение числа на разряды
        int digit1 = number / 100;        // Третий разряд
        int digit2 = (number / 10) % 10;  // Второй разряд
        int digit3 = number % 10;         // Первый разряд

        // Вычисление суммы и произведения цифр
        int sum = digit1 + digit2 + digit3;
        int product = digit1 * digit2 * digit3;

        // Проверка, являются ли сумма и произведение четными
        if (sum % 2 == 0 && product % 2 == 0)
        {
            System.out.println("Число " + number + " является дважды четным.");
        }
        else
        {
            System.out.println("Число " + number + " не является дважды четным.");
        }
    }
}