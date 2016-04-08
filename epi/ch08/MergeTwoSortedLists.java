package chapter8;


public class MergeTwoSortedLists {
    public static ListNode<Integer> mergeSortedLists(ListNode<Integer> L1, ListNode<Integer> L2) {

        // remove heads
        ListNode<Integer> copy1 = L1.next;
        ListNode<Integer> copy2 = L2.next;

        ListNode<Integer> headNode = new ListNode<>(0, null);
        ListNode<Integer> currentNode = headNode;

        while (null != copy1 && null != copy2) {
            if (copy1.data <= copy2.data) {
                currentNode.next = copy1;
                copy1 = copy1.next;
            }
            else {
                currentNode.next = copy2;
                copy2 = copy2.next;
            }
            currentNode = currentNode.next;
        }

        // if left list is null, appent right list!
        // if that's not the case, we'll append the left list!
        if (null == copy1) {
            currentNode.next = copy2;
        }
        else {
            currentNode.next = copy1;
        }

        return headNode.next;
    }

    public static void main(String[] args) {
        ListNode<Integer> L1 = new ListNode<>(0, null);
        L1.data = null;
        ListNode<Integer> node1 = new ListNode<>(0, null);
        node1.data = 1;
        ListNode<Integer> node2 = new ListNode<>(0, null);
        node2.data = 2;
        ListNode<Integer> node3 = new ListNode<>(0, null);
        node3.data = 5;

        L1.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = null;

        ListNode<Integer> L2 = new ListNode<>(0, null);
        L2.data = null;
        ListNode<Integer> node4 = new ListNode<>(0, null);
        node4.data = 4;
        ListNode<Integer> node5 = new ListNode<>(0, null);
        node5.data = 5;
        ListNode<Integer> node6 = new ListNode<>(0, null);
        node6.data = 6;

        L2.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = null;

        // L1 and L2 are heads

        ListNode<Integer> newList = mergeSortedLists(L1, L2);
        while (null != newList) {
            System.out.println(newList.data);
            newList = newList.next;
        }
    }
}
