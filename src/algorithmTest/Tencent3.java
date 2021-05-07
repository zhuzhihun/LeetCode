package algorithmTest;

import java.util.*;

public class Tencent3 {
    public static class ListNode {
    int val;
    ListNode next = null;
  }
    public ListNode solve (ListNode S) {
        // write code here
        ListNode root = S;
        Map<Integer,ListNode> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int i=0;
        while (root!=null){
            i++;
            if (root.val<min){
                map.clear();
                //最小
                min = root.val;
                map.put(i,root);
            }
            else if (root.val==min){
                map.put(i,root);
            }else{

            }
            root=root.next;
        }
        while (map.size()>1){
            Iterator it = map.keySet().iterator();
            int mins =Integer.MAX_VALUE;
            while (it.hasNext()){
                int res = Integer.parseInt(it.toString());
                //key
                ListNode listNode=map.get(it);
                if (listNode.next!=null){
                    map.put(res,listNode.next);
                    int listmin = listNode.next.val;
                    if (listmin>mins){
                        map.remove(res);
                    }

                }else{
                    int listmin = S.val;
                    map.put(res,S);
                    if (listmin>mins){
                        map.remove(res);
                    }

                }
            }

        }
        Iterator it = map.keySet().iterator();
        ListNode listNode=null;
        while (it.hasNext()){
            int res = Integer.parseInt(it.toString());
            listNode = map.get(it);
        }
        ListNode res = listNode;
        for (int j = 0; j < i; j++) {
            if (listNode.next==null){
                listNode.next=S;
            }
            listNode = listNode.next;
            if (j==i-1){
                listNode.next=null;
            }
        }
        return res;
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] str = sc.nextLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        int b[]=new int[n];
        int a[][] = new int[n][2];
        for (int i = 0; i < n; i++) {
            a[i][0]=sc.nextInt();
            b[i]=a[i][0];
            a[i][1]=i+1;
            //1用来表示编号
        }
        Arrays.sort(a, (o1, o2) -> {
            if (o1[0]==o2[0]){
                return o1[1]-o2[1];
            }
            return o1[0]-o2[0];
        });
//        for (int i = 0; i < n; i++) {
//            System.out.println(a[i][0]+":"+a[i][1]);
//            //1用来表示编号
//        }
        int start = a[0][0];
        for (int i = 0; i < k; i++) {

            System.out.println(a[0][1]);
            a[0][0]=a[0][0]+b[a[0][1]-1];


            Arrays.sort(a, (o1, o2) -> {
                if (o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            });

        }
    }

    public static void main(String[] args) {//3
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < T; i++) {
            //测试数据
            int n = Integer.parseInt(sc.nextLine());

            String[] str1 = sc.nextLine().split(" ");
            int[][] tw = new int[n][2];
            String[] str2 = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                tw[j][0]=Integer.parseInt(str1[j]);
                tw[j][1]=Integer.parseInt(str2[j]);
            }
            //输入完毕
            Arrays.sort(tw, (o1, o2) -> {
                if (o1[0]==o2[0]){
                    return o2[1]-o1[1];
                }
                return o1[0]-o2[0];
            });
            for (int u = 0; u < n; u++) {
               System.out.println(tw[u][0]+":"+tw[u][1]);
               //1用来表示编号
            }

            int sum=0;
            for (int j = 1; j < n; j++) {
                if (tw[j][0]==tw[j-1][0]){
                    sum+=tw[j][1];
                }
//                else{
//                    sum+=tw[j][1];
//                }
            }
            System.out.println(sum);
            }
    }

    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < T; i++) {
            String str1 = sc.nextLine();
            String str2 = sc.nextLine();
            char[] ch1 = str1.toCharArray();
            char[] ch2 = str2.toCharArray();
            boolean b=tencent3(ch1,ch2);
            if (b){
                System.out.println("YES");
            }else
            {
                System.out.println("NO");
            }


        }
    }

    private static boolean tencent3(char[] ch1, char[] ch2) {
        if (ch1.length%2==0){
            int leng = ch1.length/2;
            char[] a1 = new char[leng];
            for (int i = 0; i < leng; i++) {
                a1[i]=ch1[i];
            }
            char[] a2 = new char[leng];
            for (int i = 0; i < leng; i++) {
                a2[i]=ch1[i+leng];
            }
            char[] b1 = new char[leng];
            for (int i = 0; i < leng; i++) {
                b1[i]=ch2[i];
            }
            char[] b2 = new char[leng];
            for (int i = 0; i < leng; i++) {
                b2[i]=ch2[i+leng];
            }
            if (tencent3(a1,b1)&&tencent3(a2,b2)||tencent3(a2,b1)&&tencent3(a1,b2)){
                return true;
            }
            return false;
        }else{
            //奇数一个个比较
            for (int j=0; j < ch1.length; j++) {
                if (ch1[j]!=ch2[j]){
                    return false;
                }
            }
            return true;
        }

        //return true;
    }

//    public static void main(String[] args) {
//
//    }
}
