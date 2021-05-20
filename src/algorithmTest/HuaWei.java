package algorithmTest;

import java.util.Arrays;
import java.util.Scanner;

public class HuaWei {
    /*
    第一题
     */
        public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int[][] nums = new int[N][3];
        for (int i = 0; i < N; i++) {
            String[] str = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(str[0]);
            nums[i][1]=Integer.parseInt(str[1]);
            nums[i][2]=Integer.parseInt(str[2]);
        }
        //需要排序   M为容量
        int res[] = new int[24];
        //0代表 0-1;依次类推
        for (int i = 0; i < N; i++) {
            int start = nums[i][0];
            int end = nums[i][1];
            int people = nums[i][2];
            boolean flag =true;
            for (int j = start; j < end; j++) {
                if (res[j]+people>M){
                    flag=false;
                    break;
                }
            }
            if (flag){
                //可以添加
                for (int j = start; j < end; j++) {
                    res[j]+=people;
                }
            }

        }
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]+" ");
        }
    }
    /*
    第二题
     */
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int M = Integer.parseInt(s[0]);
        String[] s1 = sc.nextLine().split(" ");
        int N = Integer.parseInt(s1[0]);
        //M 模块总数  N行
        int[][] nums = new int[N][2];
        for (int i = 0; i < N; i++) {
            String[] str = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(str[0]);
            nums[i][1]=Integer.parseInt(str[1]);
        }
        //满分 10分  存在循环依赖 扣2分  无用模块扣 一分
        boolean[] res = new boolean[M];

        int[] used = new int[N];
        int grade=10;
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            if (!helper(res,used,nums,i)&&!flag) {
                flag=true;
            }
        }
        if (flag){
            grade-=2;
        }
        for (int i = 0; i <res.length ; i++) {
            if (!res[i])
                grade-=1;
        }
        grade = grade<0?0:grade;
        System.out.println(grade);


    }
    //used[i]=0表示没用
    //used[i]=1表示正在用
    //used[i]=2表示用完
    public static boolean helper( boolean[] res,int[] used,int[][] nums,int index) {
        used[index] = 1;//used[i]=1表示正在用
        res[nums[index][0]] = true;
        res[nums[index][1]] = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i][0] == nums[index][1]) {
                if (used[i] == 1) {
                    //访问到正在被访问的东西
                    return false;
                }
                if (!helper(res, used, nums, i)) {
                    return false;
                }
            }
        }
        //这个过程完了
        used[index] = 2;
        return true;
    }

    /*
    1.先找出最长的一条路径 如 1-2-3-4-5-6-7
    2.但是在 3节点上有 8  5点上有9-10

     */
    /*
    第三题
     */
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int k = Integer.parseInt(s[0]);
        int[] tips=new int[k];
        for (int i = 0; i < k; i++) {
            String[] s1 = sc.nextLine().split(" ");
            tips[i] = Integer.parseInt(s1[0]);
        }
        String[] strings = sc.nextLine().split(" ");
        int N = Integer.parseInt(strings[0]);
        int E = Integer.parseInt(strings[1]);
        int nums[][] = new int[E][2];
        for (int i = 0; i < E; i++) {
            String[] str = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(str[0]);
            nums[i][1]=Integer.parseInt(str[1]);
        }
        //跳数 tips [k]  数组nums[E][2]

        for (int j = 0; j <N ; j++) {
            helper(tips,nums,k,N,E);
        }


    }
    int max=0;
    public static int helper(int[] tips,int[][] nums,int k,int N,int E){

        return  0;
    }
    /*
    面试题1：
    任务列表 有任务ID为0 ，1 ... N-1，共N个任务的乱序排列数组tasks。
    由于存在资源竞争，某些任务间存在两两互斥关系，并记录在二维数组 mutexPairs 中，该二维数组元素为 [Ni, Nj]，其中 Ni，Nj 为互斥的两个任务编号。
    现在需要对任务列表 tasks 进行切割分组。要求：

    存在互斥关系的任务不能分在同一组
    单个任务也可以单独一组
    一个任务可能和多个任务互斥
    请判断 最少 可以将任务列表 tasks 切割 成几组？（即：切割后的小组是原列表的 连续子数组）

    示例：

    输入：tasks = [1,3,2,4,6,5,0], mutexPairs = [[1,3],[4,5]]

    输出：3

    解释：任务1，3 不能被分在同一组；4，5 不能被分在同一组，所以最终只能将任务列表分成3组。例如，[1],[3,2,4,6],[5,0] 为其中一种分法；[1],[3,2,4],[6,5,0] 为另一种分法。
    注意：切割后的小组是原列表的连续子数组。

     */
    public static void main(String[] args) {
        int[] tasks = {1, 3,2,4,6, 5,0};
        int[][] mutexPairs={{1,3},{4,5}};
        int[] count = new int[mutexPairs.length];
        int sum = 1;//个数

        if (tasks.length<1)//没有数据
            System.out.println(0);
        for (int i = 0; i < tasks.length; i++) {
            for (int j = 0; j < mutexPairs.length; j++) {//从数组中判断是否包含在其中  并且对应
                if (mutexPairs[j][0]==tasks[i]||mutexPairs[j][1]==tasks[i]){//寻找到对应的数
                    if (count[j]==0){
                        count[j]++;
                        break;
                    }else{
                        //代表第j组已经使用过
                        //需要将count置为0
                        Arrays.fill(count,0);
                        sum++;
                        //代表从新划分
                    }
                }
            }


        }
        System.out.print(sum);

    }

}