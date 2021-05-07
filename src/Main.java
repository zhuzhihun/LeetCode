import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
//    public int[] QuickSort(int nums[]){
//        for (int i = 0; i < nums.length; i++) {
//            //int key=nums[i];
//            int min=i;
//
//            for (int j = i+1; j < nums.length; j++) {
//                if (nums[j]<nums[min]){
//                    min=j;
//                }
//            }
//            int temp = nums[min];
//            nums[min]=nums[i];
//            nums[i]=temp;
//        }
//
//    }
    public int[] helper(int[] nums, int left,int right,int k){
        if (right>=nums.length){
            int[] res = {-1,-1};
            return res;
        }
        if (left<=right){
            int mid = (left+right)/2;
            //if (nums)
            if (nums[mid]<k){//差右边
                return helper(nums,mid+1,right,k);

            }else if (nums[mid]>k){//查左边
                return helper(nums,left,mid-1,k);
            }
            else{
                //这个数就是k
                int start =mid;
                int i=mid;
                int end=mid;
                while (start>=0&&nums[start]==k){
                    start--;
                }
                start++;
                while (end<nums.length&&nums[end]==k){
                    end++;
                }
                end--;
                //返回 start end
                int []res = {start,end};
                return res;
            }
        }
        int[] res = {-1,-1};
        return res;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] str = sc.nextLine().split(" ");
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i]=Integer.parseInt(str[i]);
        }
        int dp[] = new int[n+1];
        int max=0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                max=Math.max(max,helper(nums,i,j)) ;
            }
        }
        System.out.println(max);
    }

    private static int helper(int[] nums, int i, int j) {
        int res=0;
        for (int k = 0; k < nums.length; k++) {
            if (k>=i&&k<=j){
                if (nums[k]==0)
                    res++;
            }else{
                if (nums[k]==1)
                    res++;
            }


        }
        return res;
    }

    public static void main3(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int k = Integer.parseInt(sc.nextLine());
        int count=0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)=='1')
                count++;
        }
        for (int i = str.length()-1; i >=0 ; i--) {
            if (count<k){
                //k够
                if (str.charAt(i)=='1')
                    continue;

                if (str.charAt(i)=='0'){
                    StringBuilder stringBuilder = new StringBuilder(str);
                    stringBuilder.replace(i,i+1,"1");
                    System.out.println(stringBuilder.toString());
                    return;
                }

            }

        }

    }




    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String s = Integer.toBinaryString(n);
        System.out.println("============");
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toHexString(n));
        System.out.println(Integer.toOctalString(n));
        System.out.println(Integer.toUnsignedString(n));
        System.out.println("============");
        System.out.println(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='1'){
                System.out.print(i+1+" ");
            }
        }

    }
    public static void cellDuplication1(int n) {
        int count=0;
        List<Integer> list = new ArrayList<>();
        while (n>0){
            n=n>>1;
            if (n%2!=0){
                list.add(count);
            }
            count++;
        }
        System.out.println(count);
        System.out.print(1);
        System.out.print(" ");
        for (int i = 0; i <list.size() ; i++) {
            System.out.print(count-list.get(i));
            System.out.print(" ");
        }

    }
    public static void cellDuplication(int n) {
        int res=1;
        int count = 1;
        while (res<n) {
            res*=2;
            count++;
        }
        if (n==res){
            System.out.println(count);
            System.out.print(1);
            //System.out.print(" ");
            return;
        }
        System.out.println(count-1);
        System.out.print(1);
        System.out.print(" ");
        //上面是刚好用完
        n=n-res/2;
        while (n>0){
            int r=1;
            int c = 1;
            while (r<n) {
                r*=2;
                c++;
            }
            if (n==r){
                System.out.print(count-c);
                //System.out.print(" ");
                return;
            }
            //System.out.println(count);
            System.out.print(count-c);
            System.out.print(" ");
            n=n-res/2;
        }
    }
    public static String add(String a,String b){
        StringBuilder sb=new StringBuilder();
        int x=0;
        int y=0;
        int pre=0;//进位
        int sum=0;//存储进位和另两个位的和

        while(a.length()!=b.length()){//将两个二进制的数位数补齐,在短的前面添0
            if(a.length()>b.length()){
                b="0"+b;
            }else{
                a="0"+a;
            }
        }
        for(int i=a.length()-1;i>=0;i--){
            x=a.charAt(i)-'0';
            y=b.charAt(i)-'0';
            sum=x+y+pre;//从低位做加法
            if(sum>=2){
                pre=1;//进位
                sb.append(sum-2);
            }else{
                 pre=0;
                sb.append(sum);
            }
        }
        if(pre==1){

            sb.append("1");
        }
        return sb.reverse().toString();//翻转返回
    }
}





