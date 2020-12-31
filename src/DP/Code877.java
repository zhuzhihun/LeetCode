package DP;
/*
* 输入：[5,3,4,5]
输出：true
解释：
亚历克斯先开始，只能拿前 5 颗或后 5 颗石子 。
假设他取了前 5 颗，这一行就变成了 [3,4,5] 。
如果李拿走前 3 颗，那么剩下的是 [4,5]，亚历克斯拿走后 5 颗赢得 10 分。
如果李拿走后 5 颗，那么剩下的是 [3,4]，亚历克斯拿走后 4 颗赢得 9 分。
这表明，取前 5 颗石子对亚历克斯来说是一个胜利的举动，所以我们返回 true 。
piles=[3,9,1,2]
     0       1     2      3
0  (3,0)
1         (3,0)
2                (3,0)
3                        (3,0)
* */
public class Code877 {
    public boolean stoneGame(int[] piles){
        int n = piles.length;
        int[][][] dp =new int[n][n][2];//最后的1与2用来区分 先手与后手
        //n*n的矩阵

        for (int i = 0; i < n; i++) {
            dp[i][i][0]=piles[i];
        }
        //dp[0][1][0]=max(piles[]+dp[0][0].)
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i <= n - l; i++) {
                int j = l + i - 1;
                //



            }
        }


        return true;
    }
}
