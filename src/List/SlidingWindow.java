package List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
利用滑动窗口解决问题
 */
public class SlidingWindow {
    /*
    code.76
    给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
    如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

    注意：如果 s 中存在这样的子串，我们保证它是唯一的答案。
     */
    public String minWindow(String s, String t) {
        if (s == null || s == "" || t == null || t == "" || s.length() < t.length()) {
            return "";
        }
        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];

        for (int i = 0; i < t.length(); i++) {
            needs[t.charAt(i)]++;
        }

        int left = 0;
        int right = 0;

        //String res = "";
        int start=0;//开始的索引位置
        //用来记录最短需要多少个字符。
        int minLength = s.length() + 1;//最小包含的字符长度

        //目前有多少个字符 need中需要包含的字符与t中的大小相同
        int count = 0;
        //滑动窗口开始
        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            window[ch]++;
            //判断need数组是否需要当前字符串ch 并且窗口window中还需要当前字符ch
            if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                count++;
            }

            //移动到不满足条件为止
            //使用count 与t.length 来判断是否满足要求
            while (count == t.length()) {
                ch = s.charAt(left);
                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                if (right - left< minLength) {
                    //更新最小长度
                    minLength = right - left;
                    //更新开始的位置
                    start = left;
                    //res = s.substring(left, right + 1);

                }
                window[ch]--;
                left++;

            }


        }
        return minLength==s.length() + 1 ? "":s.substring(start,start+minLength);
    }
    /* Code.567字符串的排列
    给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。

    换句话说，第一个字符串的排列之一是第二个字符串的子串。

    输入: s1 = "ab" s2 = "eidbaooo"
    输出: True
    解释: s2 包含 s1 的排列之一 ("ba").
    输入: s1= "ab" s2 = "eidboaoo"
    输出: False
     */
    public boolean checkInclusion(String s1, String s2) {
        //s1属于s2

        //用来统计t中每个字符出现次数
        int[] needs = new int[128];
        //用来统计滑动窗口中每个字符出现次数
        int[] window = new int[128];

        for (int i = 0; i < s1.length(); i++) {

            needs[s1.charAt(i)]++;
        }
        int left = 0;
        int right = 0;

        int count=0;
        //int size=0;
        //整个窗口右移
        while (right<s2.length()){
            char c = s2.charAt(right);
            if (needs[c]>0&&window[c]<needs[c]){
                count++;
            }
            window[c]++;
            right++;
            //将左边的窗口指针右移 从局部解中达到最优解
            while (count>=s1.length()){

                //当count==s1.length()时  此时则为最优解
                if (right-left==s1.length()){
                    return true;
                            //s1.substring(left,left+count);
                }
                char ch = s2.charAt(left);

                if (needs[ch] > 0 && needs[ch] >= window[ch]) {
                    count--;
                }
                //*********
                //if ()

                window[ch]--;
                left++;
            }

        }
        return false;
    }

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        String s1="cbaebabacd";

        String s2="abc";
        List<Integer> list=slidingWindow.findAnagrams(s1,s2);
        System.out.println(list.toString());

    }
    /* Code.438. 找到字符串中所有字母异位词
    给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
    字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
说明：
    字母异位词指字母相同，但排列不同的字符串。
    不考虑答案输出的顺序。
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] need = new int[128];
        for (char c:p.toCharArray()){
            need[c]++;
        }
        int[] window = new int[128];


        int left=0;
        int right=0;
        int count=0;
        List<Integer> list = new ArrayList<>();

        while (right<s.length()){
            //滑动窗口
            char c = s.charAt(right);
            //当need中需要字符c并且窗口中含有的字符c的个数小于need中需要c字符的个数
            if (need[c]>0&&window[c]<need[c]){
                count++;

            }
            window[c]++;
            right++;
            //窗口左边界left 向右边移动
            while (count>=p.length()){
                if (right-left==p.length()){
                    list.add(left);
                }
                char ch = s.charAt(left);
                if (need[ch]>0&&need[ch]>=window[ch]){
                    count--;

                }
                window[ch]--;
                left++;
            }
        }
        return list;
    }
    /*
    给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    public int lengthOfLongestSubstring(String s) {
        int[] window = new int[128];
        int maxlength =0;
        int left=0;
        int right=0;
        while (right<s.length()){
            char c = s.charAt(right);
            window[c]++;
            right++;
            while (window[c]>1){
                char ch = s.charAt(left);
                window[ch]--;
                left++;
            }
            maxlength=Math.max(maxlength,right-left);
        }
        return maxlength;
    }
}