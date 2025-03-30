public class arraySumCalculator
{
    public static void main(String[] args)
    {
        // Пример использования метода
        int[][] array = {
                {57, 3, 8},
                {6, 27, 6},
                {8, 2, 0}
        };

        int sum = sumOf2DArray(array);
        System.out.println("Сумма всех элементов массива: " + sum);
    }

    // Метод для вычисления суммы всех элементов двумерного массива
    public static int sumOf2DArray(int[][] array)
    {
        int sum = 0;

        // Проходим по всем строкам массива
        for (int i = 0; i < array.length; i++)
        {
            // Проходим по всем элементам текущей строки
            for (int j = 0; j < array[i].length; j++)
            {
                sum += array[i][j];
            }
        }

        return sum;
    }
}