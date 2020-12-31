package DataStructure;

import java.util.ArrayList;
import java.util.List;

//回溯算法
public class DFS {
    /* code.46 全排列
给定一个 没有重复 数字的序列，返回其所有可能的全排列。
示例:
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len==0){
            return res;
        }
        




    }
}
