package List;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
//给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
//
//子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
//输入：nums = [10,9,2,5,3,7,101,18]
//输出：4
//解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。

public class Code300 {
    public int lengthOfLIS(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0]=1;
        int ans = 1;
        for (int i = 1; i < length; i++) {
            dp[i]=1;
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[i]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                }
            }
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] envelopes = {{5,4}, {6,4},{6,7},{2,3}};
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //当a>b时，返回正数  交换两个数
                //当a<b时，返回负数
                //当a=b时，返回0

                if (o1[0]==o2[0]){//当数组第一个数 即长相等时  比较第二个宽的大小
                    return o2[1]-o1[1];//后 - 前 大于0时，交换两个数  按降序排列
                }else{//第一个数不相等 直接按升序
                    return o1[0]-o2[0];//第一个数－第二个数大于0时，表示需要交换两个数 升序排列
                }
            }
        });
        //此时 envelopes 已经排好序
        for (int[] ints:envelopes)
            System.out.println(ints[0]+","+ints[1]);
        //
        List<TreeNode> treeNodes=Arrays.asList(
                new TreeNode(1),
                new TreeNode(2),
                new TreeNode(3)
        );
    }
}
