public class leftArrayRotator
{
    public static int[][] rotate90CounterClockwise(int[][] matrix)
    {
        if (matrix == null || matrix.length == 0)
        {
            return new int[0][0];
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // Новый массив: кол-во строк = кол-ву столбцов исходного, и наоборот
        int[][] rotated = new int[cols][rows];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < cols; j++)
            {
                rotated[cols - 1 - j][i] = matrix[i][j];
            }
        }

        return rotated;
    }

    public static void main(String[] args)
    {
        // Пример использования
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] rotated = rotate90CounterClockwise(matrix);

        // Вывод результата
        for (int[] row : rotated)
        {
            for (int num : row)
            {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}