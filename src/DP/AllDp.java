package DP;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class AllDp {
    public static void main1(String[] args) {
        char[][] matrix={{'1','0','1','0','0'},
                         {'1','0','1','1','1'},
                         {'1','1','1','1','1'},
                         {'1','0','1','1','0'}};
        System.out.println("4:"+maximalSquare(matrix));
        //4
        String s="bbbab";
        System.out.println("4:"+longestPalindromeSubseq(s));
        //4
        int[][] envelopes = {{5,4},{6,4},{6,7},{2,3}};
        System.out.println("3:"+maxEnvelopes(envelopes));
        //3
        String s1="baba";
        System.out.println("bab:"+longestPalindrome(s1));
        int[] prices = { 1 , 3 , 2 , 8 , 4 , 9 } ;
        System.out.println("8:"+maxProfit(prices,2));
        TreeNode root = new TreeNode(3);
        TreeNode root1 = new TreeNode(4);
        TreeNode root2 = new TreeNode(5);
        root.left=root1;root.right=root2;
        TreeNode root3 = new TreeNode(1);
        TreeNode root4 = new TreeNode(3);
        root1.left=root3;root1.right=root4;
        TreeNode root5 = new TreeNode(1);
        root2.right=root5;
        System.out.println("9:"+rob(root));

        System.out.println("13:"+maxProfit(prices));
        int nums[]={1 , 2 , 3 , 1};
        System.out.println("4:"+massage(nums));
    }
    /*
    * 在一个由'0'和'1'组成的二维矩阵内，找到只包含'1'的最大正方形，并返回其面积。

    输入：
    matrix =
[       [" 1 "," 0 "," 1 "," 0 "," 0 "] ,
        [" 1 "," 0 "," 1 "," 1 "," 1 "] ,
        [" 1 "," 1 "," 1 "," 1 "," 1 "] ,
        [" 1 "," 0 "," 0 "," 1 "," 0 "] ]
    输出：4

    dp[i][j]表示一i,j为右下角的最大正方形
    dp[i][j]=min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1

      */
    public static int maximalSquare(char[][] matrix){
        //get the height nad width from the matrix
        int height = matrix.length;
        int width  = matrix[0].length;
        int maxSide = 0;
        int dp[][] = new int[height+1][width+1];
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <=width; j++) {
                if (matrix[i-1][j-1]=='1'){
                    // i stand for square
                    // recurrence formula(递推公式)
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                    maxSide = Math.max(maxSide,dp[i][j]);
                }

            }
        }
        return maxSide*maxSide;
    }

    /*
    给定一个字符串s，找到其中最长的回文子序列，并返回该序列的长度。可以假设s的最大
    长度为 1000 。
输入:
" b b b a b "
输出:
4
一个可能的最长回文子序列为 "bbbb"。

    dp[i][j]= dp[i+1][j-1]+2
    dp[i][j]= min(dp[i+1][j],dp[i][j-1])

     */
    public static int longestPalindromeSubseq(String s) {
        int dp[][]= new int[s.length()][s.length()];
        for (int i = s.length()-1; i >=0; i--) {
            dp[i][i]=1;
            for (int j = i+1; j < s.length(); j++) {
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1]+2;
                }else{
                    dp[i][j]=Math.max(dp[i+1][j],dp[i][j-1]);
                }
            }
        }
        return dp[0][s.length()-1];

    }
/*

给 你 一 个 二 维 整 数 数 组 envelopes ， 其 中 envelopes [i]=[wi ,hi] ， 表 示 第 i 个 信 封 的 宽
度和高度。
当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，
如同俄罗斯套娃一样。
请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个
信封里面）。
注意：不允许旋转信封。
示例 1：
输入：e n ve l o p e s = [ [ 5 , 4 ] , [ 6 , 4 ] , [ 6 , 7 ] , [ 2 , 3 ] ]
输出：3
解释：最多信封的个数为 3 , 组合为: [ 2 , 3 ] => [ 5 , 4 ] => [ 6 , 7 ]。

先按长度上升排序，再按宽度下降排序    求下降的上升子序列问题

* */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes==null||envelopes.length==0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            }
        });
        //完成排序

        return lengthOfLIS(envelopes);
    }
    //求最长上升子序列
    public static int lengthOfLIS(int[][] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,1);
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                //如果当前值nums[i]大于nums[j]，说明nums[i]可以和
                //nums[j]结尾的上升序列构成一个新的上升子序列
                if (nums[i][1]>nums[j][1]){
                    dp[i]= Math.max(dp[i],dp[j]+1);
                }
            }
            //记录最大值
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    /*
     给你一个字符串s，找到s中最长的回文子串。
示例 1：
输入：s = " b a b a d "
输出：" b a b "
解释：" a b a " 同样是符合题意的答案。

    * */
    public static String longestPalindrome(String s) {
        if (s.length()<2){
            return s;
        }
        int start = 0,maxLen=1;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int right = 1; right < length; right++) {
            for (int left = 0; left < right; left++) {
                ///如果两种字符不相同，肯定不能构成回文子串
                if (s.charAt(left)!=s.charAt(right))
                    continue;
                //下面是s.charAt(left)和s.charAt(right)两个
                //字符相同情况下的判断
                //如果只有一个字符，肯定是回文子串
                if (right-left<=2){
                    //类似于"aa"和"aba"，也是回文子串 或者只有一个字符串的情况即left==right
                    dp[left][right]=true;
                }else{
                    dp[left][right]=dp[left+1][right-1];
                }
                //如果字符串从left到right是回文子串，只需要保存最长的即可
                if (dp[left][right] && right-left+1>maxLen){
                    maxLen = right-left+1;
                    start = left;
                }
            }
        }
        //截取最长的回文子串
        return s.substring(start, start + maxLen);
    }
    /*
     给定一个整数数组pri ces，其中第i个元素代表了第i天的股票价格；非负整数fee代表了
     交易股票的手续费用。
     你可以无限次地完成 交易 ，但是你每笔交易都需要付手续费。如果你已 经 购 买 了 一 个 股
     票，在卖出它之前你就不能再继续购买股票了。
     返回获得利润的最大值。
     注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次
     手续费。
     示例 1:
     原创 山大王wld 数据结构和算法 2月7日输入:
     prices = [ 1 , 3 , 2 , 8 , 4 , 9 ] , fee = 2
     输出: 8
     解释: 能够达到的最大利润:
     在此处买入 prices [ 0 ] = 1
     在此处卖出 prices [ 3 ] = 8
     在此处买入 prices [ 4 ] = 4
     在此处卖出 prices [ 5 ] = 9
     总利润: ((8 - 1 ) - 2 ) + ((9 - 4 ) - 2 ) = 8
    * */
    public static int maxProfit(int[] prices, int fee) {
        if(prices.length<2){
            return 0;
        }
     //   dp[i][0]表示第i天交易完之后手里没有股票的最大利润。
     //   dp[i][1]表示第i天交易完之后手里持有股票的最大利润。
        //int dp[][] = new int[prices.length][2];
        //dp[0][0]=0;
        //dp[0][1]=-prices[0];//表示第一天购入股票花费的钱
        int noHold=0;
        int hold = -prices[0];

        for (int i = 1; i < prices.length; i++) {
            //dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            noHold=Math.max(noHold,hold+prices[i]-fee);
            //dp[i][1]=Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
            hold=Math.max(noHold-prices[i],hold);
        }
        //最后一天肯定是手里没有股票的时候，利润才会最大，
        //只需要返回dp[length - 1][0]即可
        //return dp[prices.length][0];
        return noHold;
    }
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int x){this.val=x;}
        TreeNode(int x, TreeNode left, TreeNode right){
            this.val=x;
            this.left=left;
            this.right=right;
        }
    }
/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个
地区只有一个入口，我们称之为“根”。除了“根”之外，每栋房子有且只有一个“父“房子
与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二
叉树”。如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
示例 1:

输入: [3,4,5,1,3,null,1]
         3
        / \
       4   5
      / \   \
     1   3   1
输出: 9
解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.

    我们就从根节点开遍历这棵二叉树。
    如果偷根节点，那么就不能偷根节点的两个子节点，所以
    dp[1]=root.val+left.dp[0]+right.dp[0];
    这里的伪代码left.dp[0]表示的是不能偷当前节点的左子节点
    如果不偷根节点，那么我们可以偷子节点也可以不偷子节点，我们取最大值即可，所以
    dp[0]=max(left.dp[0],left.dp[1])+max(right.dp[0],right.dp[1]);
*/
    public static int rob (TreeNode root){
        int[] robHelp = robHelper(root);
        //取偷根节点和不偷根节点的最大值
        return Math.max(robHelp[1], robHelp[0]);

    }
    //robHelp[0]表示不偷此节点
    //robHelp[1]表示偷此节点
    private static int[] robHelper(TreeNode root) {
        if (root==null)
            return new int[2];
        //这里的left是个长度为2的一维数组，其中left[0]表示不偷root.left节点
        //所能偷窃的最大金额，left[1]表示偷root.left节点所能偷窃的最大金额。
        int left[] = robHelper(root.left);
        int right[]= robHelper(root.right);
        //Math.max(right[0], right[1]), root.val + left[0] + right[0]表示
        //的是不能偷当前节点，所以可以偷两个子节点，也可以不偷子节点，我们取最大的。
        //root.val + left[1] + right[1]表示的是偷当前节点，所以不能偷两个子节点。
        int[] res = new int[2];
        //[0]表示不偷此节点  取左节点最多 + 右节点最多
        res[0]= Math.max(left[0],left[1])+Math.max(right[0],right[1]);
        res[1]= root.val+left[0]+right[0];
        return res;
    }
    /*
    给定一个数组，它的第i个元素是一支给定股票第i天的价格。
    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（ 多 次 买
    卖一支股票）。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
    示例 1:
    输入: [ 7 , 1 , 5 , 3 , 6 , 4 ]
    输出: 7
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖
    出, 这笔交易所能获得利润 = 5 -1 = 4 。
    随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时
    候卖出, 这笔交易所能获得利润 = 6 -3 = 3 。

    动态规划的递推公式有了，那么边界条件是什么，就是第一天
    如果买入：dp[0][1]=-pri ces [0];
    如果没买：dp[0][0]=0;
    */
    public static int maxProfit(int[] prices) {
        if (prices==null||prices.length<2)
            return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][0]-prices[i],dp[i-1][1]);
        }
        return dp[prices.length-1][0];
    }
    public int maxProfit1(int[] prices) {
        int total = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            //原数组中如果后一个减去前一个是正数，说明是上涨的，
            //我们就要累加，否则就不累加
            total += Math.max(prices[i + 1] - prices[i], 0);
            }
        return total;
    }

    /*
    给定一个数组，它的第i个元素是一支给定股票第i天的价格。
    如果你最多只允许完成一笔交易

    （即买入和卖出一支股票一次），

    设计一个算法来计算你所能获取的最大利润。
    注意：你不能在买入股票前卖出股票。
    示例 1:
    输入: [ 7 , 1 , 5 , 3 , 6 , 4 ]
    输出: 5
    解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖
    出，最大利润 = 6 -1 = 5 。
     */
    public int maxProfitFromOneUseDP(int[] prices){
        if (prices==null||prices.length<2)
            return 0;
        int dp[][] = new int[prices.length][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][i]+prices[i]);
            //如果是持有的话  表示之前就已经持有  或者  这次买入 则为 -prices[i]
            dp[i][1]=Math.max(-prices[i],dp[i-1][i]);
        }
        return dp[prices.length-1][0];
    }
    public int maxProfitFromOneUsePointer(int[] prices){
        if (prices==null||prices.length<2)
            return 0;
        int min = prices[0];//记录数组中房访问最小的数
        int maxPro = 0;//记录最大利润
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min,prices[i]);
            maxPro = Math.max(prices[i]-min,maxPro);
        }
        return maxPro;
    }
    public int maxProfitFromOneUseStack(int[] prices){
        if (prices==null||prices.length<2)
            return 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(prices[0]);
        int max =0 ;
        for (int i = 1; i < prices.length; i++) {
            if (stack.peek()>prices[i]){
                stack.pop();
                stack.push(prices[i]);
            }else{
                max = Math.max(max,prices[i]-stack.peek());
            }
        }
        return max;
    }
    /*
    给定一个整数数组 n u m s ，找到一个具有最大和的连续子数组
    （子数组最少包含一个元素），返回其最大和。
    示例:
    输入: [-2 , 1 ,-3 ,4 ,-1 , 2 , 1,-5 , 4 ]
    输出: 6
    解释: 连续子数组[ 4 ,-1 , 2 , 1 ]的和最大，为6。

     */
    public static int maxSubArray(int[] nums){
        if (nums.length<1)
            return 0;
        int dp[] = new int[nums.length];
        int max = dp[0];
        dp[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i]=nums[i]+Math.max(0,dp[i-1]);
            max = Math.max(max,dp[i]);
        }
        return max;
    }
    /*
    一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次
    预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替
    按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。
    示例 1：
    输入：[ 1 , 2 , 3 , 1 ]
    输出：4
    解释：选择 1 号预约和 3 号预约，总时长 = 1 + 3 = 4。
     */
    public static int massage(int[] nums) {
        if (nums==null||nums.length==1)
            return 0;
        int dp[][]=new int[nums.length][2];
        dp[0][0]=0;
        dp[0][1]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]);
            dp[i][1]=dp[i-1][0]+ nums[i];
        }
        return Math.max(dp[nums.length-1][0],dp[nums.length-1][1]);
    }
/*
给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中相邻的结点
上。
相邻的结点在这里指的是下标与上一层结点下标相同或者等于一层结点下标+1的两个结
点。
例如，给定三角形：
[
   [2],
  [3,4],
 [6,5,7],
[4,1,8,3]
]

 */
    public int minimumTotal(List<List<Integer>> triangle) {
        //定义一个二维数组
        int[][] dp = new int[triangle.size() + 1][triangle.size() + 1];
        //从最后一行开始计算
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                //递归公式
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
        return dp[0][0];
        }
        /*
        请实现一个函数用来匹配包含' . '和' * '的正则表达式。模式中的字符' . '表示任意一个字
        符，而' * '表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的
        所有字符匹配整个模式。
        例如，字符串"aaa"与模式"a.a"和"ab*ac *a"匹配，但与"aa.a"和"ab*a"均不匹配。

         */
    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i < n; i++) {
            //如果p的第i+1个字符也就是p.charAt(i)是"*"的话，
            //那么他就可以把p的第i个字符给消掉（也就是匹配0次）。
            //我们只需要判断p的第i-1个字符和s的前0个字符是否匹
            //配即可。比如p是"a*b*"，如果要判断p的第4个字符
            //"*"和s的前0个字符是否匹配，因为字符"*"可以消去
            //前面的任意字符，只需要判断p的"a*"和s的前0个字
            //符是否匹配即可
            if (p.charAt(i) == '*' && dp[0][i - 1]) {
                dp[0][i + 1] = true;
                }
            }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    dp[i + 1][j + 1] = dp[i][j];
                    } else if (p.charAt(j) == '*') {
                    //递归公式
                    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                        dp[i + 1][j + 1] = dp[i][j + 1];
                     }
                    dp[i + 1][j + 1] |= dp[i + 1][j - 1];
                    }
                }
            }
        return dp[m][n];
}
/*
给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径
上的数字总和为最小。
说明：每次只能向下或者向右移动一步。
示例:
输入:
[
[ 1 , 3 , 1 ] ,
[ 1 , 5 , 1 ] ,
[ 4 , 2 , 1 ]
]
输出: 7   解释: 因为路径 1→3→1→1→1 的总和最小。
 */
//public static void main(String[] args) {
//    int[][] grid={{1 , 3 , 1},{ 1 , 5 , 1 },{4 , 2 , 1 }};
//    System.out.println("7->"+minPathSum(grid));
//}

    public static int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        //初始化值
        dp[0][0]=grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0]=dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            dp[0][i]=dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[m-1][n-1];
    }
    /*
    给定一个无序的整数数组，找到其中最长上升子序列的长度。
示例:
输入: [ 1 0 , 9 , 2 , 5 , 3 , 7 , 1 0 1 , 1 8 ]
输出: 4
解释: 最长的上升子序列是 [ 2 , 3 , 7 , 1 0 1 ]，它的长度是 4。

     */

    public static void main(String[] args) {
        int nums[] = {10 , 9 , 2 , 5 , 3 , 7 , 101 , 18 };
        System.out.println("4->"+lengthOfLIS(nums));

        int[][] obstacleGrid={{0 , 0 , 0 },{0 , 1 , 0 },{0 , 0 , 0}};
        System.out.println("2->"+uniquePathsWithObstacles(obstacleGrid));

        int[] A={1 , 2 , 3 , 2 , 1};
        int[] B={3 , 2 , 1 , 4 , 7};
        System.out.println("3->"+findLength(A,B));
        String word1 = "horse";String word2 = "ros";
        System.out.println("3->"+minDistance(word1,word2));
        String s1="people";String s2="eplm";
        System.out.println("3->"+maxLong(s1,s2));

    }
    public static int lengthOfLIS(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp,1);
        int max=1;
        //全部填充1，表示自己是最大的子序列
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i]>nums[j]){
                    dp[i]=Math.max(dp[i],dp[j]+1);
                    max = Math.max(max,dp[i]);
                }

            }

        }
        return max;
    }
    /*
    一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
    机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记
    为“ F ini sh”）。
    现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
输入:
[
[ 0 , 0 , 0 ] ,
[ 0 , 1 , 0 ] ,
[ 0 , 0 , 0 ]
]
输出: 2
解释:
3 x 3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1 . 向右 -> 向右 -> 向下 -> 向下
2 . 向下 -> 向下 -> 向右 -> 向右
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] dp = new int[m+1][n+1];
        dp[1][1]=1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (obstacleGrid[i-1][j-1]==0)
                    dp[i][j]+=dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m][n];
    }
    /*
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记
为“ F ini sh”）。
问总共有多少条不同的路径？

输入: m = 3 , n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1 . 向右 -> 向右 -> 向下
2 . 向右 -> 向下 -> 向右
3 . 向下 -> 向右 -> 向右

     */
    //排列组合的计算公式如下
    //公式为(m+n-2)! / [(m-1)! * (n-1)!]
    public int uniquePaths(int m, int n) {//利用公式计算

        int N = n + m - 2;
        double res = 1;
        for (int i = 1; i < m; i++)
            res = res * (N - (m - 1) + i) / i;
        return (int) res;
    }
    /*
    给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
示例：
输入：
A: [ 1 , 2 , 3 , 2 , 1 ]
B: [ 3 , 2 , 1 , 4 , 7 ]
输出：3
解释：
长度最长的公共子数组是 [ 3 , 2 , 1 ] 。

     */
    public static int findLength(int[] A, int[] B) {
        int[][] dp = new int[A.length+1][B.length+1];
        int max=0;
        for (int i = 1; i <= A.length; i++) {
            for (int j = 1; j <=B.length ; j++) {
                if (A[i-1]==B[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    max = Math.max(max,dp[i][j]);
                }
            }
        }
        return max;
    }
    /*
    给你两个单词 wo rd 1 和 wo rd 2，请你计算出将 wo rd 1 转换成 wo rd 2 所使用的最少
操作数 。
你可以对一个单词进行如下三种操作：
插入一个字符
删除一个字符
替换一个字符
示例 1：
输入：word1 = " horse ", word2 = "ros"
输出：3
解释：
ho r se -> ro r se (将 ' h ' 替换为 'r')
ror se -> ro se (删除 'r')
ro se -> ro s (删除 ' e ')
总有共3步
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        if (len1*len2==0)
            return len1+len2;
        int[][] dp = new int[len1+1][len2+1];
        //初始化
        for (int i = 0; i <= len1; i++) {
            dp[i][0]=i;
        }
        for (int i = 0; i <= len2; i++) {
            dp[0][i]=i;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[len1][len2];
    }
    /*
    最长公共子串代码
     */
    public static int maxLongLianXu(String str1, String str2) {
        if (str1==null||str2==null||str1.length()==0||str2.length()==0)
            return 0;
        int dp[][] = new int[str1.length()+1][str2.length()+1];
        int max=0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1)==str2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=0;
                max=Math.max(max,dp[i][j]);
            }
        }
        return max;
    }
    /*
    最长公共子序列
     */
    public static int maxLong(String str1, String str2) {
        if (str1==null||str2==null||str1.length()==0||str2.length()==0)
            return 0;
        int dp[][] = new int[str1.length()+1][str2.length()+1];
        //int max=0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i-1)==str2.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1]+1;
                else
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                //max=Math.max(max,dp[i][j]);
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
