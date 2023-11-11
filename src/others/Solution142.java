import java.util.HashMap;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


public class Solution142 {

    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return null;
        }
        HashMap<String, ListNode> map = new HashMap<>();
        ListNode currentNode = head;
        while(currentNode != null) {
            if (map.get(currentNode.toString()) == null) {
                map.put(currentNode.toString(), currentNode);
            } else {
                return map.get(currentNode.toString());
            }
            currentNode = currentNode.next;
        }
        return null;
    }
    public static void main(String[] args) {
        ListNode node0 = new ListNode(1);
        //ListNode node1 = new ListNode(2);
//        ListNode node2 = new ListNode(0);
//        ListNode node3 = new ListNode(-4);
        //node0.next = node1;
        //node1.next = node0;
//        node2.next = node3;
//        node3.next = node1;
        ListNode pos = new Solution142().detectCycle(node0);
        System.out.println(pos.val);
    }
}


