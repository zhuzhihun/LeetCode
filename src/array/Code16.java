package array;

public class Code16 {
//    public static int threeSun(int[] nums,int target){
//        //List<List<Integer>> lists =new ArrayList<List<Integer>>();
//        int len = nums.length;
//        int s=Integer.MAX_VALUE;
//        System.out.println(len);
//        if (len<3){
//            return lists;
//        }
//        Arrays.sort(nums);
//        if (nums[0]>0){
//            return lists;
//        }
//        System.out.println("for开始");
//        for (int first = 0; first <len-2 ; first++) {
//            System.out.println("first:"+first+":"+nums[first]);
//            if (first>0 && nums[first] == nums[first-1]){//当枚举到相同数时跳过。
//                continue;//即当前数字与上一字符相等  即继续跳过当前循环
//            }
//            //第三个数为 数组最右端
//            int third = len-1;
//            System.out.println("third:"+third+":"+nums[third]);
//            int target = -nums[first];//使得地二个与第三个数相加为第一个数的相反数，这样三者相加为0；
//            for (int second = first+1; second < len-1; second++) {
//                System.out.println("second:"+second+":"+nums[second]);
//                //第二个数字也要与之前数不同
//                if (second>first+1 && nums[second]==nums[second-1]){
//                    continue;
//                }
//                //需要保证第二个指针在第三个左边 此时数组是有序的
//                while (second<third && nums[second]+nums[third]>target){
//                    third--;
//                }
//                if (second==third){
//                    break;
//                }
//                if (nums[second]+nums[third]==target){
//                    //找到满足三者和为0的条件
//                    List<Integer> list=new ArrayList<>();
//                    list.add(nums[first]);
//                    list.add(nums[second]);
//                    list.add(nums[third]);
//                    lists.add(list);
//                }
//
//
//            }
//
//
//        }
//
//        return lists;
//    }
//
//    public static void main(String[] args) {
//        int[] nums = {0,0,0};
//        List<List<Integer>> lists=threeSun(nums);
//        System.out.println(lists.toString());
//    }
}


