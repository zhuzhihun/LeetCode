package BacktraceAlgorithm;

import java.util.*;

public class BFS {
    /*752. 打开转盘锁
    你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：
    例如把 '9' 变为  '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。

锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
字符串 target 代表可以解锁的数字，你需要给出最小的旋转次数，如果无论如何不能解锁，返回 -1。

     */
    public int openLock(String[] deadends, String target) {
        //保存 死亡数组
        Set<String> dead = new HashSet<>();
        for (String s:deadends){
            dead.add(s);
        }
        //保存以及走过的 路径
        Set<String> visit = new HashSet<>();

        //
        int setp=0;
        Queue<String> q = new LinkedList<>();
        q.offer("0000");
        if (dead.contains("0000")){
            return -1;
        }
        while (!q.isEmpty()){
            int n = q.size();
            for (int i = 0; i < n; i++) {
                //将列表中的现有元素取出
                String s = q.remove();

                if (s.equals(target)){
                    return setp;
                }
                //对取出元素进行 每个位置的操作
                for (int j = 0; j < 4; j++) {
                    String s1 = oneUp(s,j);
                    if (!dead.contains(s1)){
                        if (!visit.contains(s1)){
                            q.add(s1);
                            visit.add(s1);
                        }
                    }
                    String s2 = oneDown(s,j);
                    if (!dead.contains(s2)){
                        if (!visit.contains(s2)){
                            q.add(s2);
                            visit.add(s2);
                        }
                    }
                }
                //从0-3对四个位置进行 上移与下移操作
            }
            setp++;
        }
        return setp;
    }

    public static void main(String[] args) {
        String[] deadends={"0201","0101","0102","1212","2002"};
        String target="0202";
        BFS bfs = new BFS();
        int  i = bfs.openLock(deadends,target);
        System.out.println(i);
    }

    public String oneUp(String s,int j){
        char[] chars = s.toCharArray();
        if (chars[j]=='9')
            chars[j]='0';
        else
            chars[j] += 1;
        return new String(chars);
    }
    public String oneDown(String s,int j){
        char[] chars = s.toCharArray();
        if (chars[j]=='0')
            chars[j]='9';
        else
            chars[j] -= 1;
        return new String(chars);
    }
    public void BFS(String target) {


    }
    /* code.773
在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.

一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.

最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。

给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1

     */
    public int slidingPuzzle(int[][] board) {
        //将2*3数组转化为一维数组
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }

        String target = "123450";


        return 0;
    }
}
