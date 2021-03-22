package dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Code239 {
    /*
    给你一个整数数组 nums，
    有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
    你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

    返回滑动窗口中的最大值。
     */
    public int[] maxSlidingWindow(int[] nums,int k,int p){
        int n = nums.length;
        int[] output = new int[n-k+1];
        for (int i = 0; i < n - k + 1; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i; j < i+k; j++) {
                max = Math.max(max,nums[j]);
            }
            output[i]=max;
        }
        return output;
    }

    class MonotonicQueue {
        LinkedList<Integer> q = new LinkedList<>();
        public void push(int n) {
            // 将小于 n 的元素全部删除
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            // 然后将 n 加入尾部
            q.addLast(n);
        }

        public int max() {
            return q.getFirst();
        }

        public void pop(int n) {
            if (n == q.getFirst()) {
                q.pollFirst();
            }
        }
    }

    /* 解题函数的实现 */
    int[] maxSlidingWindow(int[] nums, int k) {

        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先填满窗口的前 k - 1
                window.push(nums[i]);
            } else {
                // 窗口向前滑动，加入新数字
                window.push(nums[i]);
                // 记录当前窗口的最大值
                res.add(window.max());
                // 移出旧数字
                window.pop(nums[i - k + 1]);
            }
        }
        // 需要转成 int[] 数组再返回
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
    //利用暴力法对滑动窗口中的最大值求解
    public int[] maxSlidingWindow(int[] nums, int k,String zuijia) {
        if(k == 1)
            return nums;

        int maxIdx = 0;
        for(int i = 0; i < k; i++) {
            if(nums[i] > nums[maxIdx])
                maxIdx = i;
        }

        int[] maxes = new int[nums.length - k + 1];
        for(int li = 0; li < maxes.length; li++) {
            int ri = li + k - 1;
            if(maxIdx < li) {
                maxIdx = li;
                for(int i = li + 1; i <= ri; i++) {
                    if(nums[i] > nums[maxIdx])
                        maxIdx = i;
                }
            }else if(nums[ri] > nums[maxIdx]) {
                maxIdx = ri;
            }
            maxes[li] = nums[maxIdx];
        }
        return maxes;
    }

}
