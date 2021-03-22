package unionFindSet;

import java.util.HashSet;
import java.util.Set;

public class Code547 {
    public int findCircleNumDFS(int[][] isConnected) {
        int length = isConnected.length;
        boolean isVisited[] = new boolean[length];//初始值 默认为false
        int count=0;
        for (int i = 0; i < length; i++) {
            if (!isVisited[i]){//表示没有走过
                dfs(isConnected,isVisited,length,i);
                count++;
            }
        }
        return 0;
    }

    private void dfs(int[][] isConnected, boolean[] isVisited, int length, int i) {
        for (int j = 0; j < length; j++) {
            if (isConnected[i][j]==1&&!isVisited[j]){
                isVisited[j]=true;
                dfs(isConnected,isVisited,length,j);
            }
        }
    }


    public static void main(String[] args) {
        int[][] n = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        Code547 code547 = new Code547();
        System.out.println(code547.findCircleNum(n));
        code547.findCircleNumDFS(n);
    }
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        int[] parent = new int[provinces];
        for (int i = 0; i < provinces; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < provinces; i++) {
            for (int j = i + 1; j < provinces; j++) {
                if (isConnected[i][j] == 1) {
                    union(parent, i, j);
                }
            }
        }
        int circles = 0;
        for (int i = 0; i < provinces; i++) {
            if (parent[i] == i) {
                circles++;
            }
        }
        return circles;
    }

    public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index1)] = find(parent, index2);
    }

    public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}

