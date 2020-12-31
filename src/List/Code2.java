package List;

public class Code2 {
    public class ListNode{
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode res = new ListNode(0);
        ListNode q=l1,p=l2,curr=res;
        int carry=0;
        while (p!=null || q!=null){
            int x = (q!=null)?q.val:0;
            int y = (p!=null) ? p.val : 0;
            int sum = x+y+carry;
            carry= sum/10;
            curr.next = new ListNode(sum%10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry>0){
            curr.next = new ListNode(carry);
        }
        return res.next;
    }
    public static void main(String[] args) {

    }
}
