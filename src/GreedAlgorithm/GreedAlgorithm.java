package GreedAlgorithm;

import sun.awt.image.ImageWatched;

import java.util.*;

//贪心算法
public class GreedAlgorithm {
    /*
    给定一个已排序的正整数数组nums，和一个正整数n。从[1, n]区间内选取任意个数字
    补 充 到 nums 中 ， 使 得 [1,n] 区 间 内 的 任 何 数 字 都 可 以 用 nums 中 某 几 个 数 字 的 和 来 表
    示。请输出满足上述要求的最少需要补充的数字个数。
    示例 1:
    输入: n u m s=[ 1 , 3 ] , n=6
    输出: 1
    解释:
    根据n u m s里现有的组合[ 1 ] , [ 3 ] , [ 1 , 3 ]，可以得出1 , 3 , 4。
    现 在 如 果 我 们 将 2 添 加 到 n u m s 中 ， 组 合 变 为 : [ 1 ] , [ 2 ] , [ 3 ] , [ 1 , 3 ] , [ 2 , 3 ] ,
    [ 1 , 2 , 3 ]。
    其和可以表示数字1 , 2 , 3 , 4 , 5 , 6，能够覆盖[ 1 , 6 ]区间里所有的数。
    所以我们最少需要添加一个数字。


假 设 数 组 中 前 k 个 数 字 能 组 成 的 数 字 范 围 是 [1,total] ，
以合并成[1,total]U[nums [k],total+nums [k]]
     */
//    public static void main(String[] args) {
//        int nums[] = {1,5,10};
//        minPatches(nums,20);
//        System.out.println("The sum of the candy to child:"+candy(nums));
//    }
    public static int minPatches(int[] nums, int n) {
        //nums 是已经排好序的数组
        //total 表示总和
        int total = 0;
        //索引
        int index=0;
        //添加的元素个数
        int res = 0;
        while (total<n){
            if (index<nums.length && nums[index]<=total+1){//确保在数组之内，并且nums[index]已经出现在total中
                                                //total+1表示刚好中间相连起来  不用添加任何数字
                total+=nums[index];
                index++;
            }else{
                int add = total+1;
                System.out.println("添加 : "+add);
                total = total + add;
                res++;

            }
        }
        return res;
    }
/*
老师想给孩子们分发糖果，有N个孩子站成了一条直线，老师会根据每个孩子的表现，预
先给他们评分。
你需要按照以下要求，帮助老师给这些孩子分发糖果：
每个孩子至少分配到1个糖果。
评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果。
那么这样下来，老师至少需要准备多少颗糖果呢？

 */
    public static int candy(int[] ratings) {
        int[] candyFromLeft = new int[ratings.length];
        int[] candyFromRight= new int[ratings.length];
        Arrays.fill(candyFromLeft,1);
        Arrays.fill(candyFromRight,1);//每个孩子至少分一个糖果
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i]>ratings[i-1]){
                //孩子得分比前一名高
                candyFromLeft[i]=candyFromLeft[i-1]+1;
            }
        }
        for (int i = ratings.length-2; i >=0 ; i--) {
            if (ratings[i]>ratings[i+1])
                //achieve the grade is more than its right
                candyFromRight[i]=candyFromRight[i+1]+1;
        }
        //candy 表示从左到右  and 从右到左  分别需要的至少的个数
        int sum=0;//用来记录结果数
        for (int i = 0; i < ratings.length; i++) {//可以在第二个循环中直接比较得出结果
            sum+=Math.max(candyFromLeft[i],candyFromRight[i]);
        }
        return sum;
    }
    /*
    假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
    对每个孩子i，都有一个胃口值g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且
    每块饼干j，都有一个尺寸s [j]。如果s [j]>=g[i] ，我们可以将这个 饼干j 分配给孩子i ，
    这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
    示例 1:
    输入: g = [ 1 , 2 , 3 ] , s = [ 1 , 1 ]
    输出: 1
    解释:
    你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1 , 2 , 3。
    虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。

     */
    //一种最简单的方式就是先从胃口最小的孩子开始，拿最小的饼干试一下能不能满足他，
    // 如果能满足就更好，如果不能满足，在找稍微大一点的，如果还不能满足就再找更大一点的…
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);Arrays.sort(s);
        //从小到大 一个个满足 从最小胃口 最小饼干开始
        int i=0;int j=0;
        int count=0;
        while (i<g.length&&j<s.length){
            if (g[i]<=s[j]){
                i++;j++;count++;
            }else{
                //饼干小了
                j++;
            }

        }
        return count;

    }
    /*
    在柠檬水摊上，每一杯柠檬水的售价为5美元。
    顾客排队购买你的产品，（按账单bi l l s支付的顺序）一次购买一杯。
    每位顾客只买一杯柠檬水，然后向你付5美元、10美元或20美元。
    你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付5美元。
    注意，一开始你手头没有任何零钱。
    如果你能给每位顾客正确找零，返回true ，否则返回fal se 。
    示例 1：
    输入：[ 5 , 5, 5 , 10 , 20 ]
    输出：true
    解释：
    前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
    第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
    第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
    由于所有客户都得到了正确的找零，所以我们输出 true。

     */
    public static boolean lemonadeChange(int[] bills) {
        int five = 0;//5元的张数
        int ten  = 0;//10元的张数
        for (int i = 0; i < bills.length; i++) {
            if (bills[i]==5){       //5元直接添加
                five++;
            }else if(bills[i]==10){ //10元 减去5元
                five--;
                ten++;
            }else{//20元的
                if (ten>0){
                    ten--;
                    five--;
                }else
                    five-=3;

            }
            //
            if (five<0)
                //表示5元不够
                return false;
        }
        return true;
    }

//    public static void main(String[] args) {
//        int[] num = {5 , 5, 10 , 10 , 20};
//        System.out.println("true:"+lemonadeChange(num));
//    }
    /*
    给定一个二叉树，返回其节点值自底向上的层序遍历。（即按从叶子节点所在层到根节点
    所在的层，逐层从左向右遍历）
    例如：
    给定二叉树 [3,9,20,nul l ,nul l ,15,7],
           3
          / \
         9  20
           /  \
          15   7

    返回其自底向上的层序遍历为：
    [
        [15,7],
        [9,20],
        [3]
    ]
     */
    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int x){
            this.val = x;
        }
    }

    //这题类似于二叉树的BFS打印，就是一层一层的打印，一般情况下对于二叉树我们都是从
    //上往下打印，但这题是从下往上打印。直接从下往上不太好操作，可以换种思路，因为题
    //目要求的结果是从下往上就可以了，并没有要求打印的过程。
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //边界条件
        if (root==null)
            return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left!=null)
                    queue.add(node.left);
                if (node.right!=null)
                    queue.add(node.right);
            }
            res.add(0,list);//相当于每个都从最前面位置0开始插
        }
        return res;
    }
    /*
    给 定 两 个 单 词 （ beginWord 和 endWord ） 和 一 个 字 典 ，
    找 到 从 beginWord 到endWord的最短转换序列的长度。转换需遵循如下规则：
    1. 每次转换只能改变一个字母。
    2. 转换过程中的中间单词必须是字典中的单词。
    说明:
    如果不存在这样的转换序列，返回 0。
    所有单词具有相同的长度。
    所有单词只由小写字母组成。
    字典中不存在重复的单词。
    你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
    示例 1:
    输入:
    beginWord =" hit",
    endWord = " cog ",
    wordList =
    ["hot","dot","dog","lot","log","cog"]
    输出: 5
    解释: 一个最短转换序列是
    "hit" -> "hot" -> "dot" -> "dog" -> "cog"
    返回它的长度 5。
     */
    public static void main(String[] args) {
        String beg="hit";
        String end="cog";
        List<String> list = new ArrayList<>();
        list.add("hot");
        list.add("dot");
        list.add("dog");
        list.add("lot");
        list.add("log");
        list.add("cog");
        System.out.println("5->"+ladderLength(beg, end, list));
    }
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //将wordList放入Set中  方便查找
        Set<String> dictSet = new HashSet<>(wordList);
        //BFS中常见的队列，我们可以把它想象成为一颗二叉树，记录每一层的节点。
        //或者把它想象成为一个图，记录挨着的节点，也就是每一圈的节点。这里我们把它想象成为一个图
        Queue<String> queue = new LinkedList<>();
        //创建一个新的单词，记录单词是否被访问过，如果没被访问过就加入进来
        Set<String> visit = new HashSet<>();
        queue.add(beginWord);
        //这里的图是一圈一圈往外扩散的，这里的minlen可以看做扩散了多少圈，
        //也就是最短的转换序列长度
        int minlen = 1;
        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String str = queue.poll();
                for (int j = 0; j < str.length(); j++) {
                    char[] ch = str.toCharArray();
                    //这里遍历每一个节点的单词，然后修改其中一个字符，让他成为一个新的单词，
                    //并查看这个新的单词在字典中是否存在，如果存在并且没有被访问过，就加入到队列中

                    for (char k = 'a'; k <='z' ; k++) {
                        if (ch[j]==k)
                            continue;
                        ch[j]=k;
                        //修改其中一个字符  然后变成新的字符
                        String newString = new String(ch);
                        if (dictSet.contains(newString)&&visit.add(newString)){
                            //表明字典中包含这个新的字符串  并且没有被使用过，if使用了 visit.add会返回false
                            if (newString.equals(endWord))
                                return minlen+1;
                            queue.add(newString);

                        }

                    }
                }
            }
            //每循环一圈 就圈数加1
            minlen++;
        }
        return 0;
    }
    /*
给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点
便会重叠。
你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的
值相加作为节点合并后的新值，否则不为 NUL L 的节点将直接作为新二叉树的节点。

     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2){
        if (t1==null&&t2==null)
            return null;
        if (t1==null)
            return t2;
        if (t2==null)
            return t1;
        TreeNode node = new TreeNode(t1.val+t2.val);
        node.left=mergeTrees(t1.left,t2.left);
        node.right=mergeTrees(t1.right,t2.right);
        return node;

    }
}
