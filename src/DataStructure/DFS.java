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
    /*
    code.77组合
    给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
输入: n = 4, k = 2
输出:
[ [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        boolean[] used = new boolean[n];
        combineDFS(used,res,list,n,k,0);
        return res;
        //此方法耗时很长
    }

    private void combineDFS(boolean[] used,List<List<Integer>> res,List<Integer> list ,int n, int k,int sum) {
        if (sum==k){
            //添加新的组合是必须用new 来取代 用list会将所有结果都指向同一个
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < n; i++) {
            int old;
            if(list.size()==0){
                old=0;
            }else{
                old = list.get(list.size()-1);
            }

            if (!used[i]&&i+1>old){
                //代表当前【i】没有用过
                used[i]=true;
                list.add(i+1);
                combineDFS(used, res, list, n, k, sum+1);
                used[i]=false;
                list.remove(list.size()-1);
            }
        }
    }
    /*
    更新code77. 组合问题
     */
    List<List<Integer>> ans=new ArrayList<>();
    ArrayList<Integer> list=new ArrayList<>();
    public List<List<Integer>> combine77(int n, int k) {
        newDFS77(n,k,1);
        return ans;
    }
    void newDFS77(int n,int k,int start){
        if(list.size()==k){
            ans.add(new ArrayList<>(list));
            return;
        }
        for (int i=start;i<=n;i++){
            //如果当前temp 的大小为 s，
            // 未确定状态的区间 [cur,n] 的长度为 t，
            // 如果 s + t < k，那么即使 t个都被选中，
            // 也不可能构造出一个长度为 k的序列
            // 剪枝：temp 长度加上区间 [cur, n] 的长度小于 k，不可能构造出长度为 k 的 temp
            if(list.size()+n-i+1<k){
                break;
            }
            list.add(i);
            newDFS77(n,k,i+1);
            list.remove(list.size()-1);
        }
    }
    /* code.78
    给你一个整数数组 nums ，返回该数组所有可能的子集（幂集）。解集不能包含重复的子集。

    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
         */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        //ans.add(new ArrayList<>(list));
        subsetsDFS(ans,nums,list,0);
        return ans;
    }

    private void subsetsDFS(List<List<Integer>> ans, int[] nums, ArrayList<Integer> list,int start) {
        if (start<nums.length){
            if (!ans.contains(new ArrayList<>(list)))
                ans.add(new ArrayList<>(list));
        }
        for (int i = start; i < nums.length; i++) {

            list.add(nums[i]);
            subsetsDFS(ans, nums, list, i+1);
            list.remove(list.size()-1);

        }
    }

    public static void main(String[] args) {
        DFS dfs = new DFS();
        int[] n = {1,2,3};
        List<List<Integer>> res = dfs.subsets(n);
        System.out.println(res.toString());
    }
    /*code.90
    给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    解集不能包含重复的子集。
需要先进行排序
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans=new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        Arrays.sort(nums);
        //ans.add(new ArrayList<>(list));
        subsetsWithDupDFS(ans,nums,list,0);
        return ans;
    }

    private void subsetsWithDupDFS(List<List<Integer>> ans, int[] nums, ArrayList<Integer> list,int start) {
        if (start<=nums.length){
            //if (!ans.contains(new ArrayList<>(list)))
            ans.add(new ArrayList<>(list));
        }
        for (int i = start; i < nums.length; i++) {
            if(i>start&&nums[i]==nums[i-1]){
                continue;
            }
            list.add(nums[i]);
            subsetsDFS(ans, nums, list, i+1);
            list.remove(list.size()-1);
        }
    }
}
