package Tree;

import java.util.LinkedList;

public class Code116 {//给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
    public Node connect(Node root){
        if (root==null) return null;
        connectTwoNode(root.left,root.right);
        return root;

    }

    private void connectTwoNode(Node left, Node right) {
        if(left==null || right==null){
            return;
        }
        left.next=right;
        connectTwoNode(left.left,left.right);
        connectTwoNode(right.left,right.right);
        connectTwoNode(left.right, right.left);


    }
    public Node connect(Node root, Node node){
        if (root==null) return null;
        LinkedList<Node> linkedList=new LinkedList<>();
        linkedList.add(root);
        while (!linkedList.isEmpty()){
            int size =linkedList.size();
            for (int i = 0; i < size; i++) {
                Node cur = linkedList.poll();
                if (i<size-1) {
                    cur.next=linkedList.peek();
                }
                if (cur.left!=null){
                    linkedList.add(cur.left);

                }
                if (cur.right!=null){
                    linkedList.add(cur.right);
                }
            }
        }
        return root;
    }
    public Node connects(Node root) {
        if (root == null) {
            return root;
        }

        // 从根节点开始
        Node leftmost = root;

        while (leftmost.left != null) {//每一层的最左边是否有数

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;//从每层 出发

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {//利用已经连接的next操作连接不同父节点的右子树与左子树
                    head.right.next = head.next.left;
                }//当head指向最后的右子树节点时 其head.next为null 跳过此操作；

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }

    public static void main(String[] args) {
        Code116 c = new Code116();
        Node root =new Node(1);
        Node root2 =new Node(2);
        Node root3 =new Node(3);
        root.left=root2;
        root.right=root3;
        Node root4 =new Node(4);
        Node root5 =new Node(5);
        root2.left=root4;
        root2.right=root5;
        Node root6 =new Node(6);
        Node root7 =new Node(7);
        root3.left=root6;
        root3.right=root7;
        //Node root ={"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}
        c.connects(root);

    }
}
