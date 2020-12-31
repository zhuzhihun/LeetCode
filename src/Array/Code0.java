package Array;

import java.util.LinkedList;


public class Code0 {
    public static String replaceSpace(String s){
        StringBuilder res = new StringBuilder();
        for(Character c:s.toCharArray()){
            if (c==' '){
                res.append("%20");
            }else{
                res.append(c);
            }
        }

        return res.toString();
    }
    public static int[] reversrePrint(ListNode head){
        LinkedList<Integer> stack = new LinkedList<Integer>();
        while (head!=null){
            stack.add(head.val);
            head=head.next;
        }
        int[] res = new int[stack.size()];
        for (int i = 0; i < res.length; i++) {
            res[i]=stack.removeLast();
        }
        return res;
    }

    public static void main(String[] args) {
        String s =" asf asf ";
        s=s.replace(" ","a");
        s=replaceSpace(s);
        StringBuilder a=new StringBuilder();
        a.append(s);
        a.replace(1,2,"we");
        System.out.println(a);

        ListNode listNode=new ListNode(1);
        //Solution sf=new Solution();
        String s1 = "1235.56e52";
        //boolean b =sf.isNumber(s1);

    }
}
