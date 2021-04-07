package algorithmTest;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Tencent {
    //1(0%).判断一棵树是否为完全二叉树(还是满二叉树)

    static class TreeNode {
        int val = 0;

        TreeNode left = null;
        TreeNode right = null;
        public TreeNode(int x){
            this.val=x;
        }
    }
    public static void main(String[] args) {
        TreeNode node1=new TreeNode(302);

        TreeNode node2=new TreeNode(196);
        TreeNode node3=new TreeNode(100);
        node1.left=node2;
        node1.right=node3;
        TreeNode node4=new TreeNode(162);
        TreeNode node5=new TreeNode(178);
        node2.left=node4;
        node4.left=node5;
//        TreeNode node6=new TreeNode(6);
//        TreeNode node7=new TreeNode(7);
//        node3.left=node6;
//        node3.right=node7;
//        TreeNode node8=new TreeNode(8);
//        node4.left=node8;
        //Main main = new Main();
        //TreeNode root = main.solve(node1);
        //System.out.println(root);
    }
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 你需要返回一个指针，指向root，表示删减去若干个点后，剩下的树
     * @param root TreeNode类 指向二叉树的根
     * @return TreeNode类
     */
    public TreeNode solve (TreeNode root) {
        // write code here
        //对称树
        solve(root.left,root.right);
        return root;
    }
    public boolean solve (TreeNode leftroot, TreeNode rightroot) {
        // write code here
        //对称树
        if(leftroot!=null&&rightroot!=null){
            if(!solve(leftroot.left,rightroot.right)) {
                leftroot.left=null;
                rightroot.right=null;

            }
            if(!solve(leftroot.right,rightroot.left)){
                leftroot.right=null;
                rightroot.left=null;
            }

        }
        else if(leftroot==null&&rightroot==null ){
            return true;
        }else{
            return false;
        }
        return true;
    }

    //==============================================================
    //2(AC).一个数组判断相邻是否满足等于十,若等于则从数组中消掉.利用栈的性质,等于十出栈,不等则入栈.
    public static void main02(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String s = sc.nextLine();
        int a[]= new int[n];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            a[i]=ch-48;
        }
        //n   yu   a[]
        Stack<Integer> stack = new Stack<>();
        //stack.add(a[0]);
        for (int i = 0; i <n ; i++) {
            if(stack.isEmpty()){
                stack.add(a[i]);
            }else{//不为空
                if(a[i]+stack.peek()==10){
                    stack.pop();
                }else{
                    stack.add(a[i]);
                }
            }
        }
//        if(stack.isEmpty()){
//
//        }
        System.out.println(stack.size());

    }
    //==============================================================
    //3(0%).走钢索问题,一根平衡杆,每次最多两个人过,且需要一人带着杆子走回来.
    public static class Main03 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            int N = Integer.valueOf(sc.nextLine());
            while (sc.hasNext()){
                int n = Integer.valueOf(sc.nextLine());
                String[] strings = sc.nextLine().split(" ");
                int a[]=new int[n];
                for (int j = 0; j < n; j++) {
                    a[j]=Integer.parseInt(strings[j]);
                }
                //数据输完
                Arrays.sort(a);
                //排序
                if (a.length<2){
                    System.out.println(a[0]);
                }else{


                    int min1 = a[0];
                    int min2 = a[1];
                    int sum=min2;
                    for (int j = 2; j < n; ) {
                        if(j+1<n){
                            sum+=min2*2;
                            sum+=a[j+1];
                            j+=2;
                            if(j>=n){
                                sum+=min1;
                            }
                        }
                        else{
                            sum+=min1;
                            sum+=a[j];
                            j++;
                        }
                    }
                    System.out.println(sum);
                }
            }
        }
    }
    //==============================================================
    //4.手机充电问题.手机电量a[0]  每秒耗电a[1].充电线性充电,每秒冲i*w.换手机充电中间不耗时.判断手机最多能坚持多久


    //==============================================================
    //5.砝码问题.给定目标值m(可以是n*m).判断砝码个数最少满足要求(01背包).

}



