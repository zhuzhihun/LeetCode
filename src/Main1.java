// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Main1 {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        int[] aa = {1,2,3};
        List<int[]> ints = Arrays.asList(aa);
        //ints.add(new int[]{1, 2});
        Integer[] aaa = {1,1,32,4};
        List<Integer> list = Arrays.asList(aaa);
        //list.add(13846665737);
        for (Object o : map.entrySet()) {
            AtomicInteger a=new AtomicInteger();
            List<Object> objects = Collections.synchronizedList(new ArrayList<>());
        }
        for (Object o : map.keySet()) {
            //AtomicBoolean
            AtomicIntegerArray[] atomicIntegerArrays = new AtomicIntegerArray[9];
        }
        //Collections.s
        //RandomAccess  啥都没有的一个接口  代表实现此接口的具有随机访问的功能（标识符的功能）
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);
        int n=in.nextInt();
        int a[]=new int[n];
        for (int i = 0; i < n; i++) {
            a[i]=in.nextInt();
        }
        //输入完毕
        int dp[][]= new int[n][n];
        for(int i=0;i<n;i++){
            dp[i][i]=a[i];
        }
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                dp[i][j]=dp[i][j-1]+a[j];

            }

        }

        int max=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            for(int j=i;j<n;j++){
                if(dp[i][j]%6==0&&dp[i][j]>max){
                    max=dp[i][j];
                }
            }
        }
        if(max==Integer.MIN_VALUE){
            System.out.println(-1);
        }else{
            System.out.println(max);
        }

    }

}

//-----------------------------------------------------------------------------//

class Main5 {

    private  static List<Integer> list=new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str=in.nextLine();
        str=str.substring(1,str.length()-1);
        //System.out.println(str);
        String[] s = str.split(",");
        for (int i = 0; i <s.length ; i++) {

            // System.out.println(s[i]);
        }
        int[] a = new int[s.length];
        for (int i = 0; i <s.length ; i++) {
            if(s[i].charAt(0)=='n'){
                a[i]=0;
            }else{
                a[i]=Integer.valueOf(s[i]);
            }

            //System.out.println(a[i]);
        }
        int target=in.nextInt();
        Main5 main0 = new Main5();
        TreeNode root=main0.gou(a,1);
        //System.out.println(root.val);
        //List<Integer> list=new ArrayList<>();
        main0.get(root,target);

        if(res.size()==0) {
            String ss =res.toString().substring(1,res.toString().length()-1);
            System.out.println(ss);
            //return;
        }
//        }else{
//            //System.out.print("[");
//            System.out.println(res.toString());
////            for (int i = 0; i < res.size(); i++) {
////                System.out.print(list.indexOf(i));
////                if(i<list.size()-1){
////                    System.out.print(",");
////                }
////            }
//           // System.out.print("]");
//        }}


    }
    private  static List<List<Integer>> res=new ArrayList<>();
    public void get(TreeNode root,int target){
        if (root==null){
            return;
        }
        if (root.val==target){
            list.add(root.val);
            res.add(list);
            String s =res.toString().substring(1,res.toString().length()-1);
            System.out.println(s);
            return;//找到了
        }
        if (root.val>target){

            return;
        }
        if (root.val<target){
            list.add(root.val);
            get(root.left,target-root.val);
            if (res.size()==1){
                return;
            }
//            if (list.size() > 1) {
//
//                list.remove(list.size()-1);
//            }
            get(root.right,target-root.val);
            if (res.size()==1){
                return;
            }
            if (list.size() > 1) {

                list.remove(list.size()-1);
            }

        }


    }
    public TreeNode gou(int[] a,int i){
        if (i>a.length||a[i-1]==0){
            return null;
        }
        TreeNode root = new TreeNode(a[i-1]);
        root.left=gou(a,i*2);
        root.left=gou(a,i*2+1);
        return root;

    }
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){ }
        public TreeNode(int val){
            this.val=val;
        }
    }
}