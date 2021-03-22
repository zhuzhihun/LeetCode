package list;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
合并k个升序链表
给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。
 */
public class Code23 {
    public ListNode mergeKLists(ListNode[] lists){


        return null;
    }
    /*
    方法三：使用优先队列合并
思路

这个方法和前两种方法的思路有所不同，
我们需要维护当前每个链表没有被合并的元素的最前面一个，
k个链表就最多有 k个满足这样条件的元素，
每次在这些元素里面选取 val 属性最小的元素合并到答案中。
在选取最小元素的时候，我们可以用优先队列来优化这个过程。
     */
    public ListNode mergeKLists(ListNode[] lists,int queue){
        PriorityQueue<Integer> priorityQueue= new PriorityQueue<>();//小顶堆
        for (ListNode listNode:lists){//将全部节点存放到小顶堆中？
            ListNode t = listNode;
            while (t!=null){
                priorityQueue.add(t.val);
                t=t.next;
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while(!priorityQueue.isEmpty()){
            ListNode node = new ListNode(priorityQueue.poll());
            tail.next=node;
            tail=tail.next;
        }
        return head.next;
    }

    public static void main(String[] args) {
        ListNode o1 = new ListNode(-2);
        ListNode o2 = new ListNode(-1);
        ListNode o3 = new ListNode(-1);
        ListNode o4 = new ListNode(-1);
        o1.next=o2;o2.next=o3;o3.next=o4;
        ListNode o5 = new ListNode();
        ListNode[] listNodes = {o1,o5.next};
        Code23 code23=new Code23();
        ListNode o6 = code23.mergeKLists(listNodes,1);
        while (o6!=null){
            System.out.println(o6.val);
            o6=o6.next;
        }
    }
    /*
    存放到小顶堆的时候  每次只需要存放第一个节点，
    当从小顶堆中获取第一个最小之后
    将该节点的后继节点加入到小顶堆进行比较
     */
    //O(kn×logk)
    public ListNode mergeKLists(ListNode[] lists,String queue){
        PriorityQueue<ListNode> priorityQueue= new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });//小顶堆
        for (ListNode listNode:lists){//将全部节点存放到小顶堆中？
            if (listNode!=null){//只需要将第一个节点加入到小顶堆中 因为最小值必定在第一位
                priorityQueue.add(listNode);
            }
        }
        ListNode head = new ListNode();
        ListNode tail = head;
        while(!priorityQueue.isEmpty()){
            ListNode min = priorityQueue.poll();
            tail.next=min;
            if (min.next!=null){//没取出一个最小值  则将此最小值链表的后继链表的第一个值（即最小）添加到小顶堆中取进行比较
                priorityQueue.add(min.next);
            }
            tail=tail.next;
        }
        return head.next;
    }
    /*
    方法一：顺序合并
    我们可以想到一种最朴素的方法：用一个变量 ans 来维护以及合并的链表，
    第 i 次循环把第 i 个链表和 ans 合并，答案保存到 ans 中。
     */
    //O(k^2 n)
    public ListNode mergeKListsForMergerTwoLists(ListNode[] lists){
        if (lists.length<1){
            return null;
        }
        if (lists.length==1){
            return lists[0];
        }
        ListNode ans = lists[0];
        for (int i=1;i<lists.length;i++){
            ans = mergeTwoLists(ans,lists[i]);
        }
        return ans;
    }
    /*
    方法二：分治合并
思路

考虑优化方法一，用分治的方法进行合并。

将 k个链表配对并将同一对中的链表合并；
第一轮合并以后， k个链表被合并成了 k/2
个链表，平均长度为 2n/k
然后是k/4个链表，k/8个链表等等；
重复这一过程，直到我们得到了最终的有序链表。
     */
    public ListNode mergeKListsForDiv(ListNode[] lists){//O(kn×logk)
        return merge(lists,0,lists.length-1);
    }
    public ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        if (l > r) {
            return null;
        }
        int mid = (l + r) >> 1;
        return mergeTwoLists(merge(lists, l, mid), merge(lists, mid + 1, r));
    }
    /*
    合并两个有序的链表
     */
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}
