import java.util.HashMap;
import java.util.Map;

public class longestSubstringWithoutRepeating
{
    public static String longestUniqueSubstring(String s)
    {
        if (s == null || s.length() == 0)
        {
            return "";
        }

        Map<Character, Integer> charIndexMap = new HashMap<>();
        int maxLength = 0;
        int start = 0;
        int resultStart = 0;

        for (int end = 0; end < s.length(); end++)
        {
            char currentChar = s.charAt(end);

            // Если символ уже встречался и его индекс >= start, сдвигаем start
            if (charIndexMap.containsKey(currentChar) && charIndexMap.get(currentChar) >= start)
            {
                start = charIndexMap.get(currentChar) + 1;
            }

            charIndexMap.put(currentChar, end);

            // Обновляем максимальную длину и начало подстроки
            if (end - start + 1 > maxLength)
            {
                maxLength = end - start + 1;
                resultStart = start;
            }
        }

        return s.substring(resultStart, resultStart + maxLength);
    }

    public static void main(String[] args)
    {
        String input = "abcabcbb";
        System.out.println("Наибольшая подстрока без повторений: " + longestUniqueSubstring(input));

        input = "bbbbb";
        System.out.println("Наибольшая подстрока без повторений: " + longestUniqueSubstring(input));

        input = "pwwkew";
        System.out.println("Наибольшая подстрока без повторений: " + longestUniqueSubstring(input));
    }
}