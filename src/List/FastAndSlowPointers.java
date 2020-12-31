package List;

import java.util.HashSet;
import java.util.Set;

//利用快慢指针解决问题
public class FastAndSlowPointers {
    /*
    Code141.
    给定一个链表，判断链表中是否有环。

    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    「快慢指针」判定链表中是否包含环
    如果链表中存在环，则返回 true 。 否则，返回 false 。
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow,fast;
        slow=fast=head;
        while (fast.next!=null&&fast!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                return true;
            }
        }
        return false;
    }
    /*
    Code142.
    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    要求返回环开始的节点
     */
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head!=null){
            if (set.contains(head)){
                return head;
            }
            set.add(head);
            head=head.next;
        }
        return null;
    }
    public ListNode detectCycle(ListNode head,int i) {
        ListNode slow,fast;
        slow=fast=head;
        //先判断fast 再 fast.next
        while (fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if (slow==fast){
                break;
            }
        }
        if (fast==null||fast.next==null){
            //遇到空指针 说明没有环
            return null;
        }
        slow=head;
        while (slow!=fast){
            slow=slow.next;
            fast=fast.next;
        }
        return slow;
    }
    /*
    给定一个头结点为 head 的非空单链表，返回链表的中间结点。
    如果有两个中间结点，则返回第二个中间结点。
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow,fast;
        slow=fast=head;
        while (fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        return slow;
    }
}
