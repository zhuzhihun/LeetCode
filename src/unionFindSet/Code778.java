package unionFindSet;

public class Code778 {
    private int N;
    private static final int[][] DIRECTIONS={{0,1},{0,-1},{1,0},{-1,0}};

    public int swimInWater(int[][] grid) {
        this.N=grid.length;

        int length=N*N-1;
        UnionFind unionFind = new UnionFind(length);
        // 下标：方格的高度，值：对应在方格中的坐标
        int[] index = new int[length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //赋值  将每个
                index[grid[i][j]] = getIndex(i,j);
            }
        }
        for (int i = 0; i < length; i++) {
            int x = index[i] / N;
            int y = index[i] % N;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY) && grid[newX][newY] <= i) {
                    unionFind.union(index[i], getIndex(newX, newY));
                }

                if (unionFind.isConnect(0, length - 1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int getIndex(int x, int y) {
        return x * N + y;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    class UnionFind{
        private int[] parent;
        public UnionFind(int n){
            parent=new int[n];
            //初始化
            for (int i = 0; i < n; i++) {
                parent[i]=i;
            }
        }

        public int find(int x){
            while (x!=parent[x]){
                parent[x]=parent[parent[x]];
                x=parent[x];//找到跟
            }
            return x;
        }
        public boolean isConnect(int x,int y){
            return find(x) == find(y);
        }
        public void union(int p,int q){
            if (isConnect(p,q))
                return;
            parent[find(p)]=parent[find(q)];//根节点设置
        }
    }

    public static void main(String[] args) {
        String s1 = "sag";
        String s2 = "ssg";
        if (s1.compareTo(s2)>0){
            System.out.println("s1>s2");
        }
        System.out.println("s2>s1");
    }

}
