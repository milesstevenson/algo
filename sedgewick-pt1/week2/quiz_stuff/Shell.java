package week2;

public class Shell extends AbstractQuizClass {
    @Override
    public void sort(String[] A) {
        int N = A.length;
        int h = 1;

        while (h < N/3) h = h*3 + 1;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(A[j],A[j-h]); j -=h) {
                    exch(A, j, j-h);
                }
            }
            h = h/3;
            print(A);
        }
    }
}
