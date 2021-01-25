package DP;

public class   Code72 {
    Node[][] dp;
    public Node[][] minDistance(String word1,String word2){
        int m=word1.length(),n=word2.length();
        //构建m+1*n+1的初始化数组
        dp= new Node[m + 1][n + 1];
        //将基础数据填写
        dp[0][0]=new Node(0,0);
        for (int i = 1; i <= m; i++) {
            Node nod = new Node(i,2);
            dp[i][0]=nod;
            //dp[i][0].setVal(i);//啥问题？

        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = new Node();
            dp[0][j].val=j;
            dp[0][j].chioce=1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                    dp[i][j].chioce=0;
                }
                else{
                    //获取 此时 到这步骤 是经历 替换 删除 还是 插入的操作;
                    Node low=new Node(1,1);
                    if(dp[i][j-1].val<dp[i-1][j].val){
                        low.val=dp[i][j-1].val;
                        low.chioce=1;

                    }else {
                        low.val=dp[i-1][j].val;
                        low.chioce=2;
                    }
                    if (low.val>dp[i-1][j-1].val){
                        low=dp[i-1][j-1];
                        low.chioce=3;
                    }
                    dp[i][j].val=1+Math.min(dp[i-1][j-1].val,Math.min(dp[i][j-1].val,dp[i-1][j].val));
                    dp[i][j].chioce=low.chioce;
                }
            }
        }
        return dp;

    }
    class Node{
        int val;
        int chioce;

        public  Node(){}
        public  Node(int v,int c){
            this.val=v;
            this.chioce=c;
        }
        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public int getChioce() {
            return chioce;
        }

        public void setChioce(int chioce) {
            this.chioce = chioce;
        }
// 0 代表啥都不做
        // 1 代表插入
        // 2 代表删除
        // 3 代表替换
    }

    public static void main(String[] args) {
        Code72 c = new Code72();
        String s1 ="horse";
        String s2 ="ros";
        Node[][] node=c.minDistance(s1,s2);
        System.out.println(node.length);
    }

}
