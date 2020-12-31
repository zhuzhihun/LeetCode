package Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code95 {
    private static int count =0;
    public List<TreeNode> generateTrees(int n){//构建二叉搜索树
        List<Integer> list = new ArrayList<>();
        if (n==0){
            List<TreeNode> li = new LinkedList<>();
            return li;
        }
//        for (int i = 0; i <n ; i++) {
//            list.add(i);
//        }
        List<TreeNode> list1 = treehelper(1,n);

        // 返回条件为   start>end   即当start=1，end=1 i=1，此时 左（start.1，i-1.0） 右（i+1. 2,end .1）

        return list1;
    }
    public List<TreeNode> treehelper(int start, int end){
        List<TreeNode> list = new LinkedList<>();
        if(start>end){
            list.add(null);
            return list;
        }

        //从1-n 分别为i作为节点构建二叉树   start =1, end = n;
        for (int i = start; i <= end; i++) {
            // start-> i-1 为左子树
            //TreeNode root = new TreeNode(i);
            //如何构建左子树  左子树所有的可能性
            List<TreeNode> left = treehelper(start,i-1);

            // i+1-> end 为右子树
            List<TreeNode> right = treehelper(i+1,end);
            for (TreeNode l:left){
                for (TreeNode r:right){
                    TreeNode root = new TreeNode(i);
                    root.left=l;
                    root.right=r;
                    //保存到 数组中进行返回
                    list.add(root);
                    count++;
                }
            }

                //返回考虑的值与形式   返回的应该是 以该节点为root构建所有树的可能
        }
        return list;
        // 返回条件为   start>end   即当start=1，end=1 i=1，此时 左（start.1，i-1.0） 右（i+1. 2,end .1）
    }
    public static void main(String[] args) {
        Code95 c = new Code95();
        List<TreeNode> list = c.generateTrees(3);
//        for (TreeNode i : list){
//            System.out.println(i.val);
//        }
        System.out.println(list.size());
    }
}

