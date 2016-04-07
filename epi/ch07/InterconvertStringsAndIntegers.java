package chapter7;

public class InterconvertStringsAndIntegers {

    /**
     * O(n) time complexity O(1) additional space
     */
    public static int StringToInteger(String s) {
        int n = s.length();
        int wholeVal = 0;

        for (int i = 0; i < n; i++) {
            int val = s.charAt(n-i-1) - '0';
            wholeVal += (val * Math.pow(10, i));
        }

        return wholeVal;
    }

    /**
     * O(k) time complexity, where k is the number of single digits
     * O(k) additional space
     */
    public static String IntegerToString(int n) {
        boolean isPositive = n >= 0 ? true : false;
        StringBuilder sb = new StringBuilder("");

        while (n > 0) {
            char ch = (char) ((n % 10) + '0');
            sb.append(ch);
            n /= 10;
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

        String s = "123";
        System.out.println(StringToInteger(s));
        System.out.println(IntegerToString(123));
    }
}
