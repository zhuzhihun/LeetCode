package backtraceAlgorithm;

import java.util.Arrays;
import java.util.List;

public class BackTrace {
    /*
输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼
成正方形。
示例 1:
输入: [ 1 , 1 , 2 , 2 , 2 ]
输出: tru e
解释: 能拼成一个边长为2的正方形，每边两根火柴。
示例 2:
输入: [ 3 , 3 , 3 , 3 , 4 ]
输出: f a l se
解释: 不能用所有火柴拼成一个正方形。
 */

    public static boolean makesquare(int[] nums) {
          int total = 0;
          //统计所有火柴的长度
          for (int num : nums) {
             total += num;
             }
          //如果所有火柴的长度不是4的倍数，直接返回false
          if (total == 0 || (total & 3) != 0)//total & 3相当于 判断是不是4的倍数
             return false;
          Arrays.sort(nums);
          //回溯
          //从最长的这跟火柴开始会降低一定的次数
          return makesquareBacktrack(nums, nums.length-1, total >> 2, new int[4]);//total >> 2  相当于除2再除2

    }
    //index表示访问到当前火柴的位置，target表示正方形的边长，size是长度为4的数组，
    //分别保存正方形4个边的长度

    private static boolean makesquareBacktrack(int[] nums, int index, int target, int[] size) {
        //结束条件
        if (index<0){
            //如果火柴都访问完了，并且size的4个边的长度都相等，说明是正方形，直接返回true，
            //否则返回false
            if (size[0]==size[1]&&size[1]==size[2]&&size[2]==size[3])
                return true;
            return false;
        }
        //到这一步说明火柴还没访问完
        for (int i = 0; i < size.length; i++) {
             //如果把当前火柴放到size[i]这个边上，他的长度大于target，我们直接跳过。或者
             // size[i] == size[i - 1]即上一个分支的值和当前分支的一样，上一个分支没有成功，
             //说明这个分支也不会成功，直接跳过即可。
            if (size[i]+nums[index]>target||i>0&&size[i]==size[i-1])
                continue;
            //如果当前火柴放到size[i]这个边上，长度不大于target，我们就放上面
            size[i]+=nums[index];
            //然后在放下一个火柴，如果最终能变成正方形，直接返回true
            if (makesquareBacktrack(nums,index-1,target,size))
                return true;
            size[i]-=nums[index];

        }
        return false;
    }

    public static void main(String[] args) {
        int nums[] =  {1 , 1 , 2 , 2 , 2 };
        System.out.println("true->"+makesquare(nums));
        String s="AAB";
        System.out.println("8->"+numTilePossibilities(s));
        System.out.println("8:"+numTilePossibilities1(s));
    }
    /*
    你有一套活字字模ti les，其中每个字模上都刻有一个字母ti les [i]。返回你可以印出的
    非空字母序列的数目。
    注意：本题中，每个活字字模只能使用一次。
    示例 1：
    输入："AAB"
    输出：8
    解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
     */
    public static int numTilePossibilities(String tiles) {
        char[] array = tiles.toCharArray();
        //先排序，目的是让相同的字符挨着，在下面计算的时候好过滤掉重复的
        int[] res = new int[1];//保存结果  作为参数携带  改变其数组内值  其指向不变
        numTilePossibilitiesBackTrace(array,res,new boolean[tiles.length()], tiles.length(), 0);
        return res[0];
    }
    //判断字符是否使用了
    private static void numTilePossibilitiesBackTrace(char[] array, int[] res, boolean[] used, int length, int index) {
        //结束条件
        if (index==length)
            return;
        //注意，这里的i每次都是从0开始的，不是从index开始   原因：之前
        for (int i = 0; i < length; i++) {
            //如果这个字符已经被使用了
            if (used[i])
                continue;
            if (i>0&&array[i]==array[i-1]&&!used[i-1])//这个字符与前面的那个字符一样  且前面那个字符没有被使用
                continue;

            used[i]=true;
            res[0]++;//选择一个字符，就多了一种结果
            //下一分支继续递归
            numTilePossibilitiesBackTrace(array, res, used, length, index+1);
            used[i]=false;
        }
    }
    public static int numTilePossibilities1(String tiles) {
        char[] array = tiles.toCharArray();

        int[] count = new int[26];
        for (char c : array)
            count[c - 'A']++;

        //先排序，目的是让相同的字符挨着，在下面计算的时候好过滤掉重复的
        int[] res = new int[1];//保存结果  作为参数携带  改变其数组内值  其指向不变
        numTilePossibilitiesBackTrace1(count,res);
        return res[0];
    }
    //判断字符是否使用了
    private static void numTilePossibilitiesBackTrace1(int[] array, int[] res) {
        //遍历所有字符
        for (int i = 0; i < 26; i++) {
            if (array[i]==0)
                continue;
            //如果没使用完就继续使用，然后把这个字符的数量减1
            array[i]--;
            res[0]++;
            numTilePossibilitiesBackTrace1(array,res);
            //当前字符使用完之后，把它的数量还原
            array[i]++;
        }
        
    }

     /*
    private void backtrack("原始参数") {
         //终止条件(递归必须要有终止条件)
         if ("终止条件") {
             //一些逻辑操作（可有可无，视情况而定）
             return;
             }

         for (int i = "for循环开始的参数"; i < "for循环结束的参数"; i++) {
             //一些逻辑操作（可有可无，视情况而定）

             //做出选择

             //递归
             backtrack("新的参数");
             //一些逻辑操作（可有可无，视情况而定）16
             //撤销选择
             }
    */
    /*
    给定一个数字字符串S，比如S= "123456579"，我们可以将它分成斐波那契式的序列
    [123, 456, 579]。
    示例 2：
    输入: " 11 2 3 5 8 1 3 "
    输出: [ 1 , 1 , 2 , 3 , 5 , 8 , 1 3 ]
    示例 3：
    输入: " 11 2 3 5 8 1 3 0 "
    输出: [ ]
    解释: 这项任务无法完成。
    输入: " 11 0 1111 "
    输出: [ 11 0 , 1 , 111 ]
    解释: 输出 [ 11 , 0 , 11 , 11 ] 也同样被接受。

     */
//     public List<Integer> splitIntoFibonacci(String S) {
//
//
//
//     }
//    public static boolean splitIntoFibonacciBackTrace(char[] digit,int index,List<Integer> list){
//         //结束条件
//        if (index==digit.length&&list.size()>2)
//            return true;
//        for (int i = 0; i < digit.length(); i++) {
//
//        }
//    }
    }
