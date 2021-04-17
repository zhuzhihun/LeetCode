package algorithmTest;

import java.util.Stack;

public class Test {
    public static int compress (int[] m) {
        // write code here
        int a[] = new int[m.length+1];
        a[0]=0;
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < m.length; i++) {
            a[i+1]=a[i]+m[i];
            if(a[i+1]>max){
                max=a[i+1];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int a[]= {1,2,-1,1,-1};
        System.out.println(compress(a));
    }
}
