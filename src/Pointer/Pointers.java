package Pointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pointers {
    /*给定两个数组，编写一个函数来计算它们的交集。
    示例 1：
    输入：n u m s1 = [ 1 , 2 , 2 , 1 ] , n u m s2 = [ 2 , 2 ]
    输出：[ 2 , 2 ]
    示例 2:
    输入：n u m s1 = [ 4 , 9 , 5 ] , n u m s2 = [ 9 , 4 , 9 , 8 , 4 ]
    输出：[ 4 , 9 ]
    */
    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i=0,j=0;
        while (i<nums1.length&&j<nums2.length){
            if (nums1[i]<nums2[j]){
                i++;
            }else if (nums1[i]>nums2[j]){
                j++;
            }else{
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        //int[] objects = (int)list.toArray();
        int res[] = new int[list.size()];
        for (int k = 0; k < res.length; k++) {
            res[k]=list.get(k);
        }
        return res;
    }

    public static void main(String[] args) {
        int nums1[] = { 4 , 9 , 5 } ;int nums2[] = { 9 , 4 , 9 , 8 , 4 };
        int[] res =intersect(nums1,nums2);
        for (int a:res) {
            System.out.print(a+"   ");
        }
    }
}
