package dataStructure;

import java.util.Comparator;
import java.util.PriorityQueue;
//中位数巧妙的利用大顶堆与小顶堆  分成两部分组合完成

/*
中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。

例如，
[2,3,4] 的中位数是 3
[2,3] 的中位数是 (2 + 3) / 2 = 2.5
设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
 */
public class MedianFinder {
    int count;//用来计算添加的总数
    /*
    利用中位数性质  分为前大顶堆 与后小顶堆
    中位数的大小取决于大顶堆与小顶堆的顶
     */
    PriorityQueue<Integer> maxheap;
    PriorityQueue<Integer> minheap;

    /** initialize your data structure here. */
    public MedianFinder() {//初始化
        this.count=0;
        //大顶堆如何构建
        maxheap=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        //构建默认为小顶堆
        minheap=new PriorityQueue<>();
    }

    public void addNum(int num) {
        count++;
        if (count==1){
            //表明只有一个数字
            maxheap.add(num);
            return;
        }
        if (num<maxheap.peek()){
            //表明次数应该存放在大顶堆中
            maxheap.add(num);
            if ((count & 1) ==0){
                minheap.add(maxheap.poll());
            }
        }
        else{//应该存放到小顶堆中
            minheap.add(num);
            if ((count & 1) !=0){
                maxheap.add(minheap.poll());
            }
        }

    }

    public double findMedian() {
        if(count==0){
            return 0;
        }
        //判断总数是偶数还是奇数
        if ((count & 1)==0){//等价于count%2==0判断是否为偶数 此时为偶数
            // 如果两个堆合起来的元素个数是偶数，数据流的中位数就是各自堆顶元素的平均值
            return (double)(maxheap.peek()+minheap.peek())/2;
        }
        else{
            // 如果两个堆合起来的元素个数是奇数，数据流的中位数大顶堆的堆顶元素
            return (double)maxheap.peek();
        }
    }
}
