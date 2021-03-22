package list;
/*
左右指针在数组中实际是指两个索引值，
一般初始化为 left = 0, right = nums.length - 1
 */
public class LeftAndRightPoints {
    /*  Code.167
    给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。

说明:
返回的下标值（index1 和 index2）不是从零开始的。
你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
     */
    public int[] twoSum(int[] numbers, int target) {
        int left=0;
        int right = numbers.length;
        while (left<right){
            if (target==numbers[left]+numbers[right]){
                return new int[]{left+1,right+1};
            }
            else if(target>numbers[left]+numbers[right]){
                left++;
            }else if(target<numbers[left]+numbers[right]){
                right--;
            }
        }
        return null;
    }
    /*
    //344.
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。

你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
     */
    public void reverseString(char[] s) {
        int left=0;
        int right = s.length-1;
        while (left<right){
            char a = s[left];
            s[left]=s[right];
            s[right]=a;
            left++;
            right--;
        }
    }
}
