public class mergeSortedArrays
{
    public static int[] merge(int[] arr1, int[] arr2)
    {
        int n = arr1.length;
        int m = arr2.length;
        int[] result = new int[n + m];

        int i = 0, j = 0, k = 0;

        // Обходим оба массива и выбираем меньший элемент на каждом шаге
        while (i < n && j < m)
        {
            if (arr1[i] <= arr2[j])
            {
                result[k++] = arr1[i++];
            } else
            {
                result[k++] = arr2[j++];
            }
        }

        // Копируем оставшиеся элементы из первого массива (если они есть)
        while (i < n)
        {
            result[k++] = arr1[i++];
        }

        // Копируем оставшиеся элементы из второго массива (если они есть)
        while (j < m)
        {
            result[k++] = arr2[j++];
        }

        return result;
    }

    public static void main(String[] args)
    {
        int[] arr1 = {1, 3, 5, 7};
        int[] arr2 = {2, 4, 6, 8, 10};

        int[] mergedArray = merge(arr1, arr2);

        System.out.print("Объединенный массив: ");
        for (int num : mergedArray)
        {
            System.out.print(num + " ");
        }
    }
}