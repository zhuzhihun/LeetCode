package Others;

import org.omg.CORBA.MARSHAL;

import java.util.*;

public class BookBoss {
    /*
    今 天 ， 书 店 老 板 有 一 家 店 打 算 试 营 业 cus tomer s . length 分 钟 。 每 分 钟 都 有 一 些 顾 客
    （cus tomer s [i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
    在某些时候，书店老板会生气。如果书店老板在第i分钟生气，那么 grumpy [i]=1，否
    则grumpy [i]=0。当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满
    意的。
    书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续X分钟不生气，但却只
    能使用一次。
    请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
    示例：
    输入：cu s t o m e r s=[ 1 , 0 , 1 , 2 , 1 , 1 , 7 , 5 ] ,
    g ru m p y=[ 0 , 1 , 0 , 1 , 0 , 1 , 0 , 1 ] ,X=3
    输出：1 6
    解释：
    书店老板在最后3分钟保持冷静。
    感到满意的最大客户数量=1+1+1+1+7+5=1 6
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int sum=0;
//        int[] newarr = new int[customers.length];
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i]==0){
                //不生气
                sum+=customers[i];
                customers[i]=0;
            }

        }
        int left = 0;
        int right = 0;
        int res = 0;
        int max = 0;
        for (; right <customers.length ; right++) {
            res+=customers[right];
            //如果长度超过k
            if (right-left>X){
                sum-=customers[left++];
            }
            max=Math.max(max,res);
        }
        return max+sum;
    }

    /*
    给定一个由若干0和1组成的数组A，我们最多可以将K个值从0变成1。
    返回仅包含1的最长（连续）子数组的长度。
    示例 1：
    输入：
    A=[ 1 , 1 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 , 0 ] ,K=2
    输出：6
    解释：
    [ 1 , 1 , 1 , 0 , 0 , 1 , 1 , 1 , 1 , 1 , 1 ]
    粗体数字从0翻转到1，最长的子数组长度为6。
     */
    public static void main(String[] args) {
        int[] a = { 0 , 0 , 1 , 1 , 0 , 0 , 1 , 1 , 1 , 0 , 1 , 1 , 0 , 0 , 0 , 1 , 1 , 1 , 1 };
        System.out.println(longestOnes(a, 3));
        int []max={ 1 , 0 , 1 , 2 , 1 , 1 , 7 , 5 };
        int []asd={0 , 1 , 0 , 1 , 0 , 1 , 0 , 1};
        System.out.println(maxSatisfied(max, asd, 3));
    }
    public static int longestOnes(int[] A, int K) {
        int left = 0;//左窗口位置
        int zeroCount = 0;//0的个数
        int maxWindow = 0;//窗口的最大值
        for (int right = 0; right < A.length; right++) {
            if (A[right]==1){
                maxWindow=Math.max(maxWindow,right-left+1);
                continue;
            }
            zeroCount++;
            while (zeroCount>K){
                if (A[left]==0)
                    zeroCount--;
                left++;
            }
        }
        return maxWindow;
    }
    /*
    给 你 一 个 mxn 的 矩 阵 matri x 。 如 果 这 个 矩 阵 是 托 普 利 茨 矩 阵 ， 返 回 true； 否 则 ， 返 回
    fal se 。
    如果矩阵上每一条由左上到右下的对角线上的元素都相同， 那 么 这 个 矩 阵 是 托 普 利 茨 矩
    阵。
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length-1; i++) {
            for (int j = 0; j < matrix[0].length-1; j++) {
                if (matrix[i][j]!=matrix[i+1][j+1])
                    return false;
            }
        }
        return true;
    }
    /*
    给你一个整数数组arr，请你帮忙统计数组中每个数的出现次数。
    如果每个数的出现次数都是独一无二的，就返回 true；否则返回 fal se。
    示例 1：
    输入：a rr = [ 1 , 2 , 2 , 1 , 1 , 3 ]
    输出：tru e
    解释：在该数组中，1 出现了 3 次，2 出现了 2 次，3 只出现了 1 次。没有两个数的
    出现次数相同。
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        Set<Integer> set = new HashSet<>(map.values());
        return map.size()==set.size();
    }
    /*
    在未排序的数组中找到第k个最大的元素。请注意，你需要找的是数组排序后的第k个最
    大的元素，而不是第k个不同的元素。
    输入: [ 3 , 2 , 1 , 5 , 6 , 4 ] 和 k = 2
    输出: 5
     */
    public int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int val : nums) {
            queue.add(val);//加入堆中
            //如果堆中元素大于k，则把堆顶元素给移除
            if (queue.size() > k)
                queue.poll();
            }
        return queue.peek();//返回堆顶元素
    }
    /*
    给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
    注意:
            1. 可以认为区间的终点总是大于它的起点。
            2. 区间 [ 1 , 2 ] 和 [ 2 , 3 ] 的边界相互“接触”，但没有相互重叠。
    示例 1:
    输入: [ [ 1 , 2 ] , [ 2 , 3 ] , [ 3 , 4 ] , [ 1 , 3 ] ]
    输出: 1
    解释: 移除 [ 1 , 3 ] 后，剩下的区间没有重叠。
    */
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0)
            return 0;
        //先排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        //记录区间尾部的位置
        int end = intervals[0][1];
        //需要移除的数量
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
              if (intervals[i][0] < end) {
                   //如果重叠了，必须要移除一个，所以count要加1，
                   //然后更新尾部的位置，我们取尾部比较小的
                   end = Math.min(end, intervals[i][1]);
                   count++;
                   } else {
                   //如果没有重叠，就不需要移除，只需要更新尾部的位置即可
                   end = intervals[i][1];
                   }
            }
        return count;
        }
    /*
    给定一个数组，将数组中的元素向右移动k 个位置，其中k是非负数。
     */
    public void rotate(int nums[], int k) {
        int length = nums.length;
        int temp[] = new int[length];
        // 把原数组值放到一个临时数组中，
        for (int i = 0; i < length; i++) {
            temp[i] = nums[i];
        }
        // 然后在把临时数组的值重新放到原数组，
        // 并且往右移动k位
         for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = temp[i];
            }
         }
    /*
    给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返
    回 -1。
    示例：
    s = "l e e t co d e "
    返回 0
    s = "l o ve l e e t co d e "
    返回 2
     */
    /*
    String包含一下两种方法
    一个是indexOf，表示的是从前面查找字符在字符串中第一次出现的位置。
    一个是lastIndexOf，从后面查找字符在字符串中第一次出现的位置。
     */
    public int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++)
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
            return i;
        return -1;
        }
    /*
    你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所
    有的房屋都围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
    房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自
    动报警 。
    给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下 ，
    能够偷窃到的最高金额。
    示例 1：
    输入：n u m s = [ 2 , 3 , 2 ]
    输出：3
    原创 山大王wld 数据结构和算法 昨天解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为
    他们是相邻的。
         */
    public int rob(int[] nums) {
        if (nums.length==1)
            return nums[0];
        //偷第一家，但不能偷最后一家
        int robFirst = robHelper(nums,0,nums.length-1);
        //偷最后一家 但不能偷第一家
        int robLast = robHelper(nums,1,nums.length);
        return Math.max(robFirst,robLast);
    }

    private int robHelper(int[] nums, int start, int end) {
        int steal = 0;
        int noSteal = 0;

        for (int j = start; j < end; j++) {
            int temp = steal;
            steal = noSteal+nums[j];
            noSteal = Math.max(noSteal,temp);
        }
        return Math.max(steal,noSteal);
    }
    /*
    给你一个字符串s，请你根据下面的算法重新构造字符串：
    1. 从s中选出最小的字符，将它接在结果字符串的后面。
    2. 从s剩余字符中选出最小的字符，且该字符比上一个添加的字符大，将它接在结果
    字符串后面。
    3. 重复步骤2，直到你没法从s中选择字符。
    4. 从s中选出最大的字符，将它接在结果字符串的后面。
    5. 从s剩余字符中选出最大的字符，且该字符比上一个添加的字符小，将它接在结果
    字符串后面。
    6. 重复步骤5，直到你没法从s中选择字符。
    7. 重复步骤1到6，直到s中所有字符都已经被选过。
    在任何一步中，如果最小或者最大字符不止一个，你可以选择其中任意一个，并将其添
    加到结果字符串。
    请你返回将s中字符重新排序后的结果字符串 。
    示例 1：
    输入：s = " a a a a b b b b c c c c "
    输出：" a b c cb a a b c cb a "
    解释：第一轮的步骤 1，2，3 后，结果字符串为 re su l t = " a b c "
    第一轮的步骤 4，5，6 后，结果字符串为 re su l t = " a b c cb a "
    第一轮结束，现在 s = " a a b b c c " ，我们再次回到步骤 1
    第二轮的步骤 1，2，3 后，结果字符串为 re su l t = " a b c cb a a b c "
    第二轮的步骤 4，5，6 后，结果字符串为 re su l t = " a b c cb a a b c cb a "
     */
    public String sortString(String s) {
        //存放26个字母
        int[] bucket = new int[26];
        char[] charArr = s.toCharArray();
        //把s中的字符分别放到对应的桶里
        for (char c:charArr){
            bucket[c-'a']++;
        }
        //存储计算的结果
        char res[] =new char[s.length()];
        int index=0;
        while (index<s.length()){
            //先从左往右找，遍历26个桶,如果当前桶不为空，
            //就从当前桶里拿出一个元素出来
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i]!=0){
                    res[index]=(char)(i+'a');
                    index++;
                    bucket[i]--;//拿出来后桶中元素的个数减-
                }
            }
            for (int i = 25; i >=0; i--) {
                if (bucket[i]!=0){
                    res[index]= (char) (i+'a');
                    index++;
                    bucket[i]--;
                }
            }
        }
        return new String(res);
    }

}
