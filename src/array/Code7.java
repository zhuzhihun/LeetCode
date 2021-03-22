package array;

public class Code7 {
    public static int reverse(int x){
        int ans = 0;
        while (x != 0) {
//            if ((ans * 10) / 10 != ans) {//判断溢出
//                ans = 0;
//                break;
//            }
            ans = ans * 10 + x % 10;

            x = x / 10;
            System.out.println("ans:"+ans+",x:"+x);
        }
        return ans;
    }

    public static void main(String[] args) {
        int a=-13;
        a=reverse(a);
        System.out.print(a);
    }
}
