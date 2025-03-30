public class maxInRowFinder
{
    public static void main(String[] args)
    {
        // Пример использования
        int[][] array = {
                {8, 4, 2},
                {4, 2, 8},
                {5, 1, 0}
        };

        int[] maxElements = findMaxInEachRow(array);

        // Вывод результата
        for (int num : maxElements)
        {
            System.out.print(num + " ");
        }
    }

    public static int[] findMaxInEachRow(int[][] array)
    {
        if (array == null || array.length == 0)
        {
            return new int[0];
        }

        int[] result = new int[array.length];

        for (int i = 0; i < array.length; i++)
        {
            if (array[i] == null || array[i].length == 0)
            {
                result[i] = Integer.MIN_VALUE; // или можно выбрать другое значение по умолчанию
                continue;
            }

            int max = array[i][0];
            for (int j = 1; j < array[i].length; j++)
            {
                if (array[i][j] > max)
                {
                    max = array[i][j];
                }
            }
            result[i] = max;
        }

        return result;
    }
}