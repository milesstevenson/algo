package chapter14;

import java.util.HashMap;
import java.util.Map;

public class AnonymousLetterConstruction {

    /**
     * Time complexity: O(l + m), where n is the size of letters and
     *                  m is the size of mag.
     * Additional Space: O(o + p), where p is the number of unique
     *                   characters in letters and p is the number
     *                   of unique characters in mag.
     */
    public static boolean isConstructable(String letter, String mag) {
        Map<Character, Integer> lMap = generateUniqueOccurances(letter);
        Map<Character, Integer> mMap = generateUniqueOccurances(mag);

        int len = letter.length();

        for (Character ch : lMap.keySet()) {
            if (mMap.containsKey(ch) && lMap.get(ch) <= mMap.get(ch)) {
                continue;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static Map<Character, Integer> generateUniqueOccurances(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int k = s.length();
        for (int i = 0; i < k; i++) {
            char ch = s.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            }
            else {
                int z = map.get(ch);
                map.put(ch, ++z);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        String letter1 = "abcdefghijk   ";
        String magazine1 = "abcdefghijk";

        String magazine2 = "abcdefghijkkkkk     ";

        System.out.println("First result: " + isConstructable(letter1, magazine1));
        System.out.println("First result: " + isConstructable(letter1, magazine2));
    }
}
