import sun.reflect.generics.tree.Tree;

import java.util.*;

class ListNode{//链表
    int val;      //节点值
    ListNode next;//后继节点引用
    ListNode(){}
    ListNode(int x){val=x;}
}
class Node{
    public int val;
    public Node left;
    public Node right;
    public Node next;
    public Node(){}
    public Node(int _val){
        val=_val;
    }
}


class ErListNode{//链表
    int val;      //节点值
    ListNode left;//左节点引用
    ListNode right;//右节点引用
    ErListNode(int x){val=x;}
}

class TreeNode{
    int val;       //节点值
    TreeNode left; //左节点
    TreeNode right;//右节点
    TreeNode(){}
    TreeNode(int x){
        val=x;
    }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
     }
}

public class Code {
    static int hash(int id) {
        int index = (id - 1) % 10000;
        return index;
    }
    public static void main(String[] args) {
    int[] array = new int[8];//数组
    List<Integer> list= new ArrayList<>();//可变数组
    //实例化节点
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(5);
        n1.next=n2;
        n2.next=n3;
    String f =null;//“ ”！=null
//    System.out.println(f.equals(null));//错误  null无equals
     //栈  先进后出
        Stack<Integer> stacks = new Stack<>();//通常情况下，不推荐使用 Java 的 Vector 以及其子类 Stack
        stacks.push(1);//元素1入栈
        System.out.println(stacks.toString());
        stacks.push(2);//元素2入栈
        System.out.println(stacks.toString());
        stacks.pop();//出栈->元素2
        System.out.println(stacks.toString());

    //栈//使用LinkedList代替栈Stack
        LinkedList<Integer> stack = new LinkedList<>();//使用LinkedList代替栈Stack
        stack.addLast(1);//元素1入栈
        System.out.println(stack.toString());
        stack.addLast(2);//元素2入栈
        System.out.println(stack.toString());
        stack.offerLast(3);//元素3入栈
        System.out.println(stack.toString());
        stack.removeLast();// 出栈 -> 元素 3
        System.out.println(stack.toString());
        stack.pollLast();//出栈 -> 元素 2
        System.out.println(stack.toString());
    // Deque
        System.out.println("deque.toString()");
        Deque<Integer> deque = new LinkedList<>();
        System.out.println(deque.toString());
        deque.addFirst(1);
        System.out.println(deque.toString());
        deque.addLast(2);
        System.out.println(deque.toString());
        deque.add(3);
        System.out.println(deque.toString());
        deque.addLast(5);
        System.out.println(deque.toString());
        deque.offerFirst(6);
        System.out.println(deque.toString());
        deque.offerLast(9);
        System.out.println(deque.toString());
        deque.remove();
        System.out.println(deque.toString());
        deque.removeFirst();
        System.out.println(deque.toString());
        deque.removeLast();
        System.out.println(deque.toString());
        int i = deque.peek();
        System.out.println(deque.toString());
        deque.poll();
        System.out.println(deque.toString());


    //队列   先进先出
        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.toString());
        queue.offer(1);
        System.out.println(queue.toString());
        queue.add(2);
        System.out.println(queue.toString());
        queue.poll();
        System.out.println(queue.toString());
        queue.remove();
        System.out.println(queue.toString());

    //树
        TreeNode tn1 = new TreeNode(1);
        TreeNode tn2 = new TreeNode(2);
        TreeNode tn3 = new TreeNode(3);
        TreeNode tn4 = new TreeNode(4);
        TreeNode tn5 = new TreeNode(5);

        tn1.left=tn2;
        tn1.right=tn3;
        tn2.left=tn4;
        tn2.right=tn5;
        System.out.println(tn1.val);
        System.out.println(tn1.left.val);
        System.out.println(tn1.left.right.val);
        //System.out.println(tn1.right.right.val);//空 NullPointerException

    //散列表  散列表是一种非线性数据结构，通过利用 Hash 函数将指定的「键 key」映射至对应的「值 value」，以实现高效的元素查找。
        Map<String ,Integer> map = new HashMap<>();
        //添加 key->value键值对
        map.put("A",1);
        map.put("B",2);
        //查找
        System.out.println(map.get("A"));
        System.out.println(map.get("B"));
        System.out.println(map.toString());

     //自行设计Hash函数
        String[] names = { "小力", "小特", "小扣" };
        System.out.println(names[hash(10001)]);// 小力
        System.out.println(names[hash(10002)]);// 小特
        System.out.println(names[hash(10003)]);// 小扣

    //堆  堆是一种基于「完全二叉树」的数据结构，可使用数组实现。以堆为原理的排序算法称为「堆排序」，
        // 基于堆实现的数据结构为「优先队列」。堆分为「大顶堆」和「小顶堆」，
        // 大（小）顶堆：任意节点的值不大于（小于）其父节点的值
        //完全二叉树定义： 设二叉树深度为 kk ，若二叉树除第 kk 层外的其它各层（第 11 至 k-1k−1 层）的节点达到最大个数，
        // 且处于第 kk 层的节点都连续集中在最左边，则称此二叉树为完全二叉树
        //初始化小顶堆
        Queue<Integer> heap = new PriorityQueue<>();

        //Queue<Integer> daheap   = new
        //元素入堆
        heap.add(1);
        heap.offer(4);
        heap.add(3);
        heap.add(2);
        heap.add(6);
        heap.add(8);
        System.out.println(heap.toString());
        heap.remove(2);
        System.out.println(heap.toString());
        //元素出堆 (从小到大)
        System.out.println(heap.poll());
        System.out.println(heap.toString());
        System.out.println(heap.poll());
        System.out.println(heap.toString());
        heap.poll();
        System.out.println("****************");
        Iterator<Integer> itl = heap.iterator();
        while (itl.hasNext()){
            int si = itl.next();
            System.out.println(si);
        }
    }

    //完全二叉树的增删改查
    //一、判断 BST 的合法性
    public boolean isValidBST(TreeNode root){
        //需要确定root节点的上界与下界
        return isValidBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
    }
    public boolean isValidBST(TreeNode root,int low,int hight){
        if (root==null) return true;
        if (root.val<low) return false;
        if (root.val>hight) return false;
        return isValidBST(root.left, low, root.val)&&isValidBST(root.right, root.val, hight);
    }
    //在 BST 中搜索一个数
    boolean isInBST(TreeNode root,int target){
        if (root == null) {
            return false;
        }
        if (root.val==target){
            //寻找到目标，需要返回节点root？
            return true;
        }
        if (target<root.val){
            return isInBST(root.left, target);
        }
        if (target>root.val){
            return isInBST(root.right,target);
        }
        return false;
    }
    //在BST中插入一个数
    TreeNode insertIntoBST(TreeNode root,int val){
        //寻找插入的位置。即节点为空时。
        if (root==null){
            return new TreeNode(val);
        }
        if (val<root.val){
            root.left=insertIntoBST(root.left, val);
        }
        if (val>root.val){
            root.right=insertIntoBST(root.right,val);
        }
        return root;
    }
    //在BST中删除一个数
    TreeNode deleteFromBST(TreeNode root,int val){
        if (root==null) return null;
        //寻找删除的位置
        if(root.val==val){
            //确认此节点为删除节点位置
            if (root.left==null&&root.right==null){
                //删除的节点位置为叶子节点 直接删除
                return null;
            }
            if(root.left==null){//节点的左子树为空 返回右子树
                return root.right;
            }
            if (root.right==null){//节点的右子树为空  返回左子树
                return root.left;
            }
            //节点的左右子树都不为空
            //寻找替代的节点以及节点位置
            TreeNode min = getMin(root.right);//寻找右子树中节点的最小值
            root.val = min.val;
            root.right=deleteFromBST(root.right,min.val);
        }
        if (root.left!=null && val<root.val){
            root.left=deleteFromBST(root.left,val);
        }
        if(root.right!=null&&val>root.val){
            root.right=deleteFromBST(root.right,val);
        }

        return root;
    }
    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

}

