import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] a;
    private int N;

    /**
     * Construct an empty randomized queue
     */
    public RandomizedQueue() {
        a = (Item[]) new Object[1];
        N = 0;
    }

    /**
     * Is the queue empty?
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * Return the number of items in the queue
     */
    public int size() {
        return N;
    }


    /**
     * Add the item
     */
    public void enqueue(Item item) {
        if (null == item)
            throw new NullPointerException();
        if (N == a.length) {
            resize(2*a.length);
        }
        a[N++] = item;
    }

    /**
     * Move stack of size N <= max to a new array
     * of size max.
     */
    private void resize(int max) {
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }


    /**
     * Remove and return a 'random' item
     */
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(N);
        Item item = swap(index, N-1);
        a[N--] = null;
        if (N > 0 && N == a.length/4)
            resize(a.length/2);
        return item;
    }


    private Item swap(int i, int j) {
        Item item = a[i];
        a[i] = a[j];
        a[j] = item;
        return a[j];
    }

    /**
     * Return, but do not remove, a random item
     */
    public Item sample() {
        int index = StdRandom.uniform(N);
        Item item = a[index];
        return item;
    }


    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ListIterator implements Iterator<Item> {
        private int i;
        private Item[] copy;
        ListIterator() {
            i = 0;
            copy = (Item[]) new Object[N];
            for (int j = 0; j < N; j++) {
                copy[j] = a[j];
            }
            StdRandom.shuffle(copy);
        }

        public boolean hasNext() {
            return i < N;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return copy[i++];
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        rq.enqueue("foo");
        rq.enqueue("bar");
        rq.enqueue("baz");
        rq.enqueue("qux");
        rq.enqueue("woop");

        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.isEmpty());
    }
}
