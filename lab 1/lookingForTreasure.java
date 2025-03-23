import java.util.Scanner;

public class lookingForTreasure
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты клада (X Y):");
        int treasureX = scanner.nextInt();
        int treasureY = scanner.nextInt();

        int currentX = 0;
        int currentY = 0;

        // Счетчик выполненных указаний
        int instructionsCount = 0;

        // Минимальное количество указаний для достижения клада
        int minInstructions = Integer.MAX_VALUE;

        System.out.println("Введите указания карты (направление и шаги, завершите ввод словом 'стоп'):");

        // Цикл обработки указаний карты
        while (true)
        {
            String direction = scanner.next();

            if (direction.equals("стоп"))
            {
                System.out.println("Ввод завершен.");
                break;
            }

            // Ввод количества шагов
            int steps = scanner.nextInt();

            // Обновление текущих координат в зависимости от направления
            switch (direction)
            {
                case "север":
                    currentY += steps;
                    break;
                case "юг":
                    currentY -= steps;
                    break;
                case "восток":
                    currentX += steps;
                    break;
                case "запад":
                    currentX -= steps;
                    break;
                default:
                    System.out.println("Неизвестное направление: " + direction);
                    continue; // Пропустить неверное указание
            }

            // Увеличение счетчика выполненных указаний
            instructionsCount++;

            // Проверка, достигли ли клада
            if (currentX == treasureX && currentY == treasureY)
            {
                // Если клад найден, обновляем минимальное количество указаний
                if (instructionsCount < minInstructions)
                {
                    minInstructions = instructionsCount;
                }
            }
        }

        // Вывод результата после завершения ввода
        if (minInstructions != Integer.MAX_VALUE)
        {
            System.out.println("Минимальное количество указаний для достижения клада: " + minInstructions);
        }
        else
        {
            System.out.println("Клад не найден.");
        }

        scanner.close();
    }
}