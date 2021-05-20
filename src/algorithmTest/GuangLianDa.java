package algorithmTest;

import java.util.*;

public class GuangLianDa {



    private static int mas;

    public static void main(String[] args) {
        /* input */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); /* 敌人数量 */
        int k = sc.nextInt(); /* 装弹时间 */
        int container = 4; /* 子弹容量 */
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        int dp[][]=new int [n][2];//0表示不打，1表示打

        helper(0,0,n,4,k,p,0);
        System.out.println(mas);


    }

    public static void helper(int count,int start, int end, int zidan,int time,int[] nums,int index){
        if (index>=nums.length){
            return;
        }
        if (zidan==1){
            //双倍快乐
            count+=nums[index]*2;
            mas=Math.max(mas,count);
            helper(count, start, end, 4, time, nums, index+1+time);
            count-=nums[index]*2;
        }else{
            //
            count+=nums[index];
            mas=Math.max(mas,count);
            helper(count, start, end, zidan-1, time, nums, index+1);
            count-=nums[index];
        }

        helper(count, start,end, zidan, time, nums, index+1);

    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s1= sc.nextLine().split(" ");
        int n = Integer.parseInt(s1[0]);
        int m = Integer.parseInt(s1[1]);
        int nums[]= new int[n];
        String st[] = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i]=Integer.parseInt(st[i]);
        }
        //int count[] =new int[3];
        for (int i = 0; i < m; i++) {
            String st1[] = sc.nextLine().split(" ");
            int x=0;
            int count[] =new int[3];
            count[0]=Integer.parseInt(st1[0]);
            count[1]=Integer.parseInt(st1[1]);
            count[2]=Integer.parseInt(st1[2]);

            for (int j = count[0]-1; j <count[1] ; j++) {
                x++;
                nums[j]+=(count[2]*x);
                nums[j]=nums[j]%1000000007;
            }

        }
        int max =0 ;
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            System.out.print(" ");
        }
        //System.out.println(max);
    }

    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        for (int i = y; i >=x ; i--) {
            hhelper(i,x,new ArrayList<Integer>());
        }
        System.out.println(max);

    }
    private static int max=0;
    public static void hhelper(int start, int end, ArrayList<Integer> res){
        if (start<end){
            return;
        }
        if (res.size()==0){
            res.add(start);
        }

        for (int i = start-1; i >=end ; i--) {
            if (res.get(res.size()-1)%i==0){
                res.add(i);
                hhelper(i,end,res);
                res.remove(res.size()-1);
            }
        }
        max=Math.max(max,res.size());
    }


}

