package MathOperationKills;

public class BitOperation {
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
