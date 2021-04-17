package DP;

public class Code42 {
    //接雨水问题  永远的痛
    public int trap(int[] height){
        int length = height.length;
        int sum=0;
        if(length<3){
            return sum;
        }
        for(int i=1;i<length-1;i++){// 计算每个值  左边与右边的界  得出值
            //以自身为左右边界
            int lfet = height[i];//左边界
            int right= height[i];//右边界
            for(int j=i-1;j>=0;j--){
                //寻找左边的边界
                lfet = Math.max(lfet,height[j]);
            }
            for(int k=i+1;k<length;k++){
                //寻找右边界
                right = Math.max(right,height[k]);
            }
            sum+=Math.min(lfet,right)-height[i];
            //当左右为自身时为  height[i] - height[i]
        }

        return sum;
    }
    public int trap1(int[] height) {
        int length = height.length;
        int sum=0;
        if(length<3){
            return sum;
        }

        int[] left_height = new int[length];
        int[] right_height = new int[length];

        for (int i = 1; i < length-2; i++) {
            left_height[i]=Math.max(left_height[i-1],height[i]);
        }
        for (int i = length-2; i > 0; i--) {
            right_height[i]=Math.max(left_height[i+1],height[i]);
        }
        for (int i = 1; i < length-2; i++) {
            sum+=Math.min(left_height[i],right_height[i])-height[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Code42 c = new Code42();
        int[] a={0,1,0,2,1,0,1,3,2,1,2,1};
        int b[]={4,2,0,3,2,5};
        System.out.println(c.trap1(b));
    }
}