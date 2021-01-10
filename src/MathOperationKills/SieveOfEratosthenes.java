package MathOperationKills;

import java.util.Arrays;

public class SieveOfEratosthenes {//厄拉多塞筛法，简称埃氏筛
    //高效实现求素数的算法

    //首先从 2 开始，我们知道 2 是一个素数，那么 2 × 2 = 4, 3 × 2 = 6, 4 × 2 = 8... 都不可能是素数了。
    //然后我们发现 3 也是素数，那么 3 × 2 = 6, 3 × 3 = 9, 3 × 4 = 12... 也都不可能是素数了。
    int countPrimes(int n) {
        boolean[] isPrim = new boolean[n];
        Arrays.fill(isPrim, true);
        //首先，回想刚才判断一个数是否是素数的 isPrime 函数，由于因子的对称性，
        // 其中的 for 循环只需要遍历 [2,sqrt(n)] 就够了
        for (int i = 2; i * i < n; i++)
            if (isPrim[i])
                //比如 n = 25，i = 4 时算法会标记 4 × 2 = 8，4 × 3 = 12 等等数字，
                // 但是这两个数字已经被 i = 2 和 i = 3 的 2 × 4 和 3 × 4 标记了。
                //我们可以稍微优化一下，让 j 从 i 的平方开始遍历，而不是从 2 * i 开始：
                for (int j = i * i; j < n; j += i)
                    isPrim[j] = false;

        int count = 0;
        for (int i = 2; i < n; i++)
            if (isPrim[i]) count++;

        return count;
    }
    //O(N * loglogN)
}
