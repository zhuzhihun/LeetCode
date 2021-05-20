package algorithmTest;

import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(i+":"+is2Power3(i));
        }

        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        String b = sc.nextLine();
        System.out.println(isTrans(a,b));
        if (b.length()>a.length()){
            System.out.println("no");
            return;
        }
        int i=0;
        int j=0;
        while (i<a.length()&&j<b.length()){
            if (a.charAt(i)==b.charAt(j)){
                i++;
                j++;
            }else{
                i++;
            }
        }
        if (i==a.length()&&j==b.length()){
            System.out.println("yes");
        }else{
            System.out.println("no");
        }
    }
    static boolean isTrans(String word1,String word2){
        if(word2==null || word2.length()==0) return true;
        if(word1 == null || word1.equals("") || word2.length()>word1.length()) return false;
        char [] chars1 = word1.toCharArray();
        char [] chars2 = word2.toCharArray();
        int c2Index = 0;
        for(int i=0;i<chars1.length;i++){
            if(chars1[i]==chars2[c2Index]){
                c2Index++;
                if(c2Index>=word2.length()) break;  // 这里主要判断如果当这个c2Index已经大于word2.length()的时候就没有必要继续执行了
            }
        }
        return c2Index == word2.length();
    }
    public static boolean is2Power3(int num) {
        return (num & num - 1) == 0;
    }
}
