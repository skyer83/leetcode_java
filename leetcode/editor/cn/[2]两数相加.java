//给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。 
//
// 请你将两个数相加，并以相同形式返回一个表示和的链表。 
//
// 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 
//
// 示例 1： 
// 
// 
//输入：l1 = [2,4,3], l2 = [5,6,4]
//输出：[7,0,8]
//解释：342 + 465 = 807.
// 
//
// 示例 2： 
//
// 
//输入：l1 = [0], l2 = [0]
//输出：[0]
// 
//
// 示例 3： 
//
// 
//输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
//输出：[8,9,9,9,0,0,0,1]
// 
//
// 
//
// 提示： 
//
// 
// 每个链表中的节点数在范围 [1, 100] 内 
// 0 <= Node.val <= 9 
// 题目数据保证列表表示的数字不含前导零 
// 
//
// Related Topics 递归 链表 数学 👍 11443 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        return addTwoNumbers01(l1, l2);
//        return addTwoNumbers02(l1, l2);
        return addTwoNumbers03(l1, l2);
    }

    /**
     * 过程中：0 <= ListNode.val，最终结果：0 <= ListNode.val <= 9 <br/>
     * 执行耗时:1 ms,击败了100.00% 的Java用户 <br/>
     * 内存消耗:43.5 MB,击败了78.81% 的Java用户 <br/>
     * @param l1
     * @param l2
     * @return ListNode
     * @author shenjh
     * @since 2025/8/15 9:18
     */
    public ListNode addTwoNumbers03(ListNode l1, ListNode l2) {
        l1.val = l1.val + l2.val;
        if (l1.val >= 10) {
            l1.val = l1.val - 10;
            if (l1.next == null) {
                l1.next = new ListNode(1);
            } else {
                l1.next.val = l1.next.val + 1;
            }
        }
        if (l1.next == null && l2.next == null) {
            return l1;
        }
        if (l1.next == null) {
            l1.next = new ListNode(0);
        }
        if (l2.next == null) {
            l2.next = new ListNode(0);
        }
        l1.next = addTwoNumbers03(l1.next, l2.next);
        return l1;
    }

    /**
     * 过程中：0 <= ListNode.val，最终结果：0 <= ListNode.val <= 9 <br/>
     * 执行耗时:1 ms,击败了100.00% 的Java用户 <br/>
     * 内存消耗:43.7 MB,击败了23.92% 的Java用户 <br/>
     * @param l1
     * @param l2
     * @return ListNode
     * @author shenjh
     * @since 2025/8/15 9:18
     */
    public ListNode addTwoNumbers02(ListNode l1, ListNode l2) {
        l1.val = l1.val + l2.val;
        if (l1.val >= 10) {
            l1.val = l1.val - 10;
            if (l1.next == null) {
                l1.next = new ListNode(1);
            } else {
                l1.next.val = l1.next.val + 1;
            }
            if (l2.next == null) {
                l2.next = new ListNode(0);
            }
            return new ListNode(l1.val, addTwoNumbers02(l1.next, l2.next));
        } else {
            if (l1.next == null && l2.next == null) {
                return l1;
            } else if (l1.next == null) {
                return new ListNode(l1.val, addTwoNumbers02(new ListNode(0), l2.next));
            } else if (l2.next == null) {
                return new ListNode(l1.val, addTwoNumbers02(l1.next, new ListNode(0)));
            } else {
                return new ListNode(l1.val, addTwoNumbers02(l1.next, l2.next));
            }
        }
    }

    /**
     * 过程中：0 <= ListNode.val <= 9，最终结果：0 <= ListNode.val <= 9 <br/>
     * 执行耗时:1 ms,击败了100.00% 的Java用户 <br/>
     * 内存消耗:43.5 MB,击败了89.60% 的Java用户 <br/>
     * @param l1
     * @param l2
     * @return ListNode
     * @author shenjh
     * @since 2025/8/14 17:59
     */
    public ListNode addTwoNumbers01(ListNode l1, ListNode l2) {
        ListNode l1Next = l1.next;
        ListNode l2Next = l2.next;
        int l1Val = l1.val;
        int l2Val = l2.val;
        int sum = l1Val + l2Val;
        if (sum >= 10) {
            sum = sum - 10;
            if (l1Next == null && l2Next == null) {
                return new ListNode(sum, new ListNode(1));
            } else if (l1Next == null) {
                return new ListNode(sum, addTwoNumbers(new ListNode(1), l2Next));
            } else if (l2Next == null) {
                return new ListNode(sum, addTwoNumbers(l1Next, new ListNode(1)));
            } else {
                if (l1Next.val < 9) {
                    l1Next.val = l1Next.val + 1;
                    return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
                } else if (l2Next.val < 9) {
                    l2Next.val = l2Next.val + 1;
                    return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
                } else {
                    return new ListNode(sum, addThreeNumbers(l1Next, l2Next, new ListNode(1)));
                }
            }
        } else {
            if (l1Next == null && l2Next == null) {
                return new ListNode(sum);
            } else if (l1Next == null) {
                return new ListNode(sum, addTwoNumbers(new ListNode(0), l2Next));
            } else if (l2Next == null) {
                return new ListNode(sum, addTwoNumbers(l1Next, new ListNode(0)));
            } else {
                return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
            }
        }
    }

    public ListNode addThreeNumbers(ListNode l1, ListNode l2, ListNode oneListNode) {
        ListNode l1Next = l1.next;
        ListNode l2Next = l2.next;
        int l1Val = l1.val;
        int l2Val = l2.val;
        int oneVal = oneListNode.val;
        int sum = l1Val + l2Val + oneVal;
        if (sum >= 10) {
            sum = sum - 10;
            if (l1Next == null && l2Next == null) {
                return new ListNode(sum, new ListNode(1));
            } else if (l1Next == null) {
                return new ListNode(sum, addTwoNumbers(new ListNode(1), l2Next));
            } else if (l2Next == null) {
                return new ListNode(sum, addTwoNumbers(l1Next, new ListNode(1)));
            } else {
                if (l1Next.val < 9) {
                    l1Next.val = l1Next.val + 1;
                    return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
                } else if (l2Next.val < 9) {
                    l2Next.val = l2Next.val + 1;
                    return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
                } else {
                    return new ListNode(sum, addThreeNumbers(l1Next, l2Next, new ListNode(1)));
                }
            }
        } else {
            if (l1Next == null && l2Next == null) {
                return new ListNode(sum);
            } else if (l1Next == null) {
                return new ListNode(sum, addTwoNumbers(new ListNode(0), l2Next));
            } else if (l2Next == null) {
                return new ListNode(sum, addTwoNumbers(l1Next, new ListNode(0)));
            } else {
                return new ListNode(sum, addTwoNumbers(l1Next, l2Next));
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
