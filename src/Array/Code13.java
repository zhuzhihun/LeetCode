package Array;

public class Code13 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {//判断是否为空
            return "";
        }
        String prefix = strs[0];//取第一组字符串为公共前缀
        int count = strs.length;//count表示 存在几个数组
        for (int i = 1; i < count; i++) {//分别与第二个数组开始比较  一直到count个数组
            prefix = longestCommonPrefix(prefix, strs[i],prefix);//利用longestCommonPrefix（）函数获取前缀
            if (prefix.length() == 0) {//当前缀为0时  提前结束循环；
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2,String prefix) {
        int length = Math.min(str1.length(), str2.length());//以两个数组其中短的为 前缀个数
        length= Math.min(length,prefix.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
    public String second_longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int length = strs[0].length();
        int count = strs.length;
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < count; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
    }
}
