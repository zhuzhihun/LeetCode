package algorithmTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class NeteaseGames {//网易游戏
    public static int getMatchBracketsNum (String inString, int inStringNum) {
    // write code here
    char[] ch = inString.toCharArray();
    for (int i = 0; i < inStringNum; i++) {
        System.out.println(ch[i]);
    }
    Stack<Character> stack = new Stack<>();
    int x = 0;//{}
    int m = 0;//[]
    int l = 0;//()
    //"[({}[])]",8
    for (int i = 0; i < inStringNum; i++) {
        if (ch[i]=='}'){
            if (!stack.isEmpty() && stack.peek()=='{'){
                stack.pop();
                x++;
            }

            else return -1;
        }else if (ch[i]==']'){
            if (!stack.isEmpty() && stack.peek()=='[')
            {
                stack.pop();
                m++;
            }
            else return -1;
        }else if (ch[i]==')'){
            if (!stack.isEmpty() && stack.peek()=='(')
            {
                stack.pop();
                l++;
            }
            else return -1;
        }else{
            stack.add(ch[i]);
        }
    }
    if (!stack.isEmpty()){
        return -1;
    }
    return l+2*m+3*x;

}

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 搜索满足条件的日志。 返回值为二维数组，第一维对应待查询时间戳的下标，第二维是命中日志的事件ID
     * @param logs long长整型二维数组 日志数据，数组第一维的下标即为事件ID，第二维包含两个long型整数，分别表示起始和结束时间
     * @param tss long长整型一维数组 timestamps，表示待查询的多个时间戳
     * @return int整型二维数组
     */
    /*
    [[1610000000000,1620000000000], //logs
    [1610000000000,1610000001000],
    [1615000000000,1620000000000]],

    [1610000002000,1616000000000,1630000000000] //tes
     */
//    public static void main(String[] args) {
////[[1610000000000,1620000000000], [1610000000000,1610000001000], [1615000000000,1620000000000]],[1610000002000,1616000000000,1630000000000]
//        long[][] logs={{1610000000000L,1620000000000L},
//        {1610000000000L,1610000001000L},
//        {1615000000000L,1620000000000L}};
//        long[] tss = {1610000002000L,1616000000000L,1630000000000L};
//        search_log(logs,tss);
//    }
    //87.5%  超时
    public static int[][] search_log (long[][] logs, long[] tss) {
        // write code here
        int[][] res = new int[tss.length][];
        if (logs.length==0||tss.length==0)
            return new int[0][0];

        //处理
        for (int i = 0; i < tss.length; i++) {
            //n个时间戳中进行 遍历查找
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < logs.length; j++) {
                //if判断符合条件的时间戳
                if (tss[i]>=logs[j][0]&&tss[i]<=logs[j][1]){//时间戳在范围里面
                    list.add(j);
                }
            }

            int r[] = new int[list.size()];
            for (int j = 0; j < list.size(); j++) {
                r[j]=list.get(j);
            }
            res[i]=r;
        }
        return res;
    }
    //62.5%   越界情况
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String m = sc.nextLine().trim();//初始序列长度n
        int n =Integer.valueOf(m);
        //System.out.println(n);
        int[] a = new int[n];

        String[] strings = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i]=Integer.parseInt(strings[i]);
        }
        //测试输出
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i]);
//        }
        if (n<1){
            System.out.println(0);
            return;
        }
        if (n<2){
            System.out.println(Math.max(0,a[0]));
        }

//        int dp[]= new int[n];//都是dp  都一样啊
//        int j=0;
//        dp[0]=(int)(a[0]*Math.pow(-1,j%2));
//        j++;
//        for (int i = 1; i < n; i++) {
//            int u = dp[i-1]+()
//        }


//        int[][] dp = new int[n][2];
//
//        //dp[i][0] 表示为正数直接加
//        //dp[i][1]  表示为负数
//        int max = 0;
//        dp[0][0]=a[0];
//        for (int i = 1; i < n; i++) {
//            dp[i][0]=Math.max(dp[i-1][1]+a[i],a[i]);//正数两种情况，从自己开始，或者前面的负数加自己
//            dp[i][1]=dp[i-1][0]-a[i];//负数只有一种情况  就是前面正数 加上现在的负数
//            max = Math.max(max,Math.max(dp[i][0],dp[i][1]));
//        }
//        System.out.println(max);



        long[][] dp = new long[n][n];
        long max=0L;//最小值为0
        for (int i = 0; i < n; i++) {
            dp[i][i] = a[i];
            max = Math.max(max,a[i]);
        }
        //初始化  每个从第i个位置开始的值等于自己
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                //判断是需要加  还是需要减
                if ((j-i)%2==0){
                    dp[i][j]=dp[i][j-1]+a[j];
                }
                else{
                    dp[i][j]=dp[i][j-1]-a[j];
                }
                //每次记录最大值
                max = Math.max(max,dp[i][j]);
            }
        }
        System.out.println(max);
    }
}
