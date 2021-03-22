package mathOperationKills;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class BitOperation {
    /*code448找到所有数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
找到所有在 [1, n] 范围之间没有出现在数组中的数字。
您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
     */
    //对于异或运算（^），我们知道它有一个特殊性质：一个数和它本身做异或运算结果为 0，一个数和 0 做异或运算还是它本身。
    //而且异或运算满足交换律和结合律，也就是说：
    //2 ^ 3 ^ 2 = 3 ^ (2 ^ 2) = 3 ^ 0 = 3

    //让每个索引减去其对应的元素，再把相减的结果加起来，不就是那个缺失的元素
    public List<Integer> findDisappearedNumbers(int[] nums) {
//        int n = nums.length;
//        int res = 0;
//        // 新补的索引
//        res += n - 0;
//        // 剩下索引和元素的差加起来
//        for (int i = 0; i < n; i++)
//            res += i - nums[i];
//        return res;

        // Hash table for keeping track of the numbers in the array
        // Note that we can also use a set here since we are not
        // really concerned with the frequency of numbers.
        HashMap<Integer, Boolean> hashTable = new HashMap<Integer, Boolean>();

        // Add each of the numbers to the hash table
        for (int i = 0; i < nums.length; i++) {
            hashTable.put(nums[i], true);
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that don't appear in the hash table.
        for (int i = 1; i <= nums.length; i++) {
            if (!hashTable.containsKey(i)) {
                result.add(i);
            }
        }

        return result;
    }
    //空间复杂度：O(1)，因为我们在原地修改数组，没有使用额外的空间。
    public List<Integer> findDisappearedNumbersForNspace(int[] nums) {

        // Iterate over each of the elements in the original array
        for (int i = 0; i < nums.length; i++) {

            // Treat the value as the new index
            int newIndex = Math.abs(nums[i]) - 1;

            // Check the magnitude of value at this new index
            // If the magnitude is positive, make it negative
            // thus indicating that the number nums[i] has
            // appeared or has been visited.
            if (nums[newIndex] > 0) {
                nums[newIndex] *= -1;
            }
        }

        // Response array that would contain the missing numbers
        List<Integer> result = new LinkedList<Integer>();

        // Iterate over the numbers from 1 to N and add all those
        // that have positive magnitude in the array
        for (int i = 1; i <= nums.length; i++) {

            if (nums[i - 1] > 0) {
                result.add(i);
            }
        }

        return result;
    }

    /*
编写一个函数，输入是一个无符号整数（以二进制串的形式），
返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。

请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，
输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，
因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
在 Java 中，编译器使用二进制补码记法来表示有符号整数。
因此，在上面的 示例 3 中，输入表示有符号整数 -3。
     */
    /*
    与运算符用符号“&”表示，其使用规律如下：
    两个操作数中位都为1，结果才为1，否则结果为0
     */
    /*
    或运算符用符号“|”表示，其运算规律如下：
    两个位只要有一个为1，那么结果就是1，否则就为0
     */
    /*
    非运算符用符号“~”表示，其运算规律如下：
    如果位为0，结果是1，如果位为1，结果是0，
     */
    /*
    异或运算符是用符号“^”表示的，其运算规律是：
    两个操作数的位中，相同则结果为0，不同则结果为1。
     */
    public int hammingWeight(int n) {
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0) {
                bits++;
            }
            mask <<= 1;
            //左移运算符，将运算符左边的对象向左移动运算符右边指定的位数（在低位补0）
        }
        return bits;
    }
    /*
    在二进制表示中，数字 n 中最低位的 1 总是对应 n−1 中的 0 .
    因此，将 n 和 n−1 与运算总是能把 n 中最低位的 1 变成 0 ，并保持其他位不变。
     */
    public int hammingWeight1(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
    /* 231.2的次幂
        给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
    若 n = 2^x且 x 为自然数（即 n 为 2 的幂），则一定满足以下条件：
    1.恒有 n & (n - 1) == 0，这是因为：
    n二进制最高位为 1，其余所有位为 0；
    n−1 二进制最高位为 0，其余所有位为 1；
    2.一定满足 n > 0。
    因此，通过 n > 0 且 n & (n - 1) == 0 即可判定是否满足 n = 2^x
     */
    class Solution {
        public boolean isPowerOfTwo(int n) {
            return n > 0 && (n & (n - 1)) == 0;
        }
    }
    /*
    给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

 一个数和它本身做异或运算结果为 0，即 a ^ a = 0；一个数和 0 做异或运算的结果为它本身，即 a ^ 0 = a。
对于这道题目，我们只要把所有数字进行异或，成对儿的数字就会变成 0
，落单的数字和 0 做异或还是它本身，所以最后异或的结果就是只出现一次的元素：
     */

    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    //位操作计算
    public static void main(String[] args) {
        /*利用或操作 | 和空格将英文字符转换为小写
            ('a' | ' ') = 'a'
            ('A' | ' ') = 'a'
    利用与操作 & 和下划线将英文字符转换为大写
            ('b' & '_') = 'B'
            ('B' & '_') = 'B'
    利用异或操作 ^ 和空格进行英文字符大小写互换
            ('d' ^ ' ') = 'D'
            ('D' ^ ' ') = 'd'
     */
        int[] aa = {5,4,3,2,1,2,1,2,2,5,4,8,8,9,9,10,5,6,6,5,10};
        BitOperation bitOperation = new BitOperation();
        bitOperation.singleNumber(aa);
        char a = 'a' | ' ';
        char b ='A' | ' ';
        char c ='b' & '_';
        char d ='B' & '_';
        char e ='d' ^ ' ';
        char f ='D' ^ ' ';
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        /*
        判断两个数是否异号

        int x = -1, y = 2;
        bool f = ((x ^ y) < 0); // true

        int x = 3, y = 2;
        bool f = ((x ^ y) < 0); // false
        */
        
    }
}
