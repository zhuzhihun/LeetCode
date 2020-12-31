package DP;

public class dp1 {
    //516最长回文子序列
    public int longestPalindromeSubseq(String s){
        int n = s.length();
        int[][] dp = new int[n][n];//java中初始化默认为0;
        for (int i = 0; i < n; i++) {
            dp[i][i]=1;
        }
        //
        for (int i = n-1; i >= 0 ; i--) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i)== s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
    //1143.最长公共子序列

    public int longestCommonSubsequence(String text1, String text2) {
        int m=text1.length();
        int n=text2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i < m+1; i++) {
            for (int j = 1; j < n+1; j++) {
                if (text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }
                else{
                    dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }


    //583.两个字符串的删除操作

    public int minDistance(String word1, String word2) {
        int m=word1.length(),n=word2.length();
        int[][] dp =new int[m+1][n+1];
        for (int i = 1; i <=m ; i++) {
            dp[i][0]=dp[i-1][0]+word1.codePointAt(i-1);
        }
        for (int j = 1; j <=n ; j++) {
            dp[0][j]=dp[0][j-1]+word2.codePointAt(j-1);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j]+word1.codePointAt(i-1),dp[i][j-1]+word2.codePointAt(j-1));
                }
            }
        }
        return dp[m][n];
    }
    //m*n-2倍公共字符串长度
    //518. 零钱兑换 II
    // 输入: amount = 5, coins = [1, 2, 5]
    //输出: 4
    //解释: 有四种方式可以凑成总金额:
    //  5=5
    //  5=2+2+1
    //  5=2+1+1+1
    //  5=1+1+1+1+1
    //
    public int change(int amount,int[] coins){
        int m = coins.length;
        int[][] dp =new int[m+1][amount+1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= amount; j++) {
                //此时为判断前i种硬币 在面额为 j的时候能够有几种解法
                //判断金币的面额是否大于  总价值
                if (coins[i-1]>j){
                    //表示 无法用现在的 硬币凑数   应该沿用 coins【i-1】情况下的数
                    dp[i][j]=dp[i-1][j];
                }else{//此时为  总额大于 硬币的面额
                    dp[i][j]=dp[i][j-coins[i-1]]+dp[i-1][j];

                }
            }
        }
        return dp[m][amount];
    }


    public static void main(String[] args) {
//        //一维dp数组
//        int n = array.length;
//        int[] dp = new int[n];
//
//        for (int i = 1; i < n; i++) {
//            for (int j = 0; j < i; j++) {
//                dp[i] = 最值(dp[i], dp[j] + ...)
//            }
//        }
//
//        //二维dp数组
//        int n = arr.length;
//        int[][] dp = new dp[n][n];
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 1; j < n; j++) {
//                if (arr[i] == arr[j])
//                    dp[i][j] = dp[i][j] + ...
//        else
//                dp[i][j] = 最值(...)
//            }
//        }
        dp1 dp1 = new dp1();
        String s1 = "a";
        String s2 = "at";
        int i = dp1.minDistance(s1,s2);
        System.out.println(i);
    }
}
