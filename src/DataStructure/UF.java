package DataStructure;

//并查集算法，主要是解决图论中「动态连通性」问题的

//这里所说的「连通」是一种等价关系，也就是说具有如下三个性质：
//1、自反性：节点p和p是连通的。
//2、对称性：如果节点p和q连通，那么q和p也连通。
//3、传递性：如果节点p和q连通，q和r连通，那么p和r也连通。
//比如说之前那幅图，0～9 任意两个不同的点都不连通，调用connected都会返回 false，连通分量为 10 个。
//如果现在调用union(0, 1)，那么 0 和 1 被连通，连通分量降为 9 个。
//再调用union(1, 2)，这时 0,1,2 都被连通，调用connected(0, 2)也会返回 true，连通分量变为 8 个。

class UF {
    // 连通分量个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录树的“重量”
    private int[] size;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ)
            return;

        // 小树接到大树下面，较平衡
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    private int find(int x) {
        while (parent[x] != x) {
            // 进行路径压缩
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }

    //给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或 "a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
    //
    //只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回 true，否则返回 false。 

    public boolean equationsPossible(String[] equations){
        UF uf = new UF(26);
        for(String s:equations){// 先让相等的字母形成连通分量
            if (s.charAt(1)=='='){
                char x=s.charAt(0);
                char y=s.charAt(3);
                uf.union(x-'a',y-'a');
            }
        }
        for (String s:equations){//根据不等号判断 全部
            // 检查不等关系是否打破相等关系的连通性
            if(s.charAt(1)=='!'){
                char x=s.charAt(0);
                char y=s.charAt(3);
                if (uf.connected(x-'a',y-'a')){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}