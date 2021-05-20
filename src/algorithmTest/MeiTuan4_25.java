package algorithmTest;

import org.omg.CORBA.INTERNAL;

import java.util.Scanner;

public class MeiTuan4_25 {

    public static void main2(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String c = "abcdefghijklmnopqrstuvwxyz";
        char[] chars = s.toCharArray();
        int N = Integer.parseInt(sc.nextLine());
        int nums[][]=new int[N][3];
        for (int i = 0; i < N; i++) {
            String str[] = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(str[0]);
            nums[i][1]=Integer.parseInt(str[1]);
            nums[i][2]=Integer.parseInt(str[2]);
        }

        for (int i = N-1; i >=0; i--) {
            int start = nums[i][0];
            int end = nums[i][1];
            int k=nums[i][2];
            for (int j = start-1; j <= end-1; j++) {
                chars[j]=c.charAt((c.indexOf(chars[j])+c.length()-k) % c.length());
            }
        }
        String res =new String(chars);
        System.out.println(res);

    }


    public static void main1(String[] args) {
        String[] t = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int c = Integer.parseInt(s[1]);
        while (sc.hasNext()) {
            int q = sc.nextInt();
            int d = q / c;
            int mod = q % c;
            if (mod == 0) {
                d -= 1;
                mod = c;
            }
            System.out.println(t[d]+mod);
        }
    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n  = Integer.parseInt(sc.nextLine());
        char nums[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            for (int j = 0; j < s.length; j++) {
                nums[i][j]=s[j].charAt(0);
            }
        }
        int arrStr[][][]=new int[n+1][n+1][2];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums[i-1][j-1]=='.')
                    arrStr[i][j][0]=arrStr[i][j-1][0]+1;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums[j-1][i-1]=='.')
                    arrStr[j][i][1]=arrStr[j-1][i][1]+1;
            }
        }



        int dp[][] = new int[n+1][n+1];//0 行 1 列 2 对角
        int sum=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums[i-1][j-1]=='#')
                    continue;
                dp[i][j]=arrStr[i][j][0]*arrStr[i][j][1];
                sum+=dp[i][j];
            }
        }
        System.out.println(sum);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] s = sc.nextLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int k = Integer.parseInt(s[2]);
        int zidan[] = new int[k];
        String[] ss = sc.nextLine().split(" ");
        for (int i = 0; i < k; i++) {
            zidan[i]=Integer.parseInt(ss[i]);
        }
        int nums[][] = new int[m][2];
        for (int i = 0; i < m; i++) {
            String[] sss = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(sss[0]);
            nums[i][1]=Integer.parseInt(sss[1]);
        }
        if (m>n+k){
            System.out.println(-1);
            return;
        }
        int res[] = new int[n];
        for (int i = 0; i < k; i++) {//m时敌人
            int time = zidan[k];
            for (int j = 0; j < m; j++) {//k时子弹数
                //int time = zidan[j];
                if (nums[j][0]<time){
                    if (res[nums[j][1]]==1){
                        System.out.println(-1);
                        return;
                    }
                    res[nums[j][1]]=1;
                }
                if (nums[j][0]==time){
                    //必须得射
                }else{
                    //可以选择不射这个
                }


            }

        }

    }
    /*
     * 男女用餐问题
     * */
    public static void MFEatFood(){
            Scanner s = new Scanner(System.in);
            int T = Integer.parseInt(s.nextLine());
            //数组组数
            while (s.hasNext()){
                int N = Integer.parseInt(s.nextLine());
                String str = s.nextLine();
                int table[] = new int[N];
                for(int i=0;i<N;i++){
                    //str.indexOf();
                    table[i]=Integer.parseInt(String.valueOf(str.charAt(i)));
                }
                int M = Integer.parseInt(s.nextLine());
                String MF = s.nextLine();
                char people[] = new char[M];
                for(int i=0;i<M;i++){
                    char c = MF.charAt(i);
                    people[i]= c;
                }
                //处理
                for(int i=0;i<M;i++){
                    int q=-1;
                    boolean flag=true;
                    if(people[i]=='M'){//男

                        for(int j=0;j<N;j++){
                            if(table[j]==2)
                                continue;
                            if(table[j]==1){
                                table[j]=2;
                                System.out.println(j+1);
                                flag=false;
                                break;
                            }
                            if(table[j]==0&&q==-1){
                                q=j;
                            }
                        }

                        if(q!=-1&&flag){
                            table[q]=1;
                            System.out.println(q+1);
                        }

                    }else{//女

                        for(int j=0;j<N;j++){

                            if(table[j]==2)
                                continue;

                            if(table[j]==0){
                                table[j]=1;
                                System.out.println(j+1);
                                flag=false;
                                break;
                            }
                            if(table[j]==1&&q==-1){
                                q=j;
                            }
                        }
                        if(q!=-1&&flag){
                            table[q]+=1;
                            System.out.println(q+1);
                        }

                    }

                }

                //输出

                //下一组测试用例
            }

        }

}
