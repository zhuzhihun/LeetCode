package Array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/*
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class Code3 {
    public static int lengthOfLongestSubstring(String s){
        if (s.length()==0) return 0;
        HashMap<Character,Integer> map = new HashMap<Character,Integer>();
        int max=0;
        int left =0;//用来记录最左边的（开始）字符的位置
        for (int i = 0; i < s.length(); i++) {
            System.out.println("1.第"+i+"个字符");
            if (map.containsKey(s.charAt(i))){
                System.out.println("2.1第"+i+"个字符已经包含在map中,为"+s.charAt(i));
                //滑动窗口中已经包含改“字符”
                left = Math.max(left,map.get(s.charAt(i))+1);//获取已有字符的位置
                System.out.println("更新left，其值为："+left);
                //获取
            }
            map.put(s.charAt(i),i);//放入字符s.charAt(i),对应下标为i
            System.out.println("2.2放入第"+i+"个字符，为"+s.charAt(i)+"下标为"+i);
            max = Math.max(max,i-left+1);
            int o = i-left+1;
            System.out.println("3.比较max与i-left+1大小.max:"+max+"\ti-left+1为:"+o);
            System.out.println("4.map中字符为："+map.toString());
            System.out.println();
        }
        return max;

    }
    public static void main(String[] args) {
        String a = "uhnnhuj";
        int c = lengthOfLongestSubstring(a);
        System.out.println(c);
    }
}
