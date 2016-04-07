package chapter6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DutchFlag1 {

    public enum Color { RED, WHITE, BLUE }

    /**
     * O(n) time and constant space complexity
     */
    public static void solutionLinear(int index, List<Color> A) {
        int smaller = 0;
        Color pivot = A.get(index);

        for (int i = smaller; i < A.size(); i++) {
            if (A.get(i).ordinal() < pivot.ordinal())
                Collections.swap(A, i, smaller++);
        }

        int larger = A.size()-1;
        for (int i = larger; i >= smaller; i--) {
            if (A.get(i).ordinal() > pivot.ordinal())
                Collections.swap(A, i, larger--);
        }
    }

    /**
     * slightly faster, but still O(n) time complexity and O(1) addition space complexity
     */
    public static void singlePassDutchNationalFlag(int index, List<Color> A) {
        int smaller = 0;
        int unknown_head = 0;
        int unknown_tail = A.size()-1;
        int larger = A.size()-1;

        int i = 0;
        Color pivot = A.get(index);

        while ((unknown_tail - unknown_head) > 0) {
            if (A.get(i).ordinal() < pivot.ordinal()) {
                Collections.swap(A, i, smaller++);
                unknown_head++;
            }
            else if (A.get(i).ordinal() == pivot.ordinal()) {
                unknown_head++;
            }
            else {
                Collections.swap(A, i, larger--);
                unknown_tail--;
            }
        }
    }

    public static void main(String[] args) {

        List<Color> c = new ArrayList<>();
        c.add(Color.BLUE);
        c.add(Color.RED);
        c.add(Color.RED);
        c.add(Color.WHITE);
        c.add(Color.WHITE);
        c.add(Color.BLUE);

        for (int i = 0; i < c.size(); i++)
            System.out.println(c.get(i));
        System.out.println();
        System.out.println();

        solutionLinear(2, c);

        for (int i = 0; i < c.size(); i++)
            System.out.println(c.get(i));
        System.out.println();
        System.out.println();
    }
}
