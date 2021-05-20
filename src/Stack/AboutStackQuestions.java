package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class AboutStackQuestions {
/*
给你一个整数数组 nums ，你需要找出一个 连续子数组 ，
如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
请你找出符合题意的 最短 子数组，并输出它的长度。

示例 1：
输入：nums = [2,6,4,8,10,9,15]
输出：5
解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *///利用单调栈解决
    public int findUnsortedSubarray(int[] nums) {
        Stack< Integer > stack = new Stack < Integer > ();
        int l = nums.length, r = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i])
                l = Math.min(l, stack.pop());
            stack.push(i);
        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                r = Math.max(r, stack.pop());
            stack.push(i);
        }
        return r - l > 0 ? r - l + 1 : 0;
    }
    /*
    给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

    求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public static void main(String[] args) {
        int nums[]={2,1,5,6,2,3};
        System.out.println(largestRectangleArea(nums));
    }
    //利用栈来解决
    public static int largestRectangleArea(int[] heights) {
        if (heights.length==0)
            return 0;
        if (heights.length==1)
            return heights[0];
        int nums[]=new int[heights.length+2];
        for(int i=1;i<nums.length-1;i++){
            nums[i]=heights[i-1];
        }
        heights=nums;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        //用Deque表示栈
        stack.addLast(0);
        int max=0;
        for (int i = 1; i < heights.length; i++) {
            while (heights[stack.peekLast()]>heights[i]){
                    int height = heights[stack.removeLast()];
                    int width = i- stack.peekLast()-1;
                    max = Math.max(max,width*height);
            }
            stack.addLast(i);
        }
        return max;
    }
    /*
        给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，
        找出只包含 1 的最大矩形，并返回其面积。
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length==0)
            return 0;
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]=='1'){
                    heights[j]+=1;
                }else{
                    heights[j]=0;
                }
            }
            maxArea=Math.max(largestRectangleArea(heights),maxArea);
        }
        return maxArea;
    }

}
