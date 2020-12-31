package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class Code124 {//最大路径和
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int x){this.val=x;}
        TreeNode(int x,TreeNode left,TreeNode right){
            this.val=x;
            this.left=left;
            this.right=right;
        }
    }
    private int max = Integer.MIN_VALUE;
    public  int maxPathSum(TreeNode root){//给定一个非空二叉树，返回其最大路径和
        maxPath(root);//返回的是 经过此节点的值  与 左或右节点的值（不是最大值）
        return max;//需要返回 最大值  定义最大值max
    }
    //路径被定义为一条从树中任意节点出发，沿父节点-子节点连接，
    // 达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
    public int maxPath(TreeNode root){
        if (root==null){//结束条件
            return 0;
        }
        //递归计算左右节点的最大贡献值
        //获取左右的最大值    只有大于0时 才选择  否则用0
        int left = Math.max(maxPath(root.left),0);//分别获取 单路径的最大值 ,当都是负数时,选择null 即为0;
        int right = Math.max(maxPath(root.right),0);

        max = Math.max(max,left+right+root.val);//最大值为 之前保存的值 与 左右路径之和

        return root.val+Math.max(right,left);//返回单路径 经过此节点的最大边
    }

    public boolean isBalanced(TreeNode root){
        if (root==null){
            return true;
        }
        return helper(root)>=0;
    }
    //递归判断 左 右子树是否为平衡二叉树
    public int helper(TreeNode root){
        if (root==null){
            return 0;
        }

        int left =helper(root.left) ;
        int right = helper(root.right);

        if (Math.abs(left-right)>1||left==-1||right==-1){
            //左右子树相差大于1
            return -1;//代表 不 是 平衡的二叉树。
        }
        return Math.max(left,right)+1;//返回 左右子树 最长的路径.
    }
    public int minDepths(TreeNode root) {
        if (root == null)
            return 0;
        int depth = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        return BFS(queue, depth);
    }
    private int BFS(Queue<TreeNode> queue, int depth){
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            TreeNode node = queue.poll();
            if (node.right == null && node.left == null)
                return depth;
            else {
                if (node.right != null)
                    queue.add(node.right);
                if (node.left != null)
                    queue.add(node.left);
            }
        }
        return BFS(queue,depth + 1);
    }
    public int minDepth(TreeNode root){
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth(root.right), min_depth);
        }

        return min_depth + 1;

    }
    public int help(TreeNode root){
        if (root==null){
            return 0;
        }
        int left = help(root.left);
        int right = help(root.right);
        if (right==0&&left==0){
            return 0;
        }else if(right==0){
            return left+1;
        }else if(left==0){
            return right+1;
        }
        return Math.min(left,right)+1;
    }


    //112 路径之和
    public boolean hasPathSum(TreeNode root,int sum){
        if (root==null){
            return false;
        }
        Queue<Integer> queueValue = new LinkedList<>();//用来存储根节点到当前节点的值
        Queue<TreeNode> queueNode = new LinkedList<>();//层次遍历辅助空间
        queueNode.add(root);//添加根节点
        queueValue.add(root.val);
        while (!queueNode.isEmpty()){//层次进行输出
            TreeNode nowNode = queueNode.remove();
            int value = queueValue.remove();
            if (nowNode.right==null&&nowNode.left==null){
                if (sum==value){
                    return true;
                }
                continue;
            }
            if (nowNode.left!=null){
                queueNode.add(nowNode.left);
                queueValue.add(nowNode.left.val+value);
            }
            if (nowNode.right!=null){
                queueNode.add(nowNode.right);
                queueValue.add(nowNode.right.val+value);
            }

        }

        return false;
    }
    public boolean haspathSum(TreeNode root,int sum){
        if (root==null){
            return false;
        }
        //说明此时 的节点时叶子节点
        if (root.left==null&&root.right==null){//当叶子节点值刚好与 剩下的值相等是 才是正确
            return sum==root.val;
        }
        //不是叶子节点  则要从左子树或者右子树中 满足sum-当前值value的路径
        return haspathSum(root.left,sum-root.val)||haspathSum(root.right,sum-root.val);//只需要左子树或者右子树满足条件就行  是或
    }
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(8);
        TreeNode treeNode3 = new TreeNode(11);
        TreeNode treeNode4 = new TreeNode(13);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode6 = new TreeNode(2);
        TreeNode treeNode8 = new TreeNode(1);

        treeNode.left=treeNode1;
        treeNode.right=treeNode2;
        treeNode1.left=treeNode3;
        treeNode3.left=treeNode7;
        treeNode3.right=treeNode6;
        treeNode2.left=treeNode4;
        treeNode2.right=treeNode5;
        treeNode5.right=treeNode8;
        Code124 a = new Code124();
        boolean b= a.haspathSum(treeNode,22);
        System.out.println(b);
    }
}

