package algorithmTest;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class JD {
    private static int count;
    // 左右两边可存放一个数  相同就得分为1  给一个数组 判断得分

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = Integer.valueOf(sc.nextLine());
        String[] str = sc.nextLine().split(" ");
        int a[]= new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=Integer.valueOf(str[i]);
        }
        int dp[][] = new int[n+1][n+1];

        int helper = helper(0, a, 0, 0,dp);

        System.out.println(helper);

    }
    public static int helper(int i,int[] a,int left,int right,int[][] dp){
        //结束条件
        if(i>=a.length){
            return 0;
        }
        //备忘录作用


        //相等
        if (a[i]==left||a[i]==right){
            count++;
            return 1+helper(i+1,a,left,right,dp);

        }
        //不相等
        return Math.max(helper(i+1,a,a[i],right,dp),helper(i+1,a,left,a[i],dp));

    }
//n 条线 判断两两相交的个数  输出2-n条直线相交的节点个数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = Integer.valueOf(sc.nextLine());
        double[] k = new double[n];
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            String[] str = sc.nextLine().split(" ");

            k[i]=Double.valueOf(str[0]);
            b[i]=Double.valueOf(str[1]);

        }
        Map<double[],Integer> map =new HashMap<>();
        int count=0;
        // y = 2x + 2
        // y = 3x + 0
        // y = 0x + 3
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                double[] doubles = new double[2];
                doubles[0]=(b[j]-b[i])/(k[i]-k[j]);// x
                doubles[1]=k[i]*doubles[0]+b[i];//    y
                //判断一个数是不是整数？
                if (doubles[0]%(int)doubles[0]==0||doubles[0]==0.0&&doubles[1]%(int)doubles[1]==0||doubles[1]==0.0) {
                    count++;
                    if(!map.containsKey(doubles)){
                        map.put(doubles,1);
                    }else{
                        map.put(doubles,map.get(doubles)+1);
                    }
                }

            }
        }
        Iterator iterator = map.keySet().iterator();
        System.out.print(count);
        //System.out.print(" ");
        int z=1;
        for (int i = 2; i <n; i++) {//n=3
            int j=0;
            z+=i;
            while (iterator.hasNext()){

                if(map.get(iterator.next())>=z)
                {
                    j++;
                }
            }
            System.out.print(" ");
            System.out.print(j);
            //System.out.print(" ");
        }
        // System.out.println(-1);

    }


}
