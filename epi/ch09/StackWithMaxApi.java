package chapter9;

import java.util.Deque;
import java.util.LinkedList;

public class StackWithMaxApi {
    final Deque<IntAndMax> dqStack;

    public StackWithMaxApi() {
        dqStack = new LinkedList<IntAndMax>();
    }

    public void push(int item) {
        int currentMax;

        if (dqStack.isEmpty()) {
            currentMax = item;
        }
        else {
            int previousMax = dqStack.peek().getMax();
            currentMax = Math.max(item, previousMax);
        }
        dqStack.push(new IntAndMax(item, currentMax));
    }

    public int pop() {
        if (dqStack.isEmpty())
            throw new IllegalStateException("Cannot pop from an empty stack!");

        return dqStack.pop().getItem();
    }

    public int getMax() {
        if (dqStack.isEmpty())
            throw new IllegalStateException("Cannot getMax from an empty stack!");

        return dqStack.peek().getMax();
    }

    public int peek() {
        if (dqStack.isEmpty())
            throw new IllegalStateException("Cannot peek from an empty stack!");

        return dqStack.peek().getItem();
    }

    public boolean empty() {
        return dqStack.isEmpty();
    }

    private class IntAndMax {
        private int item, max;

        public IntAndMax(int item, int max) {
            this.item = item;
            this.max = max;
        }

        public int getMax() { return max; }

        public int getItem() { return item; }
    }

    public static void main(String[] args) {
        StackWithMaxApi s = new StackWithMaxApi();
        s.push(5);
        s.push(4);
        s.push(3);
        s.push(2);
        s.push(-1);

        System.out.println(s.pop());
        System.out.println(s.getMax());
    }
}
