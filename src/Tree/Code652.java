package Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
//寻找重复的子树
public class Code652 {
    /*
    * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
    * 两棵树重复是指它们具有相同的结构以及相同的结点值。
    * */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root){
        //如何判断 自己树是什么模样 并且与其他人一样；
        traverses(root);

        return res;
    }
    //判断 树有多少个节点。
    int count(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 先算出左右子树有多少节点
        int left = count(root.left);
        int right = count(root.right);
        /* 后序遍历代码位置 */
        // 加上自己，就是整棵二叉树的节点数
        int res = left + right + 1;
        return res;
    }
    //根据 树相同节点个数判断 是否一样
    String traverse(TreeNode root) {
        if(root==null){//使用 # 好代替 空值
            return "#";
        }
        String left = traverse(root.left);
        String right =traverse(root.right);
        String subTree = left+","+right+","+root.val;
        return subTree;
    }
    //这样，以我为根的这棵二叉树（子树）长啥样 解决了，对于每个节点，递归函数中的 subTree 变量就可以描述以该节点为根的二叉树。
    //现在我们解决第二个问题，我知道了自己长啥样，怎么知道别人长啥样？这样我才能知道有没有其他子树跟我重复对吧。
    //我们借助一个外部数据结构，让每个节点把自己子树的序列化结果存进去，这样，对于每个节点，就可以知道有没有其他节点的子树和自己重复了
    // 记录所有子树
    HashSet<String> memo = new HashSet<>();//判断当前子树是否已经 存储在 HashSet中
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();//当出现树已经出现在HashSet中
    public String traverses(TreeNode root){
        if(root==null){//使用 # 好代替 空值
            return "#";
        }
        String left = traverses(root.left);
        String right =traverses(root.right);
        String subTree = left+","+right+","+root.val;
        if (memo.contains(subTree)){
            if (!res.contains(root)){
                res.add(root);
            }
            //需要判断 res 中是否存在节点 root


        }else{
            memo.add(subTree);

        }
        return subTree;
    }
    //此时 res中将出现许多重复的二叉树 节点。


    public static void main(String[] args) {
        Code652 c =new Code652();
        TreeNode root1= new TreeNode(1);
        TreeNode root2= new TreeNode(2);
        TreeNode root3= new TreeNode(3);
        root1.left=root2;root1.right=root3;
        TreeNode root4= new TreeNode(4);
        TreeNode root5= new TreeNode(2);
        TreeNode root6= new TreeNode(4);
        root3.left=root5;root3.right=root6;
        root2.left=root4;
        TreeNode root7= new TreeNode(4);
        root5.left=root7;
        List<TreeNode> s = c.findDuplicateSubtrees(root1);
        for (TreeNode t:s){
            System.out.println(t.val);
        }
    }
}
