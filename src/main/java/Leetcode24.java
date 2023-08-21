public class Leetcode24 {
    public static void main(String[] args) {

    }

    class Solution {
        public ListNode swapPairs(ListNode head) {
            ListNode resultHead = null;
            ListNode resultTail = null;

            ListNode node = head;
            while (node != null) {
                ListNode first = node;
                ListNode second = node.next;

                if (second == null) {
                    if (resultTail == null) {
                        resultHead = new ListNode(first.val);
                    } else {
                        resultTail.next = new ListNode(first.val);
                    }

                    break;
                }

                if (resultTail == null) {
                    resultTail = new ListNode(first.val);
                    resultHead = new ListNode(second.val, resultTail);
                } else {
                    resultTail.next = new ListNode(second.val, new ListNode(first.val));
                    resultTail = resultTail.next.next;
                }

                node = second.next;
            }

            return resultHead;
        }
    }

    class Solution2 {
        public ListNode swapPairs(ListNode head) {
            ListNode resultHead = null;
            ListNode resultTail = null;

            ListNode node = head;
            while (node != null) {
                ListNode first = node;
                ListNode second = node.next;

                if (second == null) {
                    if (resultHead == null) {
                        resultHead = new ListNode(first.val);
                    } else {
                        resultTail.next = new ListNode(first.val);
                    }

                    break;
                }

                if (resultHead == null) {
                    resultTail = new ListNode(first.val);
                    resultHead = new ListNode(second.val, resultTail);
                } else {
                    resultTail.next = new ListNode(second.val, new ListNode(first.val));
                    resultTail = resultTail.next.next;
                }

                node = second.next;
            }

            return resultHead;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
