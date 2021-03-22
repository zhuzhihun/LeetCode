package dataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
739.请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
要想观测到更高的气温，至少需要等待的天数。
如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

503.给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
496.下一个更大元素
给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。

 */
public class Code496 {
    /*
    739.请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：
要想观测到更高的气温，至少需要等待的天数。
如果气温在这之后都不会升高，请在该位置用 0 来代替。

例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     */
    public int[] dailyTemperatures(int[] T) {
        int n = T.length;
        if (n<1){
            return null;
        }
        int[] res = new int[n];
        Stack<Integer> stack =new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty()&&T[i]>T[stack.peek()]){
                res[stack.peek()]=i-stack.pop();
            }
            stack.add(i);
        }
        return res;
    }
    /*
    503. 下一个更大元素 II
    给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），
    输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，
    这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。
    如果不存在，则输出 -1。
     */
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for(int i=0;i<nums.length;i++){
            res[i]=Integer.MIN_VALUE;
            //寻找num后面部分
            for (int k = i+1; k < nums.length; k++) {
                if (nums[k]>nums[i]){
                    res[i]=nums[k];
                    break;
                }
            }
            if (res[i]==Integer.MIN_VALUE){//当没有找到大于当前的值  遍历前面的数组
                //寻找num前面部分
                for (int k = 0; k < i; k++) {
                    if (nums[k]>nums[i]){
                        res[i]=nums[k];
                        break;
                    }
                }
            }
            if (res[i]==Integer.MIN_VALUE){
                res[i]=-1;
            }
        }
        return res;
    }
/*
方法一：单调栈
我们可以使用单调栈来解决这个问题。

我们首先把第一个元素 A[1] 放入栈，随后对于第二个元素 A[2]，如果 A[2] > A[1]，
那么我们就找到了 A[1] 的下一个更大元素 A[2]，此时就可以把 A[1] 出栈并把 A[2] 入栈；
如果 A[2] <= A[1]，我们就仅把 A[2] 入栈。对于第三个元素 A[3]，此时栈中有若干个元素，
那么所有比 A[3] 小的元素都找到了下一个更大元素（即 A[3]），因此可以出栈，
在这之后，我们将 A[3] 入栈，以此类推。

可以发现，我们维护了一个单调栈，栈中的元素从栈顶到栈底是单调不降的。
当我们遇到一个新的元素 A[i] 时，我们判断栈顶元素是否小于 A[i]，如果是，
那么栈顶元素的下一个更大元素即为 A[i]，我们将栈顶元素出栈。重复这一操作，
直到栈为空或者栈顶元素大于 A[i]。此时我们将 A[i] 入栈，保持栈的单调性，
并对接下来的 A[i + 1], A[i + 2] ... 执行同样的操作。

由于这道题的数组是循环数组，因此我们需要将每个元素都入栈两次。
这样可能会有元素出栈找过一次，即得到了超过一个“下一个更大元素”，
我们只需要保留第一个出栈的结果即可。

 */
    public int[] nextGreaterElements(int[] nums,int q) {

        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty()&&nums[i]>nums[stack.peek()]){
                res[stack.pop()]=nums[i];
            }
            stack.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty()&&nums[i]>nums[stack.peek()]){
                res[stack.pop()]=nums[i];
            }
        }
        while (!stack.isEmpty()){
            res[stack.pop()]=-1;
        }
        return res;
    }


    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i]==nums2[j]){
                    res[i]=-1;
                    for (int k = j+1; k < nums2.length; k++) {
                        if (nums2[k]>nums1[i]){
                            res[i]=nums2[k];
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
    /*
    方法一：单调栈
我们可以忽略数组 nums1，先对将 nums2 中的每一个元素，
求出其下一个更大的元素。随后对于将这些答案放入哈希映射（HashMap）中，
再遍历数组 nums1，并直接找出答案。对于 nums2，我们可以使用单调栈来解决这个问题。
我们首先把第一个元素 nums2[1] 放入栈，随后对于第二个元素 nums2[2]，
如果 nums2[2] > nums2[1]，那么我们就找到了 nums2[1] 的下一个更大元素 nums2[2]，
此时就可以把 nums2[1] 出栈并把 nums2[2] 入栈；如果 nums2[2] <= nums2[1]，
我们就仅把 nums2[2] 入栈。对于第三个元素 nums2[3]，此时栈中有若干个元素，那么所有比 nums2[3]
小的元素都找到了下一个更大元素（即 nums2[3]），因此可以出栈，在这之后，我们将 nums2[3] 入栈，以此类推。
可以发现，我们维护了一个单调栈，栈中的元素从栈顶到栈底是单调不降的。
当我们遇到一个新的元素 nums2[i] 时，我们判断栈顶元素是否小于 nums2[i]，
如果是，那么栈顶元素的下一个更大元素即为 nums2[i]，我们将栈顶元素出栈。重复这一操作，
直到栈为空或者栈顶元素大于 nums2[i]。此时我们将 nums2[i] 入栈，保持栈的单调性，
并对接下来的 nums2[i + 1], nums2[i + 2] ... 执行同样的操作。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2,int q) {
        //利用单调栈来解决此问题
        Stack<Integer> stack = new Stack<>();
        //stack.isEmpty();
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        int[] res = new int[nums1.length];
        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek()){
                //表明栈中存在元素 并且此时的值大于栈中元素 即num2[i]为下一个最大值；
                map.put(stack.pop(),nums2[i]);
            }
            stack.add(nums2[i]);
        }
        while(!stack.isEmpty()){
            map.put(stack.pop(),-1);//此时栈中的数都为找不到下一个最大节点 赋值为-1
        }
        for (int i = 0; i < nums1.length; i++) {
            res[i]=map.get(nums1[i]);
        }
        return res;
    }
}
