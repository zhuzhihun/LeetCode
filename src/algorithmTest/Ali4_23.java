package algorithmTest;

import java.util.Map;
import java.util.Scanner;

public class Ali4_23 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] strings = sc.nextLine().split(" ");
        int n = Integer.parseInt(strings[0]);
        int m = Integer.parseInt(strings[1]);
        int q = Integer.parseInt(strings[2]);
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            int a[] = new int[m];
            String[] str = sc.nextLine().split(" ");
            for (int j = 0; j < m; j++) {
                a[j]=Integer.parseInt(str[j]);
            }
            matrix[i]=a;
        }
        int nums[][]=new int[q][2];
        for (int i = 0; i < q; i++) {
            String[] strs = sc.nextLine().split(" ");
            nums[i][0]=Integer.parseInt(strs[0]);
            nums[i][1]=Integer.parseInt(strs[1]);
        }
        int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            res[i]=helper(matrix[i]);
            //System.out.println(res[i]);
        }
        for (int i = 0; i < q; i++) {
            int max = 0;
            matrix[nums[i][0]-1][nums[i][1]-1]=
                    matrix[nums[i][0]-1][nums[i][1]-1]==0?1:0;
            //将 位置在 0 与 1 之间进行切换
            res[nums[i][0]-1]=helper(matrix[nums[i][0]-1]);
            for (int j = 0; j < res.length; j++) {
                max=Math.max(max,res[j]);
            }
            System.out.println(max);
        }
    }

    private static int helper(int[] matrix) {
        int []dp =new int[matrix.length+1];
        int max=0;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] == 1) {
                dp[i+1]=dp[i]+1;
                max = Math.max(max,dp[i+1]);
            }
        }
        return max;
    }
}

