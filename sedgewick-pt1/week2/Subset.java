import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Subset {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        String item;
        while (null != (item = StdIn.readString()))
            rq.enqueue(item);

        for (int i = 0; i < k; i++)
            StdOut.println(rq.dequeue());
    }
}
