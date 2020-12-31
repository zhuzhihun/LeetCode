package Tree;

import java.util.ArrayList;
import java.util.List;

public class Code94 {
    public static class TreeNode{
        int val;
        Code94.TreeNode left;
        Code94.TreeNode right;
        TreeNode(){}
        TreeNode(int x){this.val=x;}
        TreeNode(int x, TreeNode left, TreeNode right){
            this.val=x;
            this.left=left;
            this.right=right;
        }
    }
    private List<Integer> list = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root){//二叉树的中序遍历
        //List<Integer> list = new ArrayList<>();
        traverseral(root);


        return list;
    }
    public void traverseral(TreeNode root){
        if (root==null){
            return;
        }
        traverseral(root.left);
        list.add(root.val);
        traverseral(root.right);

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Code94 c = new Code94();

        TreeNode n  =new TreeNode(1);
        TreeNode n1  =new TreeNode(2);
        TreeNode n2  =new TreeNode(3);
        TreeNode n3  =new TreeNode(4);
        n.right=n1;
        n1.left=n2;
        n2.right=n3;
        list=c.inorderTraversal(n);
        System.out.println(list.toString());
    }
}
