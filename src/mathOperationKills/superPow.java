package mathOperationKills;

import java.util.LinkedList;

public class superPow {
    /* code.172
    给定一个整数 n，返回 n! 结果尾数中零的数量。
     */
    //首先，两个数相乘结果末尾有 0，一定是因为两个数中有因子 2 和 5，因为 10 = 2 x 5。
    //
    //也就是说，问题转化为：n! 最多可以分解出多少个因子 2 和 5？
    //现在，问题转化为：n! 最多可以分解出多少个因子 5？

    //难点在于像 25，50，125 这样的数，可以提供不止一个因子 5，怎么才能不漏掉呢？
    public int trailingZeroes(int n) {
        int res = 0;
        for (int d = n; d / 5 > 0; d = d / 5) {
            res += d / 5;
        }
        return res;
    }

    /* 372.超级次方
    你的任务是计算 ab 对 1337 取模，
    a 是一个正整数，b 是一个非常大的正整数且会以数组形式给出。
     */
    //一是如何处理用数组表示的指数，现在 b 是一个数组，也就是说 b 可以非常大，没办法直接转成整型，否则可能溢出。你怎么把这个数组作为指数，进行运算呢？
    //
    //二是如何得到求模之后的结果？按道理，起码应该先把幂运算结果算出来，然后做 % 1337 这个运算。但问题是，指数运算你懂得，真实结果肯定会大得吓人，也就是说，算出来真实结果也没办法表示，早都溢出报错了。
    //
    //三是如何高效进行幂运算，进行幂运算也是有算法技巧的，如果你不了解这个算法，后文会讲解。
    /*a[1,5,6,4]
     = a[4]  *  a[1,5,6,0]
     = a[4] * (a[1,5,6])[10]
     */
    //(a * b) % k = (a % k)(b % k) % k  技巧
    int mu = 1337;
    public int superPow(int a, int[] b) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        for(int bb:b){
            list.add(bb);
        }
        return superP(a,list);

    }
    public int superP(int a, LinkedList<Integer> list){
        if(list.isEmpty()){
            return 1;
        }
        int last = list.peekLast();
        list.pollLast();
        int res1 = quickMul(a,last);
        int res2 = quickMul(superP(a,list),10);
        return res1*res2%mu;
    }
    public int quickMul(int a, int n){

        if(n == 0){
            return 1;
        }
        a %= 1337;
        int ans = 1;
        int x = a;
        while(n>0){
            if(n % 2 == 1){
                ans = ans*x%mu;
            }
            x = x*x%mu;
            n /= 2;
        }
        return ans%mu;
    }
}
