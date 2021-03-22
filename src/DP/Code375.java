package DP;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

/*
* 我们正在玩一个猜数游戏，游戏规则如下：

我从 1 到 n 之间选择一个数字，你来猜我选了哪个数字。

每次你猜错了，我都会告诉你，我选的数字比你的大了或者小了。

然而，当你猜了数字 x 并且猜错了的时候，你需要支付金额为 x 的现金。直到你猜到我选的数字，你才算赢得了这个游戏。
*/
public class Code375 {
    public int get(int a[],int b[],int n){//n为偶数
        //找三张牌 i<j<k
        //a[i]<=a[j]<=a[k]
        //求b[i]+b[j]+b[k]最小

        if (n<3) return -1;
        int min=Integer.MAX_VALUE;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                for (int k = j+1; k < n; k++) {
                    if (a[i]<=a[j]&&a[j]<=a[k]){
                        min=Math.min(min,b[i]+b[j]+b[k]);
                    }
                }
            }
        }
        if (min==Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }

    public int getDP(int a[],int b[],int n){//n为偶数
        //找三张牌 i<j<k
        //a[i]<=a[j]<=a[k]
        //求b[i]+b[j]+b[k]最小
        if (n<3) return -1;
        int dp[][][]=new int[n][n][n];

        int min=Integer.MAX_VALUE;
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                if (a[i]<=a[j]){
                    dp[i][j][0]=b[i]+b[j];
                }
            }
        }
        for (int i = 0; i < n-2; i++) {
            for (int j = i+1; j < n-1; j++) {
                if (dp[i][j][0]!=0){
                    for (int k = j; k < n; k++) {

                    }
                }
            }
        }
        if (min==Integer.MAX_VALUE){
            return -1;
        }
        return min;
    }

    public static void main(String[] args) throws MalformedURLException {
        URL uel = new URL("http://www.baidu.com");
        System.out.println(uel);
        long t = 012;
        float f =-412;
        //int o = (int)true;
        double d =0x12;
        byte bbb=127;
        double a3=0;
        //float aaa =a3;

        Code375 code375 = new Code375();
        int[] a ={4,9,12,13,14,15,16,17,18,19,20,21};
        int[] b ={1,1,0,1};
        System.out.println(code375.getCountAmount(a.length, a));
        //System.out.println(code375.getCountAmount(0));
//        int a[]={7,4,3,6,9,5,1,1,2,8,0};
//        smallLeftBigRight(a,5);
//        System.out.println(Arrays.toString(a));
    }
    public int getCountAmount(int n,int[] a){//n为偶数
        int of = n/2;
        int b[]=new int[n];
        for (int i = 0; i < n; i++) {
            b[i]=Integer.MAX_VALUE;
        }
        int temp=0;
        for (int i = 0; i < n; i++) {
            int count = a[i];
            int sqrt = (int)Math.sqrt(count);
            //sqrt可能接近的值
            b[i]=Math.min(Math.abs(a[i]-sqrt*sqrt),Math.abs(a[i]-(sqrt+1)*(sqrt+1)));
        }
        //判断档刚好为i平方的值的个数
        Arrays.sort(b);
        for (int i = 0; i < n/2; i++) {
            temp += b[i];
        }
        return temp;
    }


    public int getMoneyAmount(int n){
        int money = cost(1, n);
        return money;
    }
    public int cost(int low,int hight){
        if (low>=hight){
            return 0;
        }
        int i1=Integer.MAX_VALUE;
        for (int i = (low+hight)/2; i <= hight; i++) {
             int res = i + Math.max(cost(low, i - 1), cost(i + 1, hight));
             i1=Math.min(i1,res);
        }
        return i1;

    }
    public int getMoneyAmountDP(int n) {
        //创建dp数组
        int[][] dp = new int[n + 1][n + 1];
        //如果所求len为1  表示只有当前这个数  如dp[i][i]=0,只剩下这个数可以进行猜测
        for (int len = 2; len <= n; len++) {
            //长度为1 则dp为空
            for (int start = 1; start <= n - len + 1; start++) {
                //start从1到  n - len + 1(代表什么？)
                int minres = Integer.MAX_VALUE;
                for (int piv = start; piv < start + len - 1; piv++) {
                    //piv表示开始需要猜测的数  start + len - 1表示猜测的距离
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    //当前 piv + 两边的最大的代价，
                    minres = Math.min(res, minres);

                }

                dp[start][start + len - 1] = minres;
            }
        }
        return dp[1][n];//从1到n中的最少
    }


//    public static void main(String[] args) {
//        Code375 code375 = new Code375();
//        System.out.println(code375.getMoneyAmount(25));
//        for (int i = 0; i < 14; i++) {
//            System.out.println("此时N="+i+",结果为"+code375.getMoneyAmount(i));
//            System.out.println("===================");
//        }
//
//    }

    public static void smallLeftBigRight(int a[],int num)
    {
        boolean flag=true;
        while (flag){
            flag=false;
            int i=0;
            int x=-1;
            while (i<a.length)
            {
                if (a[i]<=num)
                {
                    x++;
                    if (x!=i){
                        int temp=a[i];
                        a[i]=a[x];
                        a[x]=temp;
                        flag=true;
                    }
                }
                i++;
            }

        }

    }

}
