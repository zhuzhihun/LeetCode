package DP;
//给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。

import java.util.Arrays;

//设计一个算法使得这 m 个子数组各自和的最大值最小。
public class Code410 {
    public int splitArray(int[] nums,int m){
        //nums.length = 2*m
        int len = nums.length;
        //前缀和
        int[] preSum = new int[len+1];
        preSum[0]=0;
        for (int i = 0; i < len; i++) {
            preSum[i+1] = preSum[i]+nums[i];
        }
        //区间[i..j]的和preSum(j+1)-preSum(i)
        int[][] dp = new int[len][m+1];
        // 初始化：由于要找最小值，初值赋值成为一个不可能达到的很大的值
        for (int i = 0; i < len; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        // 分割数为 1 ，即不分割的情况，所有的前缀和就是依次的状态值
        for (int i = 0; i < len; i++) {
            dp[i][1] = preSum[i + 1];

        }
        // 从分割数为 2 开始递推
        String a ="123";
        a.charAt(0);
        for (int k = 2; k <= m; k++) {
            // 还未计算出的 i 是从 j 的最小值的下一位开始，因此是 k - 1
            for (int i = k - 1; i < len; i++) {
                // j 表示第 k - 1 个区间的最后一个元素额下标，最小值为 k - 2，最大值为 len - 2（最后一个区间至少有 1 个元素）
                for (int j = k - 2; j < i; j++) {
                    dp[i][k] = Math.min(dp[i][k], Math.max(dp[j][k - 1], preSum[i + 1] - preSum[j + 1]));
                }
            }
        }
        return dp[len - 1][m];
    }
}
