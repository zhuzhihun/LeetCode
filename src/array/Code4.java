package array;

public class Code4 {
    public static double find(int[] nums1,int[] nums2){
        //先将上面排序为M<N
        if(nums1.length>nums2.length){
            return  find(nums2,nums1);
        }
        int m = nums1.length;
        int n = nums2.length;
        int left = 0;
        int right=m;
        int ansi=-1;
        int mid1=0;
        int mid2=0;

        while(left<=right){
            int i = (left+right)/2;
            int j = (m+n+1)/2-i;
            //分配好 i与j 的位置，表示nums1[i-1]、nums2[i]、nums2[j-1]、nums[j]
            int nums1left = ( i==0 ? Integer.MIN_VALUE:nums1[i-1]);
            int nums1right =( i==m ? Integer.MAX_VALUE:nums1[i]);
            int nums2left = ( j==0 ? Integer.MIN_VALUE:nums2[j-1]);
            int nums2right =( j==n ? Integer.MAX_VALUE:nums2[j]);

            if(nums1left<=nums2right){
                //left右移
                ansi = i;
                mid1=Math.max(nums1left,nums2left);
                mid2=Math.min(nums1right,nums2right);
                left=i+1;
            }else{
                //left左移
                right = i-1;
            }
        }
        return (m+n)%2==0?(mid2+mid1)/2.0:mid1;

    }
    public static double findMedianSortedArrays(int[] nums1,int[] nums2){
        if (nums1.length>nums2.length){
            return findMedianSortedArrays(nums2,nums1);
        }

        int m=nums1.length;
        int n=nums2.length;
        int left = 0,right = m ,ansi=-1;
        //median1:前一部分的最大值
        //median2:后一部分的最小值
        int median1 = 0,median2 = 0;

        while (left<=right){
            System.out.println("1.1left:"+left+",right:"+right);
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;//使得i+j=（m+n+1)/2,左右两边个数相等或差为1
            System.out.println("i:"+i+",j:"+j);

            //nums_iml,nums_i,nums_jml,nums_j分别表示nums1[i-1],nums1[i],nums2[j-1],nums2[j]
            int nums_iml = (i==0 ? Integer.MIN_VALUE:nums1[i-1]);//取最小值？
            int nums_i   = (i==m ? Integer.MAX_VALUE:nums1[i]);
            int nums_jml = (j==0 ? Integer.MIN_VALUE:nums2[j-1]);
            int nums_j   = (j==n ? Integer.MAX_VALUE:nums2[j]);
            System.out.println("nums1[i-1]"+nums_iml);
            System.out.println("nums1[i]"+nums_i);
            System.out.println("nums2[j-1]"+nums_jml);
            System.out.println("nums2[j]"+nums_j);

            if(nums_iml<=nums_j){//nums1[i-1]<=nums2[j]
                ansi =i;
                median1=Math.max(nums_iml,nums_jml);//nums1[i-1]与nums2[j-1]的最大值
                median2=Math.min(nums_i,nums_j);//nums1[i]与nums2[j]的最小值
                left = i+1;
                System.out.println("2.left:"+left+",right:"+right);
            }else {
                right = i-1;
                System.out.println("3.left:"+left+",right:"+right);
            }
            System.out.println();
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }

    public static void main(String[] args) {
        int[] nums1= {1,2,3,4};
        int[] nums2={0,5,9,13,58,98};
        double dou = find(nums1,nums2);
        System.out.println(dou);
    }
}
