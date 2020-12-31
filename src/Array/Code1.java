package Array;

public class Code1 {
    int index = 0;
    public boolean havenumber(String s){
        int before = index;
        while (s.charAt(index)>='0'&&s.charAt(index)<='9'){
            index++;
        }
        return index>before;
    }
    public boolean fnumber(String s){
        if (s.charAt(index)=='-'||s.charAt(index)=='+'){
            index++;
        }
        return havenumber(s);
    }

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
