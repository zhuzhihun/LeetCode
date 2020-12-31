package Tree;

import java.util.*;

public class Code102 {
    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>> lists = new ArrayList<>();
        if (root==null){
            return lists;
        }
        //先进先出  队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<Integer>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode treeNode = queue.remove();
                list.add(treeNode.val);
                if (treeNode.left!=null){
                    queue.add(treeNode.left);
                }
                if(treeNode.right!=null){
                    queue.add(treeNode.right);
                }
            }
            lists.add(list);

        }

        return lists;
    }
    //103
    public List<List<Integer>> zigzaglevelOrder(TreeNode root){
        List<List<Integer>> lists = new ArrayList<>();
        if (root==null){
            return lists;
        }
        //先进先出  队列
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()){
            LinkedList<Integer> list = new LinkedList<>();
            int count = queue.size();
            for (int i = 0; i < count; i++) {
                TreeNode treeNode = queue.remove();

                if(flag){
                    list.addLast(treeNode.val);
                    flag=!flag;
                }else {
                    list.addFirst(treeNode.val);
                    flag=!flag;
                }

                if (treeNode.left!=null){
                    queue.add(treeNode.left);
                }
                if(treeNode.right!=null){
                    queue.add(treeNode.right);
                }
            }
            lists.add(list);

        }

        return lists;
    }
    //104   求树的最大深度
    public int maxDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        return 1+Math.max(maxDepth(root.left),maxDepth(root.right));
        //return 0;
    }
    public int maxDepth(TreeNode root, int count){
        int ans=0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int c = queue.size();
            for (int i = 0; i < c; i++) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left!=null){
                    queue.add(treeNode.left);
                }
                if (treeNode.right!=null){
                    queue.add(treeNode.right);
                }
            }

            ans++;
        }

        return ans;
    }
    //105  从前序与中序遍历序列构造二叉树
    public TreeNode buildTree(int[] preorder, int[] inorder){
        int preLength = preorder.length;
        int inLength = inorder.length;
        if (preorder==null){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<Integer, Integer>(inLength);
        for (int i = 0; i < inLength; i++) {
            map.put(inorder[i],i);
        }
        //递归
        return buildTree(preorder,map,0,preLength-1,0,inLength-1);
    }
    public TreeNode buildTree(int[] preorder, Map<Integer,Integer> map, int pre_left, int pre_right, int in_left, int in_right){
        if (pre_left > pre_right || in_left > in_right){
            return null;
        }
        int pindexvalue = preorder[pre_left];
        int pindex = map.get(pindexvalue);
        TreeNode treeNode = new TreeNode(pindexvalue);
        treeNode.left  = buildTree(preorder,map,pre_left+1,pindex-1-in_left+pre_left+1,in_left,pindex-1);
        treeNode.right = buildTree(preorder,map,pindex-1-in_left+pre_left+1+1,pre_right,pindex+1,in_right);
        return treeNode;

    }
    //106 从中序与后序遍历序列构造二叉树
    public TreeNode buildTree1(int[] inorder, int[] postorder){
        int inLength = inorder.length;
        int postLength = postorder.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < inLength; i++) {
            map.put(inorder[i],i);
        }

        return buildTrees(map,postorder,0,inLength-1,0,postLength-1);

    }

    private TreeNode buildTrees(Map<Integer, Integer> map, int[] postorder, int in_left, int in_right, int post_left, int post_right) {
        if (in_left>in_right || post_left>post_right){
            return null;
        }
        TreeNode treeNode = new TreeNode(postorder[post_right]);
        int index = map.get(postorder[post_right]);
        treeNode.left = buildTrees(map,postorder,in_left,index-1,post_left,index-1-in_left+post_left);
        treeNode.right= buildTrees(map,postorder,index+1,in_right,index-1-in_left+post_left+1,post_right-1);
        return treeNode;
    }

    public static void main(String[] args) {
        Code102 c = new Code102();
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(1);
        treeNode.right=treeNode1;
        treeNode1.left=treeNode2;
        treeNode2.right=treeNode3;
        treeNode3.left=treeNode4;
        int a = c.maxDepth(treeNode1,1);
        //System.out.println(a);

        int[] preorder = {3, 9, 8, 5, 4, 10, 20, 15, 7};
        int[] inorder  = {4, 5, 8, 10, 9, 3, 15, 20, 7};
        TreeNode root = c.buildTree(preorder,inorder);
        System.out.println(root.toString());
        List<List<Integer>> list = c.zigzaglevelOrder(root);

        System.out.println(list.toString());
        int[] postorder = {9,15,7,20,3};
        int[] inorders  = {9,3,15,20,7};
        c.buildTree1(inorders,postorder);

        StringBuilder sb = new StringBuilder();
        sb.append("#");

    }
}
