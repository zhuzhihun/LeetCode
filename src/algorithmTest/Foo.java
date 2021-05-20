package algorithmTest;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
class Foo {

    private AtomicInteger firstJobDone = new AtomicInteger(0);
    private AtomicInteger first = new AtomicInteger(0);
    private AtomicInteger second = new AtomicInteger(0);
    private AtomicInteger secondJobDone = new AtomicInteger(0);

    public Foo() {
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first".
        printFirst.run();
        first.incrementAndGet();
        // mark the first job as done, by increasing its count.
        firstJobDone.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstJobDone.get() != 1) {
            // waiting for the first job to be done.
        }
        while (first.get()!=1){

        }
        // printSecond.run() outputs "second".
        printSecond.run();
        second.incrementAndGet();
        // mark the second as done, by increasing its count.
        secondJobDone.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondJobDone.get() != 1) {
            // waiting for the second job to be done.
        }
        while (second.get()!=1){

        }
        // printThird.run() outputs "third".
        printThird.run();
    }
    public String[] split(String s,char c){
        return s.split(String.valueOf(c));

    }
    private static void perfectNumber(int n)   // 编写一个perfectNumber 的方法来找出完数
    {
        System.out.println(n+"以内的完数为：");

        for(int i=1;i<n+1;i++)                 // 循环 10000 及以内的数， 此处  i<n+1  也可以写成  i<=n
        {
            int sum=0;
            for(int j=1;j<i/2+1;j++)
            {
                if(i%j==0)                     // 找出所有真因子
                {
                    sum+=j;                    // 使所有找出的真因子一个个相加。
                    if(j==i/2 && sum==i)       /* 此处的 j==i/2 是因为： 如 24=1+2+3+4+6+8   并不是所有真因子之和，缺了真因子12。  所以此处强制先行判断是否求出所有真因子。*/
                    {
                        System.out.print(i+"  ");
                    }
                }
            }
        }
    }
}