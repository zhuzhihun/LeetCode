package algorithmTest;

import backtraceAlgorithm.BackTrace;

import java.util.*;

public class Wangyi {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(){}
        TreeNode(int val){
            this.val=val;
        }

    }
    static Map<Integer,Integer> map = new HashMap<>();

    public static void main3(String[] args) {
        TreeNode treeNode = new  TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode.left=treeNode1;
        treeNode.right=treeNode2;
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left=treeNode3;
        TreeNode treeNode4 = new TreeNode(4);
        treeNode1.right=treeNode4;
        TreeNode treeNode5 = new TreeNode(5);

        treeNode2.left=treeNode5;
        TreeNode treeNode6 = new TreeNode(6);
        //TreeNode treeNode7 = new TreeNode(2);
        treeNode3.right=treeNode6;
        //treeNode3.right=treeNode7;
        //TreeNode treeNode8 = new TreeNode(5);
        //TreeNode treeNode9 = new TreeNode(1);
        //treeNode5.left=treeNode8;
        //treeNode5.right=treeNode9;
        f(treeNode);
    }
    public static int[] f(TreeNode root) {


        getRes(0,root);
        int[][] sun = new int[map.size()][2];
        int i=0;
        Iterator<Map.Entry<Integer,Integer>> it=map.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<Integer,Integer> entry=it.next();
            sun[i][0]=entry.getKey();
            sun[i][1]=entry.getValue();
            i++;
        }
        Arrays.sort(sun, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int[] res = new int[map.size()];
        for (int j = 0; j < sun.length; j++) {
            res[j]=sun[j][1];
            System.out.println(res[j]);
        }
        return res;
    }
    public static void getRes(int index,TreeNode root){
       // Map<Integer,Integer> map = new HashMap<>();
        if (root==null)
            return;
        if (map.containsKey(index)){
            int val = map.get(index);
            if (root.val>val){
                map.put(index,root.val);
            }
        }else{
            map.put(index,root.val);
        }
       
        getRes(index-1,root.left);
        getRes(index+1,root.right);
        
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1="abc%";String str2="zxab%c%%";
        char[] c = {'%','#'};
        System.out.println(m(str1, str2, c));
    }

    private static String m(String str1, String str2, char[] c) {
        Map<Character,Integer> map =new HashMap<>();
        for (int i = 0; i < c.length; i++) {
            map.put(c[i],0);
        }
        if (str1.length()>str2.length()){
            String temp = str1;
            str1=str2;
            str2=temp;
        }
        //确保str1 比 str2 短
        int x=0;
        int y=0;
        int max =1 ;
        int i = 0;
        int j = 0;
        int flag =0;
        int end = 0;
        for (int k = 0; k < str1.length(); k++) {
            for (int l = 0; l < str2.length(); l++) {
                if (str1.length()-k<max||str2.length()-l<max)
                    break;
                i = k;
                j = l;
                while (i < str1.length()&&j < str2.length()){
//
                    char ch1  =str1.charAt(i);
                    if (map.containsKey(ch1)){
                        i++;

                        //更新
                        int res=0;
                        int a=0;
                        int d=0;
                        if(i-x>j-y){
                            a = 1;
                            res = i-x;
                            d=i;
                        }else{
                            a=2;
                            res = j-y;
                            d=j;
                        }

                        if (res>max){
                            max = res;
                            end = d;
                            flag=a;
                        }
                        continue;



                    }

                    //将包含噪声的字符跳过
                    char ch2  =str2.charAt(j);
                    //将包含噪声的字符跳过
                    if(map.containsKey(ch2)){
                        j++;

                        //更新
                        //更新
                        int res=0;
                        int a=0;
                        int d=0;
                        if(i-x>j-y){
                            a = 1;
                            res = i-x;
                            d=i;
                        }else{
                            a=2;
                            res = j-y;
                            d=j;
                        }

                        if (res>max){
                            max = res;
                            end = d;
                            flag=a;
                        }
                        continue;

                    }

                    if (ch1==ch2){
                        i++;
                        j++;
                        int res=0;
                        int a=0;
                        int d=0;
                        if(i-x>j-y){
                            a = 1;
                            res = i-x;
                            d=i;
                        }else{
                            a=2;
                            res = j-y;
                            d=j;
                        }

                        if (res>max){
                            max = res;
                            end = d;
                            flag=a;
                        }
                    } else{
                        //不相等
                        break;
                    }
                }

            }
        }
        String s = new String();
        if (flag==1){//第一个字符串
            s=str1.substring(end-max,end);
        }else {
            s=str2.substring(end-max,end);
        }

        return s;
    }

}
