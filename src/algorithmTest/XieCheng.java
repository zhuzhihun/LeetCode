package algorithmTest;

import java.util.Arrays;
import java.util.Scanner;

public class XieCheng {
    public static void main(String[] args) {
    //第一行读入一个整数n，表示有n（1<=n<=10）个订单；
    //
    //第二行读入用空格分隔的整数，表示订单的开始时间。
    //
    //第三行读入用空格分隔的整数，表示订单的结束时间。
    //
    //第四行读入用空格分隔的整数，表示订单金额。

    //样例输入
    //4
    //1 2 3 3
    //3 4 5 6
    //200 150 180 210
    //样例输出
    //410
    Scanner sc = new Scanner(System.in);
    int n = Integer.parseInt(sc.nextLine());
    String[] str1 = sc.nextLine().split(" ");
    String[] str2 = sc.nextLine().split(" ");
    String[] str3 = sc.nextLine().split(" ");
    int num[][] = new int[str1.length][3];
    for (int i = 0; i < str1.length; i++) {
        num[i][0]=Integer.parseInt(str1[i]);
        num[i][1]=Integer.parseInt(str2[i]);
        num[i][2]=Integer.parseInt(str3[i]);
    }
    if(num.length==0){
        System.out.println(0);
        return;
    }
    if (num.length<2){
        System.out.println(num[0][2]);
        return;
    }
    Arrays.sort(num, (o1, o2) -> {
        if(o1[0]==o2[0]){

            return o2[2] - o1[2];

        }
        else {
            return o1[0]-o2[0];
        }
    });
    //1 2 3 3
    //3 4 5 6
    //200 150 180 210
    int[] time = new int[25];//用来表示不同时间
    //将数据都存放到num中
    //订单是按顺序来还是可以任意安排
    //任意安排顺序
    getTheMax(num, 0, time,0);

    System.out.println(max);
}
    private static int max=0;
    public static void  getTheMax(int num[][], int index,int time[],int count){
        //结束条件
        if (index==num.length){
            max=Math.max(max,count);
            return;
        }
        boolean flag=true;
        for (int j = num[index][0]; j < num[index][1]; j++) {
            if(time[j]!=0){
                flag=false;
                break;
            }
        }
        if(flag){//区间都可以用
            //选择租
            for (int i = num[index][0]; i <num[index][1] ; i++) {
                time[i]=1;
            }
            getTheMax(num, index+1,  time,count+num[index][2]);
            //回溯
            for (int i = num[index][0]; i <num[index][1] ; i++) {
                time[i]=0;
            }
        }
        //不租的选择  即可以是 不能租时不租 也可以时能租时不租
        getTheMax(num, index+1,  time,count);

    }



}
class XieCheng1 {//携程第一题
    public static int max=0;
    /*请完成下面这个函数，实现题目要求的功能
        当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
        ******************************开始写代码******************************/
    public static int procee(int[] scores, int[] cards) {
        //scores=[1,2,3,4]
        //cards=[1,2]
        int card[] = new int[5];
        for (int i = 0; i < cards.length; i++) {
            card[cards[i]]+=1;
        }
        //记录不同卡片的张数
        proceeBack(cards.length,scores,card,0,0,0,0);
        return max;
    }
    private static void proceeBack(int k,int[] scores, int[] cards, int index,int count,int ii,int sy) {
        //结束条件
        if (index==k){
            max = Math.max(max,count+scores[ii]);
            return;
        }
        //
        for (int i = 1; i < cards.length; i++) {
            //1,2,3,4 四种方案
            if(cards[i]==0)
                continue;
            //使用这张卡片
            cards[i]-=1;
            proceeBack(k,scores, cards, index+1,  count+scores[ii],ii+i,i);
            cards[i]+=1;
        }

    }
    /******************************结束写代码******************************/
/*
样例输入
4
1
2
3
4
2
1
2
样例输出
8
 */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _scores_size = 0;
        _scores_size = Integer.parseInt(in.nextLine().trim());
        int[] _scores = new int[_scores_size];
        int _scores_item;
        for(int _scores_i = 0; _scores_i < _scores_size; _scores_i++) {
            _scores_item = Integer.parseInt(in.nextLine().trim());
            _scores[_scores_i] = _scores_item;
        }

        int _cards_size = 0;
        _cards_size = Integer.parseInt(in.nextLine().trim());
        int[] _cards = new int[_cards_size];
        int _cards_item;
        for(int _cards_i = 0; _cards_i < _cards_size; _cards_i++) {
            _cards_item = Integer.parseInt(in.nextLine().trim());
            _cards[_cards_i] = _cards_item;
        }
        //输入处理
        res = procee(_scores, _cards);
        System.out.println(String.valueOf(res));

    }
}