package list;

public class Code92 {
    public class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) {val = x;}
    }
    public ListNode reverseBetween(ListNode head, int m , int n){
        //反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
        ListNode now = new ListNode();
        now.next=head;
        ListNode curr = head;
        ListNode pre = null;
        while(m>1){
            curr=curr.next;
            pre=curr;
            m--;
            n--;
        }

        while (n>1){


            n--;
        }

        return head;
    }
    //先判断 交换位置从什么地方进行  ，
    public ListNode reserseBetween(ListNode head, int m , int n){
        //如果 m=1；说明m从头开始遍历；
        if (m==1){
            //进行遍历
            return reverseN(head,n);//交换前n个 节点链表 返回之后的链表节在 head.next;
        }
        //节点进行next 知道移到m个
        head.next=reserseBetween(head.next,m-1,n-1);//先将节点移动到需要交换的第一个位置。
        return head;
    }
    private ListNode end;
    private ListNode reverseN(ListNode head, int n) {
        if (n==1){//此时n=1，表明链表只需要反转第一个， 无变化返回head
             end = head.next;//end 为尾结点  即最后放在末尾
            return head;
        }
        ListNode last = reverseN(head.next,n-1);
        head.next.next=head;
        head.next=end;

        return last;
    }
    public ListNode resverse(ListNode head){
        if (head.next==null){//base 条件   结束
            return head;
        }

        ListNode last = resverse(head.next);
        //1.
        head.next.next=head;
        //2.   错误写法
        //last.next=head;
        head.next=null;

        return last;
    }

    public static void main(String[] args) {

        
        
        
        
        
        
        
        
    }
    public ListNode resversBetweenMandN(ListNode head, int m, int n){
        if (head.next == null) {
            return head;
        }
        ListNode node = new ListNode(-1);
        node.next=head;
        ListNode cur = head;
        ListNode pre = null;
        while (m>1){
            pre=cur;
            cur=cur.next;
            m--;n--;
        }
        ListNode tail = cur;
        ListNode coin = pre;
        while(n>1){
            ListNode third = cur.next;
            cur.next.next=cur.next;

        }


        return node.next;
    }

}
