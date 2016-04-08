package chapter8;

public class SimpleListUtil {
    public static ListNode<Integer> search(ListNode<Integer> L, int key) {
        while (null != L) {
            if (key != L.data)
                L = L.next;
        }
        return L;
    }

    public static void insertAfter(ListNode<Integer> node, ListNode<Integer> newNode) {
        newNode.next = node.next;
        node.next = newNode;
    }

    public static void deleteList(ListNode<Integer> aNode) {
        aNode.next = aNode.next.next;
    }
}
