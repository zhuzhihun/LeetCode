package Array;

public class Code1 {
    int index = 0;
    /*
    利用索引位置是否发生改变判断后面是否接的0-9的数字
     */
    public boolean havenumber(String s){
        int before = index;
        while (s.charAt(index)>='0'&&s.charAt(index)<='9'){
            index++;
        }
        return index>before;
    }
    /*
    正负号判断 并是否后面带有数字
     */
    public boolean fnumber(String s){
        if (s.charAt(index)=='-'||s.charAt(index)=='+'){
            index++;
        }
        return havenumber(s);
    }
//判断是否为数字
    public boolean isNumber(String s){
        if (s.length()==0||s==null){
            return false;
        }
        int index = 0;

        s=s.trim();//去掉前后多余的空格
        s=s+'|';
        boolean flag = fnumber(s);
        //先确定是否有 正负号  有正负号后 判断是否有数字
        if (s.charAt(index)=='.'){
            index++;
            flag = flag||havenumber(s);
        }
        //遇到小数点 要判断后面是否跟有数字
        if (s.charAt(index)=='e'||s.charAt(index)=='E'){
            index++;
            flag=  flag&&fnumber(s);
        }
        //遇到e  要判断前后是否有数字

        //之后遇到的小数点 e 都是返回false
        return flag && s.charAt(index)=='|';
    }
    /*code.1
给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
你可以按任意顺序返回答案。
     */
    public int[] twoSum(int [] nums,int target){
        int[] indxs = new int[2];
        for (int i = 0; i <nums.length-1 ; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i] + nums[j]==target ){
                    indxs[0]=i;
                    indxs[1]=j;
                    return indxs;
                }
            }

        }
        return indxs;
    }
    public static void main(String[] args) {
        String s = "+1";
        Code1 code1=new Code1();


        boolean b = code1.isNumber(s);
        System.out.println(b);
    }
}
