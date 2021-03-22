package Offers;
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
//思想：必定存在这样一个数，这个数与自身所处的位置nums[i]!=i,且交换位置时还有这样一个数

public class Offers_3 {
    public int findRepeatNumber(int[] nums) {
        int temp;
        for(int i=0;i<nums.length;i++){
            while (nums[i]!=i){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                temp=nums[i];
                nums[i]=nums[temp];
                nums[temp]=temp;
            }
        }
        return -1;
    }
    //在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    // 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
    //数组查找  二分查找  从中间向两边扩展
    //此二维数组为有序数组  从第i行0列比较  matrix[i][0]与target比较  i--;j++
    //[
    //  [1,   4,  7, 11, 15],
    //  [2,   5,  8, 12, 19],
    //  [3,   6,  9, 16, 22],
    //  [10, 13, 14, 17, 24],
    //  [18, 21, 23, 26, 30]
    //]

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        String s="sag";
        s=s.replace(" ","20%");
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int rows = matrix.length, columns = matrix[0].length;
        int j=0,i=rows-1;
        while (i>=0&&j<columns){
            if (matrix[i][j]<target){
                j++;
            }else if (matrix[i][j]>target){
                i--;
            }else
                return true;
        }

        return false;
    }
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){val=x;}
    }
    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

}
