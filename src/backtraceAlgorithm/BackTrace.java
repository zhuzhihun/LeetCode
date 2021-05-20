package backtraceAlgorithm;

import java.time.chrono.MinguoDate;
import java.util.ArrayList;
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
        return makesquareBacktrack(nums, nums.length - 1, total >> 2, new int[4]);//total >> 2  相当于除2再除2

    }
    //index表示访问到当前火柴的位置，target表示正方形的边长，size是长度为4的数组，
    //分别保存正方形4个边的长度

    private static boolean makesquareBacktrack(int[] nums, int index, int target, int[] size) {
        //结束条件
        if (index < 0) {
            //如果火柴都访问完了，并且size的4个边的长度都相等，说明是正方形，直接返回true，
            //否则返回false
            if (size[0] == size[1] && size[1] == size[2] && size[2] == size[3])
                return true;
            return false;
        }
        //到这一步说明火柴还没访问完
        for (int i = 0; i < size.length; i++) {
            //如果把当前火柴放到size[i]这个边上，他的长度大于target，我们直接跳过。或者
            // size[i] == size[i - 1]即上一个分支的值和当前分支的一样，上一个分支没有成功，
            //说明这个分支也不会成功，直接跳过即可。
            if (size[i] + nums[index] > target || i > 0 && size[i] == size[i - 1])
                continue;
            //如果当前火柴放到size[i]这个边上，长度不大于target，我们就放上面
            size[i] += nums[index];
            //然后在放下一个火柴，如果最终能变成正方形，直接返回true
            if (makesquareBacktrack(nums, index - 1, target, size))
                return true;
            size[i] -= nums[index];

        }
        return false;
    }

    //    public static void main(String[] args) {
//        int nums[] =  {1 , 1 , 2 , 2 , 2 };
//        System.out.println("true->"+makesquare(nums));
//        String s="AAB";
//        System.out.println("8->"+numTilePossibilities(s));
//        System.out.println("8:"+numTilePossibilities1(s));
//    }
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
        Arrays.sort(array);
        int[] res = new int[1];//保存结果  作为参数携带  改变其数组内值  其指向不变
        numTilePossibilitiesBackTrace(array, res, new boolean[tiles.length()], tiles.length(), 0);
        return res[0];
    }

    //判断字符是否使用了
    private static void numTilePossibilitiesBackTrace(char[] array, int[] res, boolean[] used, int length, int index) {
        //结束条件
        if (index == length)
            return;
        //注意，这里的i每次都是从0开始的，不是从index开始   原因：之前
        for (int i = 0; i < length; i++) {
            //如果这个字符已经被使用了
            if (used[i])
                continue;
            if (i > 0 && array[i] == array[i - 1] && !used[i - 1])//这个字符与前面的那个字符一样  且前面那个字符没有被使用
                continue;

            used[i] = true;
            res[0]++;//选择一个字符，就多了一种结果
            //下一分支继续递归
            numTilePossibilitiesBackTrace(array, res, used, length, index + 1);
            used[i] = false;
        }
    }

    public static int numTilePossibilities1(String tiles) {
        char[] array = tiles.toCharArray();

        int[] count = new int[26];
        for (char c : array)
            count[c - 'A']++;

        //先排序，目的是让相同的字符挨着，在下面计算的时候好过滤掉重复的
        int[] res = new int[1];//保存结果  作为参数携带  改变其数组内值  其指向不变
        numTilePossibilitiesBackTrace1(count, res);
        return res[0];
    }

    //判断字符是否使用了
    private static void numTilePossibilitiesBackTrace1(int[] array, int[] res) {
        //遍历所有字符
        for (int i = 0; i < 26; i++) {
            if (array[i] == 0)
                continue;
            //如果没使用完就继续使用，然后把这个字符的数量减1
            array[i]--;
            res[0]++;
            numTilePossibilitiesBackTrace1(array, res);
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
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        splitIntoFibonacciBackTrace(S.toCharArray(), res, 0);
        return res;

    }

    public static boolean splitIntoFibonacciBackTrace(char[] digit, List<Integer> res, int index) {
        //边界条件判断，如果截取完了，并且res的长度大于等于3，表示找到了一个组合
        if (index == digit.length && res.size() > 2)
            return true;
        for (int i = index; i < digit.length; i++) {
            //两位以上的数字不能以0开头
            if (digit[index] == '0' && i > index) {
                break;
            }
            //截取字符串转化为数字
            long num = subDigit(digit, index, i + 1);
            //如果截取的数字大于int的最大值，则终止截取
            if (num > Integer.MAX_VALUE) {
                break;
            }
            int size = res.size();
            //如果截取的数字大于res中前两个数字的和，说明这次截取的太大，直接终止，因为后面越截取越大
            if (size >= 2 && num > res.get(size - 1) + res.get(size - 2)) {
                break;
            }
            if (size <= 1 || num == res.get(size - 1) + res.get(size - 2)) {
                //将数字num添加到结合res中
                res.add((int) num);
                //如果找到了直接返回
                if (splitIntoFibonacciBackTrace(digit, res, i + 1))
                    return true;
                //如果没有找到，就会走回溯这一步，然后把上一步添加到结合res中的数字移除
                res.remove(res.size() - 1);
            }
        }
        return false;
    }

    //截取字符串S中的字串然后转为十进制数字
    private static long subDigit(char[] digit, int start, int end) {
        long res = 0;
        for (int j = start; j < end; j++) {
            res = res * 10 + digit[j] - '0';//即digit中字符相对于‘0’的位置
        }
        return res;
    }

    /*
    给定一个二维网格和一个单词，找出该单词是否存在于网格中。
    单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水
    平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
    示例:
    b o a rd =
    [
    [ 'A' , 'B' , 'C' , 'E' ] ,
    [ 'S' , 'F' , 'C' , 'S' ] ,
    [ 'A' , 'D' , 'E' , 'E' ]
    ]
    原创 山大王wld 数据结构和算法 11月17日给定 wo rd = "ABCCED", 返回 true
    给定 wo rd = "SEE", 返回 true
    给定 wo rd = "ABCB", 返回 fa l s e
     */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        //下面两个for循环，来遍历数组中的每一个值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //从[i,j]开始查找，找到直接返回true，后面结果不需要进行查找了
                if (existdfs(board, words, i, j, 0))
                    return true;
            }
        }
        return false;
    }

    private static boolean existdfs(char[][] board, char[] words, int i, int j, int index) {
        //index为查找字符的第几个
        //边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符
        //如果这二货字符不等于board[i][j]，说明验证这个坐标路径是走不通的，直接返回false
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[index]) {
            //i>=board.length||i<0||j>=board[0].length||j<0
            //表示i与j的越界情况
            return false;
        }
        //如果word的每个字符都查找完了，直接返回true
        if (index == words.length - 1)//查找到了最后一个字符
            return true;
        //把当前坐标的值保存下来，为了最后复原
        char tmp = board[i][j];
        //然后修改当前坐标的值
        board[i][j] = '.';
        //防止走回头路
        //走递归，沿着当前坐标的上下左右4个方向查找
        boolean res = existdfs(board, words, i + 1, j, index + 1)
                //往左
                || existdfs(board, words, i - 1, j, index + 1)
                //往下
                || existdfs(board, words, i, j + 1, index + 1)
                //往上
                || existdfs(board, words, i, j - 1, index + 1);
        //上面4个方向，只要有一个能查找到，就返回true；
        //递归之后再把当前的坐标还原
        board[i][j] = tmp;
        return res;

    }

    /*
    给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
    说明：解集不能包含重复的子集。
    示例:
    输入: n u m s = [ 1 , 2 , 3 ]
    输出:
    [
    [ 3 ] ,
    [ 1 ] ,
    [ 2 ] ,
    [ 1 , 2 , 3 ] ,
    [ 1 , 3 ] ,
    [ 2 , 3 ] ,
    [ 1 , 2 ] ,
    [ ]
    ]
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        subsetsBacktrack(lists, new ArrayList<>(), nums, 0);
        return lists;
    }

    private void subsetsBacktrack(List<List<Integer>> list, ArrayList<Integer> tempList, int[] nums, int start) {
        //走过的所有路径都是子集中的一部分，所以都要加入到集合中
        list.add(new ArrayList<>(tempList));//一开始就加入空的字符串
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            subsetsBacktrack(list, tempList, nums, start + 1);
            tempList.remove(tempList.size() - 1);
        }

    }
    //找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每
    //种组合中不存在重复的数字。
    //示例 1:
    //输入: k = 3 , n = 7
    //输出: [ [ 1 , 2 , 4 ] ]
    //示例 2:
    //输入: k = 3 , n = 9
    //输出: [ [ 1 , 2 , 6 ] , [ 1 , 3 , 5 ] , [ 2 , 3 , 4 ] ]
    //就是从1-9中选出k个数字，他们的和等于n，并且这k个数字不能有重复的。

    //    public static void main(String[] args) {
//        List<List<Integer>> lists = combinationSum3(3, 10);
//        for (List<Integer> list:lists){
//            System.out.println(list.toArray());
//        }
//    }
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum3DFS(res, new ArrayList<Integer>(), 1, n);
        return res;
    }

    private static void combinationSum3DFS(List<List<Integer>> res, ArrayList<Integer> list, int start, int n) {
        //总之条件
        if (n <= 0) {
            //如果找到一组合适的就把他加入到集合list中
            if (n == 0)
                res.add(new ArrayList<>(list));
            return;
        }
        //
        for (int i = start; i <= 9; i++) {
            //选择当前值
            list.add(i);
            //递归
            combinationSum3DFS(res, list, start, n - i);
            //撤销选择
            list.remove(list.size() - 1);
        }


    }

    /*
    转化为 从数组中找到n个数满足相加等于target 其中n数与1-n
     */
    public static List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        combinationSumDFS(res, new ArrayList<Integer>(), 0, nums, 0, target);
        return res;
    }

    private static void combinationSumDFS(List<List<Integer>> res, ArrayList<Integer> list, int start, int[] nums, int index, int target) {
        //总之条件
        if (target <= 0) {
            //如果找到一组合适的就把他加入到集合list中
            if (target == 0) {
                //先判断是否含有相同数据
                //由于已经排好序，因此相同的必定是前一个数据

                res.add(new ArrayList<>(list));
            }

            return;
        }
        //
        for (int i = start; i < nums.length; i++) {
            //用来判断是否需要相同的数据
//            if(i>0&&nums[i]==nums[i-1])
//                continue;
            //选择当前值

            if (target - nums[i] < 0)
                return;
            list.add(nums[i]);
            //递归
            combinationSumDFS(res, list, i + 1, nums, index, target - nums[i]);
            //撤销选择
            list.remove(list.size() - 1);
        }

    }

    public static void main1(String[] args) {
        int[] nums = {9, 1, 8, 2, 3, 4, 7, 20, 21, 1};
        int target = 9;
        List<List<Integer>> res = combinationSum(nums, target);
        printList(res);
    }

    /*
    你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m *
    n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如
    果该单元格是空的，那么就是 0。
    为了使收益最大化，矿工需要按以下规则来开采黄金：
    每当矿工进入一个单元，就会收集该单元格中的所有黄金。
    矿工每次可以从当前位置向上下左右四个方向走。
    每个单元格只能被开采（进入）一次。
    不得开采（进入）黄金数目为 0 的单元格。
    矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
    示例 1：
    原创 山大王wld 数据结构和算法 9月4日输入：
    g ri d = [ [ 0 , 6 , 0 ] , [ 5 , 8 , 7 ] , [ 0 , 9 , 0 ] ]
    输出：2 4
    解释：
    [ [ 0 , 6 , 0 ] ,
      [ 5 , 8 , 7 ] ,
      [ 0 , 9 , 0 ] ]
    一种收集最多黄金的路线是：
    9 -> 8 -> 7。

     */
    public int getMaximumGold(int[][] grid) {
        if (grid == null || grid.length == 0)
            return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {//从每个位置进行开始
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0)
                    continue;//最开始位置不能为0
                max = Math.max(getMaximumGoldDFS(i, j, grid), max);
            }
        }

        System.out.println(max);
        return max;

    }

    private int getMaximumGoldDFS(int i, int j, int[][] grid) {
        //递归结束条件
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        //循环体
        //当前位置
        int temp = grid[i][j];
        //改变grid的值 防止再走这一过程
        grid[i][j] = 0;
        //四个方向
        int up = getMaximumGoldDFS(i - 1, j, grid);
        int down = getMaximumGoldDFS(i + 1, j, grid);
        int left = getMaximumGoldDFS(i, j - 1, grid);
        int right = getMaximumGoldDFS(i, j + 1, grid);
        int max = Math.max(up, Math.min(down, Math.max(left, right)));

        //回溯
        grid[i][j] = temp;
        return max + grid[i][j];
    }

    /*
    输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树
    的根节点开始往下一直到叶节点所经过的节点形成一条路径。
    示例:
    给定如下二叉树，以及目标和 sum = 22，

        5
       / \
      4   8
     /   / \
    11  13  4
   / \     / \
  7   2   5   1
    [
    [ 5 , 4 , 11 , 2 ] ,
    [ 5 , 8 , 4 , 5 ]
    ]
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(5);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(8);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        TreeNode treeNode3 = new TreeNode(11);
        treeNode1.left = treeNode3;
        TreeNode treeNode4 = new TreeNode(13);

        TreeNode treeNode5 = new TreeNode(4);
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        TreeNode treeNode6 = new TreeNode(7);
        TreeNode treeNode7 = new TreeNode(2);
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        TreeNode treeNode8 = new TreeNode(5);
        TreeNode treeNode9 = new TreeNode(1);
        treeNode5.left = treeNode8;
        treeNode5.right = treeNode9;
        List<List<Integer>> lists = pathSum(treeNode, 22);
        printList(lists);
    }

    private static void printList(List<List<Integer>> lists) {
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> list = lists.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + "\t");
            }
            System.out.println();
        }
    }

    // 找到目标和等于target的路径（从根节点开始 ， 并且不可逆）
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null || sum < 0)
            return res;
        pathSumDFS(res, new ArrayList<>(), root, sum);
        return res;
    }

    private static void pathSumDFS(List<List<Integer>> res, ArrayList<Integer> list, TreeNode root, int sum) {
        if (sum < 0)//不正确
            return;
        if (sum == 0) {//找到一条路劲
            res.add(new ArrayList<>(list));
            return;
        }
        if (root == null)
            return;
        list.add(root.val);
        pathSumDFS(res, list, root.left, sum - root.val);
        //list.remove(list.size()-1);
        pathSumDFS(res, list, root.right, sum - root.val);
        list.remove(list.size() - 1);
    }
    /*

    输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树
    的根节点开始往下一直到叶节点所经过的节点形成一条路径。
    示例:
    给定如下二叉树，以及目标和 sum = 22，

        5
       / \
      4   8
     /   / \
    11  13  4
   / \     / \
  7   2   5   1
    [
    [ 5 , 4 , 11 , 2 ] ,
    [ 5 , 8 , 4 , 5 ] ,
    [ 4 , 18 ] 多出来的一组数据
    ]

     */
    //实现任意路径之和为 target
//    public static List<List<Integer>> pathSumAll(TreeNode root, int sum) {
//        List<List<Integer>> res = new ArrayList<>();
//        if (root==null||sum<0)
//            return res;
//        pathSumDFS(res,new ArrayList<>(),root,sum);
//        return res;
//    }

    /*
    请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路
    径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果
    一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
    例 如 ， 在 下 面 的 3×4 的 矩 阵 中 包 含 一 条 字 符 串 “bf ce” 的 路 径 （ 路 径 中 的 字 母 用 加 粗 标
    出）
    示例 1：
    输入：
    b o a r d = [   ["A","B","C","E"] ,
                    ["S","F","C","S"] ,
                    ["A","D","E","E"] ] ,
    wo r d = "ABCCED"
    输出：tr u e

     */
    public static boolean exist1(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (existDFS(board,words,i,j,0))
                    return true;
            }
        }
        return false;
    }

    private static boolean existDFS(char[][] board, char[] words, int i, int j, int index) {
        if (board[i][j]!=words[index])
            return false;
        //边界条件
        if (i<0||j<0||i>=board.length||j>=board[0].length)
            return false;
        if (index==words.length-1)
            return true;
        char temp = board[i][j];
        //判断剩下的四个方向上是否有与接下来字符相同的
        boolean res = existDFS(board, words, i-1, j, index+1)||
                existDFS(board, words, i, j-1, index+1)
                ||existDFS(board, words, i, j+1, index+1)||
                existDFS(board, words, i+1, j, index+1);
        board[i][j]=temp;
        return res;
    }

}