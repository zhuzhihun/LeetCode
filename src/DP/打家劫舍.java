package DP;
//输入：[1,2,3,1]
//输出：4
//解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。
//

public class 打家劫舍 {
    public int rob(int[] nums){
        return dp(nums,0);
    }
    public int dp(int[] nums,int start){
        if (start>nums.length){
            return 0;
        }
        int res = Math.max(dp(nums,start+1),dp(nums,start+2)+nums[start]);
        return res;
    }
    public int rob1(int[] nums){
        if (nums.length==0||nums==null){
            return 0;
        }
        if (nums.length==1){
            return nums[0];
        }
        int n = nums.length;
        int[] dp =new int[n];
        dp[0]=nums[0];
        dp[1]=Math.max(nums[0],nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i]=Math.max(nums[i]+dp[i-2],dp[i-1]);
        }

        return dp[n-1];
    }
}
