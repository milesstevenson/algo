package chapter13;

import java.util.HashMap;

public class PalindromicPermutation {

    /**
     * Time complexity: O(n)
     * Additional Space: O(n)
     */
    public static boolean hasAPalindromicPermutation(String s) {
        int n = s.length();
        HashMap<Character, Integer> occurranceMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (!occurranceMap.containsKey(ch)) {
                occurranceMap.put(ch, 1);
            }
            else {
                int m = occurranceMap.get(ch);
                occurranceMap.put(ch, ++m);
            }
        }

        int oddOccurances = 0;
        for (Character ch : occurranceMap.keySet()) {
            if (occurranceMap.get(ch) % 2 == 1) {
                ++oddOccurances;
            }
        }

        if (n % 2 == 0 && oddOccurances > 0) {
            return false;
        }
        else if (n % 2 == 0 && oddOccurances == 0) {
            return true;
        }
        else if (n % 2 == 1 && oddOccurances == 1) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        String s1 = "moo";
        String s2 = "abcd";
        String s3 = "bcabca";
        String s4 = "babaa";
        String s5 = "babaab";
        String s6 = "babaaa";

        System.out.println(s1 + " " + hasAPalindromicPermutation(s1));
        System.out.println(s2 + " " + hasAPalindromicPermutation(s2));
        System.out.println(s3 + " " + hasAPalindromicPermutation(s3));
        System.out.println(s4 + " " + hasAPalindromicPermutation(s4));
        System.out.println(s5 + " " + hasAPalindromicPermutation(s5));
        System.out.println(s6 + " " + hasAPalindromicPermutation(s5));
    }
}
