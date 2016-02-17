package week2;

public class Selection extends AbstractQuizClass {

    @Override
    public void sort(String[] A) {
        int N = A.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(A[j],A[min]))
                    min = j;
            }
            exch(A, i, min);
            print(A);
        }
    }
}
