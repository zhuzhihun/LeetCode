package algorithmTest;

//连通图判断
public class DFSLT {

    /**
     * 这里是注释文档
     * * S表示无穷大
     * <p>
     * 连通分量设置为3个
     * 0 1 2 3 4 5 6 7 8
     * -------------------------
     * 0
     * 1   s s s s s s s s
     * 2   s s s 1 1 s s s
     * 3   s s s s s 1 1 s
     * 4   s 1 s s s s s 1
     * 5   s 1 s s s s s 1
     * 6   s s 1 s s s 1 s
     * 7   s s 1 s s 1 s s
     * 8   s s s 1 1 s s s
     *
     * @param args
     */
    static int color[];
    static int d = 0;

    public static void main(String[] args) {

        int s = Integer.MAX_VALUE;
        int G[][] = {{s, s, s, s, s, s, s, s, s},
                     {s, s, s, s, s, s, s, s, s},
                     {s, s, s, s, 1, 1, s, s, s},
                     {s, s, s, s, s, s, 1, 1, s},
                     {s, s, 1, s, s, s, s, s, 1},
                     {s, s, 1, s, s, s, s, s, 1},
                     {s, s, s, 1, s, s, s, 1, s},
                     {s, s, s, 1, s, s, 1, s, s},
                     {s, s, s, s, 1, 1, s, s, s}};
        color = new int[9];

        ProcedureDFS(G, 9);
        PDLTGS(color);
    }

    private static void PDLTGS(int[] color2) {
        // TODO 自动生成的方法存根
        int temp;
        for (int i = 1; i < color2.length; i++) {
            for (int j = color2.length - 2; j >= 1; j--) {
                if (color2[j + 1] < color2[j]) {
                    temp = color2[j + 1];
                    color2[j + 1] = color2[j];
                    color2[j] = temp;
                }
            }
        }
        for (int i = 1; i < color2.length - 1; i++) {
            if (color2[i + 1] != color2[i])
                d++;
        }
        System.out.println("连通分量的个数：" + (d + 1));
    }

    public static void ProcedureDFS(int[][] G, int n) {
        //图是以二维数组的形式保存
        //n是二维数组的维数，n需要小于10，结果才会正确，否则算法上，初始化没搜索就需要换一个数字

        for (int i = 1; i <= n - 1; i++) {

            color[i] = 10;//把每一个顶点都置为10，表示还没搜索
        }

        for (int i = 1; i <= n - 1; i++) {

            //对于每一个顶点没被访问的顶点进行访问
            if (color[i] == 10) {
                color[i] = i;
                DFS_visit(G, i, color[i]);//遍历其访问的顶点
            }

        }

    }

    private static void DFS_visit(int[][] g, int i, int color2) {
        for (int t = 1; t <= g.length - 1; t++) {
            //邻接点没有被访问到
            if (color[t] == 10 && g[i][t] != Integer.MAX_VALUE) {
                color[t] = color2;
                DFS_visit(g, t, color[t]);
            }
        }
    }
}