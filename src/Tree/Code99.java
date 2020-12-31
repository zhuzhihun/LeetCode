package Tree;

import java.util.ArrayList;
import java.util.List;

public class Code99 {
    public void recoverTree(TreeNode root){
        //开辟一个新的数组用nums来记录中序遍历得到的值序列，
        List<Integer> list = new ArrayList<Integer>();
        //将树中的数据复制到List中
        inorder(root,list);
        //此时需要找到 位置 错误的两个数
        int x=-1;
        int y=-1;
        // x与y用来记录两个位置错误的数
        for (int i = 1; i <list.size() ; i++) {
            if (list.get(i)<list.get(i-1)){
                x=list.get(i);
                if (y==-1){
                    y=list.get(i-1);
                }
            }
        }
        //此时 x 与 y 即为找好的 错误位置的值
        swap(root,2,x,y);

    }
    public void swap(TreeNode root, int count, int x, int y){
        if (root==null){
            return;
        }
        swap(root.left,count,x,y);
        if (root.val==x||root.val==y){
            root.val=(root.val==y?x:y);
            count--;
        }
        if (count==0){
            return;
        }
        swap(root.right,count,x,y);
    }
    public void inorder(TreeNode root , List<Integer> list){
        if (root==null){
            return;
        }
        inorder(root.left,list);
        list.add(root.val);
        inorder(root.right,list);
    }
    public void dfs(TreeNode treeNode){
        if (treeNode==null){
            return;
        }
        dfs(treeNode.left);
        System.out.print(treeNode.val+"\t");
        dfs(treeNode.right);
    }

    public static void main(String[] args) {
        Code99 c = new Code99();
        TreeNode root =new TreeNode(5);
        TreeNode n1 =new TreeNode(3);
        TreeNode n2 =new TreeNode(1);
        TreeNode n3 =new TreeNode(2);
        TreeNode n4 =new TreeNode(6);
        root.left=n1;
        root.right=n4;
        n1.left=n2;
        n1.right=n3;
        c.dfs(root);
        c.recoverTree(root);
        System.out.println();
        c.dfs(root);
    }
}
