package Tree;

public class Code98 {
    //给定一个二叉树，判断其是否是一个有效的二叉搜索树。
    //  ***利用二叉树的中序遍历是一 组 从小到大的有序数组
    //假设一个二叉搜索树具有如下特征：
    //
    //节点的左子树只包含小于当前节点的数。
    //节点的右子树只包含大于当前节点的数。
    //所有左子树和右子树自身必须也是二叉搜索树
    //private static boolean flag=true;
    public boolean isValidBST(TreeNode root){
        return helper(root,null,null);
    }
    public boolean helper(TreeNode root , Integer lower, Integer upper){
        if (root==null){//  当节点为空时  返回true
            return true;
        }
        //判断 root.val 是否是满足条件的 即是否在上界 与  下界中
        if (lower!=null && root.val<lower) {
            return false;  //存在下界， 但是节点数小于下界  返回false
        }
        if (upper!=null && root.val>upper){
            return false;
        }//排除 不在范围内的 情况

        //左边 存在上界  即为根节点
        if (!helper(root.left,lower,root.val)){
            return false;
        }
        //右边 存在下界  即为根节点
        if (!helper(root.right,root.val,upper)) {
            return false;
        }

        return true;
    }
    public boolean isValidBSTs(TreeNode root){
        if (root==null){
            return true;
        }
        while (root==null){

        }

        return true;
    }




    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(5);
//        root.right.left = new TreeNode(2);
        Code98 c = new Code98();
        boolean f = c.isValidBST(root);
        System.out.println(f);
    }
}
