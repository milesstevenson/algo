package week2;

public abstract class AbstractQuizClass {

    public abstract void sort(String[] A);

    void print(String[] A) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }

    boolean less(String a, String b) {
        return a.compareTo(b) < 0;
    }

    void exch(String[] A, int i, int j) {
        String tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
