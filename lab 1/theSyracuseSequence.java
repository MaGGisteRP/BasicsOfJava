import java.util.Scanner;

public class theSyracuseSequence
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Это Сиракузская последовательность\n Введите любое натуральное число n: ");
        int number = scanner.nextInt();
        int counter = 0;

        while (number != 1)
        {
            counter += 1;

            if (number % 2 == 0)
            {
                number /= 2;
            }
            else
            {
                number = 3*number+1;
            }
        }

        System.out.println(counter);
    }
}