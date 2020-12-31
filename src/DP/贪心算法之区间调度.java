package DP;

import java.util.Arrays;
import java.util.Comparator;

public class 贪心算法之区间调度 {

    //给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
    //
    //注意:
    //
    //可以认为区间的终点总是大于它的起点。
    //区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
    //

    public int eraseOverlapIntervals(int[][] intervals){
        int m = intervals.length;
        if (m<2) return 0;
        //对intervals进行排序  按照区间的end节点 从小到大排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];//当返回1时，表明前者大于后者  交换位置
            }
        });
        //排完序之后 第一个便是确定的
        int count=1;//表示选择的第一个区间
        int end = intervals[0][1];
        for (int i = 1; i < m; i++) {
            if (intervals[i][0]>=end){//表明此时的区间从一开始就符合前面区间的终点
                end = intervals[i][1];
                count++;//代表找到一个区间 此区间为合适的不重跌区间
            }
        }
        return m-count;
    }
//在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
//
//一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
//
//给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
//
    public int findMinArrowShots(int[][] points){
        int m = points.length;
        if (m<2) return 0;
        //对intervals进行排序  按照区间的end节点 从小到大排序
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]>o2[1]?1:-1;//当返回1时，表明前者大于后者  交换位置
                //直接使用o1[1]-o2[1]时,测试用例[[-2147483646,-2147483645],[2147483646,2147483647]]过不了。
            }
        });
        //排完序之后 第一个便是确定的
        int count=1;//表示选择的第一个区间
        float end = points[0][1];
        for (int i = 1; i < m; i++) {
            if ((float)(points[i][0])>end){//表明此时的区间从一开始就符合前面区间的终点
                end = points[i][1];
                count++;//代表找到一个区间 此区间为合适的不重跌区间
            }
        }
        return m;
    }
    //思想跳跃游戏，
    //给定一个非负整数数组，你最初位于数组的第一个位置。
    //数组中的每个元素代表你在该位置可以跳跃的最大长度。
    //判断你是否能够到达最后一个位置。
    public boolean canJump(int[] nums) {
        int n = nums.length;
        int far = 0;
        for (int i = 0; i < n; i++) {
            far = Math.max(far,i+nums[i]);
            if (far<=i){
                return false;
            }
        }

        return far>=n;
    }
    public int jump(int[] nums) {
        int n = nums.length;
        int far=0;//表示现在可以跳到最远的位置
        int end=0;//表示跳一步可以到的最后的位置
        int step =0;
        for (int i = 0; i < n-1; i++) {
            far = Math.max(far,i+nums[i]);
            if (i==end){
                end = far;
                step++;
            }
        }
        return step;
    }
    //给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
    //
    //'.' 匹配任意单个字符
    //'*' 匹配零个或多个前面的那一个元素
    //所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
    //
    //10. 正则表达式匹配

    public boolean oisMatch(String s, String p) {
        //当没有*时;
        int i=0,j=0;
        while (i<s.length()&&j<p.length()){
            if (s.charAt(i)==p.charAt(j)|| p.charAt(j)=='.'){
                i++;j++;
            }else{
                return false;
            }
        }
        return i==j;//是否都到了最后
    }
    public boolean isMatch(String s, String p) {
        //当有*时;
        int i=0,j=0;
        while (i<s.length()&&j<p.length()){
            if (s.charAt(i)==p.charAt(j)|| p.charAt(j)=='.'){
                i++;j++;
            }else{
                return false;
            }
        }
        return i==j;//是否都到了最后
    }
    public static void main(String[] args) {

    }
}
