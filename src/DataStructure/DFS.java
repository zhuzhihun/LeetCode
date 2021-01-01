package DataStructure;

import java.util.ArrayList;
import java.util.Arrays;
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
        //用来保存结果的数组
        List<List<Integer>> res = new ArrayList<>();
        if (len==0){
            return res;
        }
        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();
        dfs(nums,len,0,path,used,res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, List<Integer> path,
                     boolean[] used, List<List<Integer>> res) {//方便看
        //结束条件
        if (depth==nums.length){//当深度与num中长度一致  表明num中的数字都已用完
            //path 所有最后会变为一个  即需要在保存的时候指向另一个地址;
            res.add(new ArrayList<>(path));
            //需要返回 相当于回溯条件
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!used[i]){
                //新数字添加到末尾
                path.add(nums[i]);
                //将当前数字表示为已经用过
                used[i]=true;

                //递归前
                dfs(nums, len, depth+1, path, used, res);//其中depth深度加一，表示用了的数字
                //回溯过程
                used[i]=false;
                //当前回溯的数字
                path.remove(path.size()-1);

            }
            //若这个数字已经用了 就下一个数字

        }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        //int[] n = {1,2,3};
        List<List<String>> res = dfs.solveNQueens(4);
        System.out.println(res.toString());
    }
    /* code.51 hard
    n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        //
        int[][] used = new int[n][n];
        //将棋盘全部覆盖为‘.’

        backtrack(res,0,used,n);
        return res;
    }

    private void backtrack(List<List<String>> res, int depth, int[][] used,int n) {
        if (depth==n){
            //结束条件
            List<String> list = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int j = 0; j < n; j++) {
                    if (used[i][j]>n){
                        stringBuffer.append("Q");
                    }else{
                        stringBuffer.append(".");
                    }

                }
                list.add(stringBuffer.toString());
            }
            res.add(list);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (used[depth][i]>0){
                //代表这个位置不能下
                continue;
            }
            //表示皇后下这个位置
            used[depth][i]=n+1;
            //改变棋盘不能下的地方
            changeUsed(used,depth,i,1,n);
            //递归
            backtrack(res, depth+1, used, n);
            //回溯，需要将used不能下载的地方进行改变
            changeUsed(used,depth,i,-1,n);
            used[depth][i]=0;
        }

    }
    /*
    将used中后面的变为"." 或者 " ";
     */
    private void changeUsed(int[][] used, int i, int j, int s,int n) {
        //行变为空
        for (int k = i+1; k < n; k++) {
            used[k][j]+=s;
        }
        //列变为空
        for (int k = j+1; k < n; k++) {
            used[i][k]+=s;
        }
        //改变斜的方向
        int k=i+1;
        int l=j+1;
        int p=j-1;
        while (k<n){
            if (l<n){
                used[k][l]+=s;
                l++;
            }
             if (p>=0){
                 used[k][p]+=s;
                 p--;
             }
            k++;
        }
    }
}
