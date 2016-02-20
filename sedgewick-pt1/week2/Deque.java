import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node head;
    private Node tail;
    private int sz;

    /**
     * Construct an empty deque
     */
    public Deque() {
        head = null;
        tail = head;
        sz = 0;
    }

    /**
     * Is the deque empty?
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Return the number of items in the deque
     */
    public int size() {
        return sz;
    }

    /**
     * Add the item to the front
     */
    public void addFirst(Item item) {
        if (null == item)
            throw new NullPointerException();

        Node first = new Node();
        first.item = item;
        first.next = head;
        if (isEmpty()) {
            head = first;
            tail = head;
        } else {
            head.prev = first;
            head = first;
        }
        sz++;
    }


    /**
     * Add the item to the end
     */
    public void addLast(Item item) {
        if (null == item)
            throw new NullPointerException();

        Node last = new Node();
        last.item = item;
        if (isEmpty()) {
            head = last;
            tail = last;
        } else {
            last.prev = tail;
            tail.next = last;
            tail = last;
        }
        sz++;
    }

    /**
     * Remove and return the item from the front
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item first = head.item;
        head = head.next;
        if (null != head)
            head.prev = null;
        sz--;
        return first;
    }

    /**
     * Remove and return the item from the end
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        Item last = tail.item;
        tail = tail.prev;
        if (null != tail)
            tail.next = null;
        sz--;
        return last;
    }

    /**
     * Return an iterator over items from front to end
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        public boolean hasNext() {
            return null != current;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        Deque<Integer> dq = new Deque<>();
        dq.addLast(1);
        dq.addLast(2);
        dq.addLast(3);
        dq.addLast(4);
        dq.addLast(5);
        for (int s : dq) {
            System.out.println(s);
        }
        System.out.println();
        System.out.println("Size is: " + dq.size());
        System.out.println(dq.removeFirst());
        System.out.println("Size is: " + dq.size());
        System.out.println(dq.removeFirst());
        System.out.println("Size is: " + dq.size());
        System.out.println(dq.removeFirst());
        System.out.println("Size is: " + dq.size());
        System.out.println(dq.removeFirst());
        System.out.println("Size is: " + dq.size());
        System.out.println(dq.removeFirst());
        System.out.println("Size is: " + dq.size());
    }
}
