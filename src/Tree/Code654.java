package Tree;

/*
* 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：

二叉树的根是数组中的最大元素。
左子树是通过数组中最大值左边部分构造出的最大二叉树。
右子树是通过数组中最大值右边部分构造出的最大二叉树。
通过给定的数组构建最大二叉树，并且输出这个树的根节点。
输入：[3,2,1,6,0,5]
输出：返回下面这棵树的根节点：

      6
    /   \
   3     5
    \    /
     2  0
       \
        1

*
* */
public class Code654 {
    public TreeNode constructMaximumBinaryTree(int[] nums){
        //build(nums,0,nums.length-1);
        return build(nums,0,nums.length-1);
    }
    public TreeNode build(int[] nums, int low, int hight){
        if (hight<low){
            return null;
        }
        int index = -1;
        int value = Integer.MIN_VALUE;
        for (int i = low; i <= hight; i++) {
            if (nums[i]>value){
                value=nums[i];
                index=i;
            }
        }
        TreeNode root = new TreeNode(value);
        root.left=build(nums,low,index-1);
        root.right=build(nums,index+1,hight);
        return root;
    }
}
