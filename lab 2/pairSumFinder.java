import java.util.HashMap;
import java.util.Map;

public class pairSumFinder
{
    public static int[] findPairWithSum(int[] nums, int target)
    {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++)
        {
            int complement = target - nums[i];
            if (numMap.containsKey(complement))
            {
                return new int[]{complement, nums[i]};
            }
            numMap.put(nums[i], i);
        }

        return null;
    }

    public static void main(String[] args)
    {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = findPairWithSum(nums, target);

        if (result != null)
        {
            System.out.println("Пара найдена: " + result[0] + ", " + result[1]);
        }
        else
        {
            System.out.println("Пара не найдена.");
        }
    }
}