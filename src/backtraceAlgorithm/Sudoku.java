package backtraceAlgorithm;

public class Sudoku {
    /*code.37
编写一个程序，通过填充空格来解决数独问题。

一个数独的解法需遵循如下规则：

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
空白格用 '.' 表示。
     */
    public void solveSudoku(char[][] board) {

        solveSudokuDFS(board,0,0);

    }

    private boolean solveSudokuDFS(char[][] board,int m,int n) {
        //返回条件
        if(n==9){
            //代表到了最后一列 应该转到下一行
            return solveSudokuDFS(board,m+1,0);
        }
        if (m==9){
            //找到一个全部的解
            return true;
        }
        //for 1->9
        if (board[m][n]!='.'){
            //表明此处有数字
            return solveSudokuDFS(board, m, n+1);
        }


        for (char i = '1'; i <= '9'; i++) {
            if (!isValid(board,m,n,i)){
                //遇到不满足的情况
                continue;
            }
            board[m][n]=i;
            if (solveSudokuDFS(board, m, n+1)){
                return true;
            }
            //说明上面行不通
            //回溯情况
            board[m][n]='.';

        }
        //此时表明没有解出来
        return false;

    }
    //用来检测当前的数组是否合法
    private boolean isValid(char[][] board,int m,int n,char c) {
        for (int i = 0; i <9 ; i++) {
            //行与列的检查
            if (board[m][i]==c||board[i][n]==c){
                return false;
            }
            //检查相应的9宫格位置
            if (board[m/3*3+i/3][n/3*3+i%3]==c){
                return false;
            }
        }
        return true;
    }
}
