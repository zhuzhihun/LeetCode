package algorithmTest;

import java.util.*;

public class MeiTuan {
    public static void main(String[] args) {

    }

}
//==============================================================
//(10%).一个数组，计算其中的子数组 如aabc  2^n   a与a认为不同
// {" ","a","a","b","c","ab","ab","ac","ac","bc","abc,"abc"}
class Main01{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Map<Character,Integer> map =new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                map.put(ch,map.get(ch)+1);
            }else{
                map.put(ch,1);
            }

        }
        int sum=0;
        Main01 main = new Main01();
        if(map.isEmpty()){
            System.out.println(main.f(length)%20210101);
            return;
        }

        Iterator iterator = map.keySet().iterator();
        //for (int i = 0; i < map.size(); i++){
        //map.
        //}
        while (iterator.hasNext()){
            int a= map.get(iterator.next());
            //第一个元素重复个数
            if(a>1){
                sum+= (a*main.f(length-a+1)-(a-1)*main.f(length-a))%20210101;
            }


        }
        System.out.println(sum);
    }
    public int f(int a){
        return (int)Math.pow(2,a);
    }
}

//=============================================================
//2(80%).沿着经度或者维度切蛋糕  -> 计算分别的刀数  2*x*(y+1)
class Main02{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        //int N = sc.nextInt();
        String s = sc.nextLine();
        int N = Integer.parseInt(s);
        int x = 0;
        int y = 0 ;
        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            String[] asd = sc.nextLine().split(" ");
            if (asd[0].equals("1")){
                Integer integer = Integer.valueOf(asd[1]);
                String another = String.valueOf(integer-180);
                if (!set.contains(asd[1])&&!set.contains(another)){
                    x++;
                    set.add(asd[1]);
                }

            }else{
                y++;
            }
        }


        System.out.println(2*x*(y+1));

    }
}
//===================================================================================
//3(AC).记录不高兴值,每组最多为1.判断一个数的因素组合是否包含讨厌值k.包含就加一,记录数组的讨厌值为多少.
class Main03{
        public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] nk = sc.nextLine().split(" ");
//        int n = sc.nextInt();
//        int k = sc.nextInt();

        String[] s = sc.nextLine().split(" ");
        int[] a = new int[s.length];
        for (int i = 0; i <a.length ; i++) {
            a[i]=Integer.valueOf(s[i]);
        }
        int sum=0;//记录不高兴值
        //先得到f（）
            Main03 main = new Main03();
        String[] res = main.f(a);
        String target = nk[1];
        for (int i = 0; i < res.length; i++) {
            String str = res[i];
            if(str.contains(target)){
                sum++;
                continue;
            }

        }
        System.out.println(sum);
    }
    public String[] f(int[] a){
        String[] f=new String[a.length];
        for (int i = 0; i < a.length; i++) {
            List<Integer> list = new ArrayList<>();
            int sq = (int)Math.sqrt(a[i]);
            while (sq>0){
                if(a[i]%sq==0){
                    int aq = a[i]/sq;
                    if(aq==sq){
                        list.add(sq);
                    }else{
                        list.add(sq);
                        list.add(aq);
                    }

                }
                sq--;
            }
            Object[] b=list.toArray();

            Arrays.sort(b);
            //System.out.println(b.toString());
            StringBuffer stringBuffer = new StringBuffer();
            for (int j = 0; j < b.length; j++) {
                stringBuffer.append(b[j]);
            }
            f[i]=String.valueOf(stringBuffer);
        }

        return f;
    }

}


//=============================================================================================
//4.n*n数组，从1按自然数顺序一直到n,否则返回-1.得出最小的(x1-x2)^2+(y1-y2)^2跳一步的距离路径,求最小的路径值.
class Main04{
    public static void main(String[] args) {

    }
}
//==============================================================
    //5.两个数组  按顺序拿 a[i]与b[i]代表甜度 前N的最大甜度,前缀数组
    class Main05{
        public static void main5(String[] args){
            Scanner sc = new Scanner(System.in);
            String[] s = sc.nextLine().split(" ");
            int n = Integer.parseInt(s[0]);
            int k = Integer.parseInt(s[1]);
            int[] a = new int[n];
            int[] b = new int[k];

            String[] str=sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                a[j]= Integer.valueOf(str[j]);
            }
            String[] str1=sc.nextLine().split(" ");
            for (int j = 0; j < k; j++) {
                b[j]= Integer.valueOf(str1[j]);
            }
            //分别取两个数组中最大
            int suma = 0;
            int[] sumA=new int[n+1];
            for (int i = 0; i < n; i++) {
                sumA[i+1]=sumA[i]+a[i];
            }
            int sumb = 0;
            int[] sumB=new int[n+1];
            for (int i = 0; i < n; i++) {
                sumB[i+1]=sumB[i]+b[i];
            }

            //存放进二维数组


        }
//============================================================//
    //美团5.9
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
    }
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++) {
            int x = sc.nextInt();//初始值
            int a = sc.nextInt();//下降值
            int b = sc.nextInt();//上升值
            int n = sc.nextInt();
            int max=0;
            int[][][] dp = new int[n+1][2][2];
            dp[0][0][0]=x;//0代表 休息 1代表联系
            dp[0][0][1]=0;// 0表示当前状态值 1表示收益
            dp[0][1][0]=x;
            dp[0][1][0]=0;
            for (int j = 1; j < dp.length; j++) {
                //休息
                /*


                dp[j][0][0]=dp[j-1][0][0]
                dp[j][0][1]=dp[j-1][0][0]
                    //练习
                dp[j][1][0]=dp[j-1][0][1]
                dp[j][1][0]=dp[j-1][0][0]
                */
            }



        }
    }
    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strings= sc.nextLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int nums[] = new int[n];
        int m = Integer.parseInt(strings[1]);
        int h = Integer.parseInt(strings[2]);
        String[] s = sc.nextLine().split(" ");
        for (int i = 0; i < n; i++) {
            nums[i]=Integer.parseInt(s[i]);
        }
        int min=-1;
        for (int i = 0; i <= n-m; i++) {
            boolean flag =true;
            for (int j = 0; j < m; j++) {
                if (nums[i+j]>h){
                    flag=false;
                    break;
                }

            }
            if (flag){
                min=i+1;
                break;
            }
        }
        System.out.println(min);
    }
    /*
    7 3
    ...
    **.
    ...
    .**
    ...
    ***
     */
    public static void main1(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strings= sc.nextLine().split(" ");
        int row = Integer.parseInt(strings[0]);
        int loc = Integer.parseInt(strings[1]);
        char[][] chars = new char[row][loc];
        for (int i = 0; i < row; i++) {
            String s = sc.nextLine();
            chars[i] = s.toCharArray();
        }
//        for (int i = 0; i < row; i++) {
//            for (int j = 0; j < loc; j++) {
//                System.out.print(chars[i][j]);
//            }
//            System.out.println();
//        }

        getMaxiCountDFS(chars,0,0,1,0,1);
        System.out.println(sum==Integer.MAX_VALUE? -1:sum);


    }
    private static int sum = Integer.MAX_VALUE;
    public static void getMaxiCountDFS(char[][] chars,int i,int j,int wuqi,int count,int fangxiang){
        //1代表右  0代表左 2代表左右都行
        //结束条件
        if (i>=chars.length||j>=chars[0].length||i<0||j<0)//超出边界
            return;
        if (wuqi==0&&chars[i][j]=='*')//遇到无法打破的位置
            return;
        if (i==chars.length-1&&j==chars[0].length-1){//到达最后的位置
            sum = Math.min(sum,count);
            return;
        }
        if (chars[i][j]=='*'){
            getMaxiCountDFS(chars, i+1, j, wuqi-1, count+1,2);//向下
            if (fangxiang==0){
                //从zuo边走
            }else{
                getMaxiCountDFS(chars, i, j+1, wuqi-1, count+1,1);//向右
            }

            if (fangxiang==1){
                //从右边走
            }else{
                getMaxiCountDFS(chars, i, j-1, wuqi, count+1,0);//向左
            }

        }else{
            getMaxiCountDFS(chars, i+1, j, wuqi, count+1,2);//向下
            if (fangxiang==0){
                //从zuo边走
            }else{
                getMaxiCountDFS(chars, i, j+1, wuqi, count+1,1);//向右
            }

            if (fangxiang==1){
                //从右边走
            }else{
                getMaxiCountDFS(chars, i, j-1, wuqi, count+1,0);//向左
            }
        }
        //不能向上跑
    }
}

