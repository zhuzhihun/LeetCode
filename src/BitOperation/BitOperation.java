package BitOperation;

public class BitOperation {
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
