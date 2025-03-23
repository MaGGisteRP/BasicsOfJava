import java.util.Scanner;

public class boolMax
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        // Приветствие и объяснение задачи
        System.out.println("Вам нужно ввести количество дорог и информацию о туннелях на каждой дороге.");
        System.out.println("Программа определит, по какой дороге можно проехать с максимальной высотой грузовика.\n");

        // Чтение количества дорог
        System.out.print("Введите количество дорог: ");
        int numRoads = scanner.nextInt();
        int bestRoad = 0;
        int maxMinHeight = 0;

        // Перебор всех дорог
        for (int i = 0; i < numRoads; i++)
        {
            System.out.println("\nДорога " + (i + 1) + ":");
            System.out.print("Введите количество туннелей на этой дороге: ");
            int numTunnels = scanner.nextInt();
            int minHeight = Integer.MAX_VALUE;

            // Перебор всех туннелей на текущей дороге
            for (int j = 0; j < numTunnels; j++)
            {
                System.out.print("Введите высоту туннеля " + (j + 1) + " (в см): ");
                int height = scanner.nextInt();
                if (height < minHeight)
                {
                    minHeight = height;
                }
            }

            // Если минимальная высота на текущей дороге больше, чем на предыдущих, обновляем результат
            if (minHeight > maxMinHeight)
            {
                maxMinHeight = minHeight;
                bestRoad = i + 1; // Нумерация дорог с 1
            }
        }

        // Вывод результата
        System.out.println("\nРезультат:");
        System.out.println("Рекомендуемая дорога: " + bestRoad);
        System.out.println("Максимальная высота грузовика: " + maxMinHeight + " см");
    }
}