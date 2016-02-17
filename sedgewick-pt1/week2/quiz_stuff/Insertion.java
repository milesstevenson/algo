package week2;

public class Insertion extends AbstractQuizClass{

    @Override
    public void sort(String[] A) {
        int N = A.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0 && less(A[j], A[j-1]); j--) {
                exch(A, j, j-1);
            }
            print(A);
        }
    }
}
