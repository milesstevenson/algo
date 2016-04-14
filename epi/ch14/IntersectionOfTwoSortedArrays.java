package chapter14;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IntersectionOfTwoSortedArrays {

    /**
     * This approach is effective if L1 and L2 have similary lengths
     * Time complexity: O(n + m), where n is L1 length and m is L2 length
     * Additional Space: O(k), where k is the number of shared elements
     */
    public static List<Integer> computeIntersectionLinear(List<Integer> L1,
                                                          List<Integer> L2) {
        List<Integer> L1L2 = new ArrayList<>();
        int i = 0, j = 0, k = 0;

        while (i < L1.size() && j < L2.size()) {
            if (L1.get(i) == L2.get(j) && (k == 0 || L1L2.get(k-1) != L1.get(i))) {
                L1L2.add(L1.get(i));
                i++;
                j++;
                k++;
            }
            else if (L1.get(i) < L2.get(j)) {
                i++;
            }
            else {
                k++;
            }
        }
        return L1L2;
    }

    /**
     * Time complexity: O(mlogn), where m is the shorter length and n is larger
     * Additional space: O(k), where k is the number of elements shared between L1,L2
     */
    public static List<Integer> computeIntersection(List<Integer> L1,
                                                    List<Integer> L2) {
        List<Integer> newList = (L1.size() < L2.size()) ? generateMerging(L1, L2) : generateMerging(L2, L1);
        return newList;
    }

    private static List<Integer> generateMerging(List<Integer> L1,
                                                 List<Integer> L2) {
        List<Integer> L3 = new ArrayList<>();
        int m = L1.size();
        for (int i = 0; i < m; i++) {
            int val = L1.get(i);
            int idx = Collections.binarySearch(L2, val);
            if (idx >= 0) {
                L3.add(val);
            }
        }
        return L3;
    }

    public static void main(String[] args) {
        List<Integer> L1 = new ArrayList<>();
        List<Integer> L2 = new ArrayList<>();
        L1.add(1);
        L1.add(2);
        L1.add(3);
        L1.add(4);
        L1.add(5);
        L1.add(6);
        L1.add(7);

        L2.add(4);
        L2.add(5);
        L2.add(6);
        L2.add(7);
        L2.add(8);

        List<Integer> L3 = computeIntersection(L1, L2);
        for (Integer val : L3) {
            System.out.print(val + " ");
        }

        System.out.println();
        List<Integer> L4 = computeIntersectionLinear(L1, L2);
        for (Integer val : L4) {
            System.out.print(val + " ");
        }
    }
}
