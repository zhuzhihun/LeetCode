package array;

import java.util.LinkedList;

public class RemoveDuplicates {
/*
给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
注意:
num 的长度小于 10002 且 ≥ k。
num 不会包含任何前导零。即第一个不为0

 */
    public String removeKdigits(String num, int k) {
        if (num.isEmpty()||num.length()==k){
            return "0";
        }

        //当0前面的数小于等于k时，才将前面的数字去掉
        LinkedList<Character> stack = new LinkedList<>();
        stack.add(num.charAt(0));
        for (int i = 1; i < num.length(); i++) {
            while(!stack.isEmpty()&&stack.peekFirst()>num.charAt(i)&&k>0){
                //当栈里面取出来的元素大于当前的元素
                stack.pollFirst();
                //取出栈顶元素
                k--;
            }
            stack.addFirst(num.charAt(i));
        }
        StringBuffer stringBuffer = new StringBuffer();
        int size = stack.size();
        for (int i = 0; i < size-k; i++) {
            if (stringBuffer.length()==0&&stack.peekLast()=='0'){
                stack.removeLast();
            }else{
                stringBuffer.append(stack.removeLast());
            }

        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String  s ="bcabc";
        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        removeDuplicates.removeDuplicateLetters(s);

    }
    /*code
     316 or 1081
    给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。
    需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     */
    public String removeDuplicateLetters(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        //用来判断此字符后 是否还有当前字符;
        int[] count = new int[26];
        //用来表明栈中是否有 该元素
        boolean[] exists = new boolean[26];
        //初始化
        for (char ch:s.toCharArray()){
            count[ch-'a']++;
        }
        //遍历s并入栈
        for (int i = 0; i < s.length(); i++) {
            //获取当前的字符 temp
            char temp = s.charAt(i);
            //判断栈中是否有当前字符 temp
            if (!exists[temp-'a']){
                //栈中此时不存在当前字符   并且判断前面的字符 字典序号是否小于当前字符
                //当栈中不为空  并且取出的栈顶元素 的字典序号 大于 当前字符的 字典序号  并且后面还有取出的字符
                while (!stack.isEmpty()&&stack.getLast()>temp&&count[stack.getLast()-'a']>0){
                    //将栈顶的元素取出
                    exists[stack.getLast()-'a']=false;
                    stack.removeLast();
                }
                //加入栈 并将exists质为true
                stack.add(temp);
                exists[temp-'a']=true;

            }//栈中存在当前字符  跳过
            count[temp-'a']--;
        }
        //将栈中元素进行输出
        Object[] chars =stack.toArray();
        StringBuffer ss = new StringBuffer();
        while (!stack.isEmpty()){
            ss.append(String.valueOf(stack.remove()));
        }
        return ss.toString();

    }
    /* code.26
    给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
    不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
    由于数组已经排序，所以重复的元素一定连在一起
     */

    public int removeDuplicates(int[] nums) {
        //利用快慢指针解决排序数组 原地删除重复元素问题
        if (nums.length<2){
            return nums.length;
        }
        int left=0;int right =1;
        while (right<nums.length){
            if (nums[right]!=nums[left]){
                left++;
                nums[left]=nums[right];


            }
            right++;
        }
        return left+1;
    }
    /* code 27
    给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。

    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。

    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

     */
    public int removeElement(int[] nums, int val) {
        int left=0;int right =0;
        while (right<nums.length){
            if(nums[right]==val){
                right++;
                //continue;
            }else{
                nums[left]=nums[right];
                left++;right++;
            }

        }
        return left;
    }
    /* code.83
    给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode fast,slow;
        fast=slow=head;
        while (fast!=null){
            //因为是排好序的 因此可以直接比较
            if (fast.val!=slow.val){
                slow=slow.next;
                slow.val=fast.val;
            }
            fast=fast.next;
        }
        slow.next=null;
        return head;
    }
    /*
    给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
    输入: [0,1,0,3,12]
    输出: [1,3,12,0,0]
     */
    public void moveZeroes(int[] nums) {
        int n = nums.length;
        int p = removeElement(nums,0);
        for (;p<n;p++){
            nums[p]=0;
        }

    }

}
