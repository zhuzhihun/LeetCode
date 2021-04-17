package algorithmTest;

import java.util.Scanner;
/*
*
算法1. 1->N dp[i]=Math.max(dp[i-2]+a[i],dp[i-1]+a[i]/2);
走一步就获取前一步一半的积分，走两步就获取全部积分，求最大
算法2. 前序中序构建二叉树，得到最大对称子树
* */
public class Ali {

    public static void main(String[] args) {
        Ali ali = new Ali();
        int[] as = {1,2,3,4,5};
        //System.out.println(ali.getGrade(as));


        Scanner sc = new Scanner(System.in);
        //输入一个正整数
        int n = sc.nextInt();//n个测试用例

        for(int i = 0; i < n; i++){
            int length = sc.nextInt();//每组测试用例个数
            int[] a=new int[length];

            for(int j = 0; j < length; j++){
                a[j] = sc.nextInt();

            }
            ali.getGrade(a);
        }
        //System.out.println(ans);

    }
    public void getGrade(int[] a){
        //1->n
        if (a.length<2){
            System.out.println(a[0]);
        }
        if (a.length<3){
            System.out.println((a[0] + a[1] / 2));
        }
        int[] dp = new int[a.length];
        dp[0]=a[0];
        dp[1]=a[0]+a[1]/2;
        for (int i = 2; i <a.length ; i++) {
            dp[i]=Math.max(dp[i-1]+a[i]/2,dp[i-2]+a[i]);
        }
        System.out.println(dp[a.length-1]);
    }


}
class four{
    public static void main(String[] args) {
        int a[] = {1,3,4,5,2};
    }
    public static int getKDin(int a[]){



        return 1;
    }
}
