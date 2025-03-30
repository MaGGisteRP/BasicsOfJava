public class maxSubarraySum
{
    public static int findMaxSubarraySum(int[] nums)
    {
        if (nums == null || nums.length == 0)
        {
            throw new IllegalArgumentException("Массив не должен быть пустым");
        }

        int maxCurrent = nums[0];
        int maxGlobal = nums[0];

        for (int i = 1; i < nums.length; i++)
        {
            maxCurrent = Math.max(nums[i], maxCurrent + nums[i]);
            if (maxCurrent > maxGlobal)
            {
                maxGlobal = maxCurrent;
            }
        }

        return maxGlobal;
    }

    public static void main(String[] args)
    {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println("Максимальная сумма подмассива: " + findMaxSubarraySum(array));
    }
}