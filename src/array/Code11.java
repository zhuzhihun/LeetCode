package array;

public class Code11 {
    public static int maxArea(int[] height) {
        int len = height.length;
        int left=0,right=len-1;
        //System.out.println(right);
        int area=0;
        while(left<right){
            area=Math.max(area,((right-left)*Math.min(height[left],height[right])));
            if (height[left]<height[right]){
                left++;
            }else
                right--;
        }

        return area;
    }

    public static void main(String[] args) {
        int[] h = {4,3,2,1,4};
        int s = maxArea(h);
        System.out.println(s);
    }
}
