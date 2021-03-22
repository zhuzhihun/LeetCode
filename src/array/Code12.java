package array;

public class Code12 {
    int[]    values  = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
    String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length && num>=0; i++) {//从最大的1000开始找
            while (values[i]<=num){//如果num大于当前的values则进行操作
                num=num-values[i];//减去当前最大值
                sb.append(symbols[i]);//没减一次，添加对应的symbols.
            }

        }

        return sb.toString();
    }
    public static int romanToInt(String s){
        s=s.replace("IV","a");//将6组特殊的两个字符组合转变为  不同的单字符
        s=s.replace("IX","b");
        s=s.replace("XL","c");
        s=s.replace("XC","d");
        s=s.replace("CD","e");
        s=s.replace("CM","f");
        //此时，只需要一个个字符转化为对应数字进行累加
        int sum=0;
        for (int i = 0; i < s.length(); i++) {
            System.out.println("s[i]"+wh(s.charAt(i)));
            sum+=wh(s.charAt(i));
        }

        return sum;
    }
    public static int wh(char ch){
        switch (ch){
            case 'a':return 4;
            case 'b':return 9;
            case 'c':return 40;
            case 'd':return 90;
            case 'e':return 400;
            case 'f':return 900;
            case 'I':return 1;
            case 'V':return 5;
            case 'C':return 100;
            case 'D':return 500;
            case 'M':return 1000;
            case 'L':return 50;
            case 'X':return 10;


        }
        return 0;
    }

    public static void main(String[] args) {
        int a=1994;
        Code12 code12 = new Code12();
        String s = code12.intToRoman(a);
        System.out.println(s);
        s="LVIII";
        a=romanToInt(s);
        System.out.println(a);
    }
}
