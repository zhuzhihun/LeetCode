package algorithmTest;

import java.util.*;

public class MyClass {
    long var;



    public void MyClass(long par){
        var=par;
    }

    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String s[]=scanner.nextLine().split(" ");
        int []nums = new int[s.length];
        for (int i = 0; i < n; i++) {
            nums[i]=Integer.parseInt(s[i]);
        }
        int dp[][] = new int[s.length+2][2];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i],0);
        }

        for (int i = 2; i < dp.length; i++) {
            dp[i][0]=dp[i-1][1];//休息
            dp[i][1]=nums[i-2]+Math.max(dp[i-1][0],dp[i-2][0]);
        }
        System.out.println(Math.max(dp[dp.length-1][0],dp[dp.length-1][1]));
    }
    //判断一个数是否是质数（素数）
    public static boolean isPrimeNumber(int num) {
        if (num == 2) return true;//2特殊处理
        if (num < 2 || num % 2 == 0) return false;//识别小于2的数和偶数
        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {//识别被奇数整除
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());
        //aaabc
        //9
        int max=0;
        int min=Integer.MAX_VALUE;
        Map<Character,Integer> map =new HashMap<>();
        for (int i = 0; i < n/s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if(map.containsKey(s.charAt(j))){
                    map.put(s.charAt(j),map.get(s.charAt(j))+1);
                }else {
                    map.put(s.charAt(j),1);
                }
            }
        }

        for (int j = 0; j < n%s.length(); j++) {
            if(map.containsKey(s.charAt(j))){
                map.put(s.charAt(j),map.get(s.charAt(j))+1);
            }else {
                map.put(s.charAt(j),1);
            }
        }
        Iterator<Map.Entry<Character,Integer>> it=map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Character,Integer> entry=it.next();
            max=Math.max(max,entry.getValue());
            min=Math.min(min,entry.getValue());
        }
        int res = max-min;
        if (isPrimeNumber(res)) {
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }
        System.out.println(res);

    }


}
