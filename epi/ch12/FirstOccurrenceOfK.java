package chapter12;

import java.util.ArrayList;
import java.util.List;

public class FirstOccurrenceOfK {

    public static int binarySearchFirstOccurrenceOfK(List<Integer> A, int k) {
        int left = 0;
        int right = A.size()-1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) / 2);
            if (A.get(mid) < k) {
                left = mid + 1;
            } else if (A.get(mid) > k) {
                right = mid - 1;
            } else {
                index = mid;
                right = mid - 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList(){{add(1); add(2); add(2); add(4);
                                            add(5); add(7); add(7); add(7);
                                            add(9);}};

        System.out.println(binarySearchFirstOccurrenceOfK(list, 7));
    }
}
