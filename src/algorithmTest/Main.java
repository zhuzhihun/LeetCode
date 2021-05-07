package algorithmTest;

public class Main {
    /*
    实现一个整形转中文的函数.
输入:
  1002  3450
输出:
  一千零二万三千四百五十
要求:
  1. 可用中文"个","十","百","千","万","亿","兆","零","负"
  2. 最大支持百兆

1,000,000,000,000,000
    ^     ^    ^
    兆    亿   万
     */
    static StringBuffer stringBuffer;//静态全局变量，一个用于接收字符串的stringbuffer

    /**
     * 这个函数是核心，用来把传入的整数转换成汉字输出字符串
     * @param num 传入的整数，应该在正负21亿之间
     * @return 返回一个字符串，字符串就是汉语翻译
     */
    public static String transInt(long num){

        stringBuffer=new StringBuffer();//每次使用之前清空一次，不然会带着上一次的值

        //先看有多少个亿
        if(num/10000/10000>0){
            int yi = (int)(num/10000/10000);//传入的数减去万亿之后的大数后，得出有多少个一亿
            changeGe(yi); //把这些一亿转换成四位数，如十一
            stringBuffer.append("亿");//加上‘亿’就变成了十一亿
        }
        if((num/10000%10000)>0){//去掉千位及以下和亿位及以上，看剩下的万位有没有值
            int wan = (int)(num/10000);//计算剩下的数中有多少万,因为只从最低位循环4遍，所以高位有什么数不用怕

            changeGe(wan);
            stringBuffer.append("万");

        }
        if(num>=20|num<10){
            int qian = (int)(num%10000);//减去所有万以后，取余看看还剩多少

            changeGe(qian);
        }
        if(num>=10&&num<20){
            littleThan20((int) num);//十到20之间的数转换规则不大一样，如一十三应写作十三，所以单独列一个情况，其他的按上面算
        }
        if(num!=0){//一下几行代码的作用是消除整个stringbuffer最前和最后的‘零’，比如3510351会写作零三百五十一万零三百五十一，为了防止这种补零后的冲突，将最前面的0消去
            if(stringBuffer.length()>=0&&stringBuffer.charAt(0)=='零'){
                stringBuffer.deleteCharAt(0);
            }
        }
        if(num == 0){
            stringBuffer.append('零');
        }
        return stringBuffer.toString();
    }



    /**
     * 按照亿，万，个等单位提取出四位数来传入本函数，本函数转换为中文，然后在上面函数添加上‘亿’‘万’等
     * @param qian 要传入的四位数
     * @return 将四位数解析为几千几百几十几，如1234解析为一千二百三十四
     */
    public static void changeGe(int qian){
        StringBuffer littleBuffer = new StringBuffer();//每四位输出一个汉字串插入到外部stringbuffer中

        for (int i = 0; i < 4; i++) {
            int ibit = qian%10;//取出最低位进行解析
            qian = qian/10;//将四位数右移一位，即将最低位删除

            if(ibit!=0){//若本位不为零，则一一映射输出
                littleBuffer.append(chineseName(i));
                littleBuffer.append(chineseBit(ibit));
                //若本位为零，则要看本位的前一位是否也为零，若为零则本非省略，因为多个连续的零只读一个零，如1001，此处还要注意防止指针越界
            }else if(littleBuffer.length()>0&&littleBuffer.charAt(littleBuffer.length()-1)=='零'){

            }else{
                littleBuffer.append("零");
            }
        }
        if(littleBuffer.charAt(0)=='零'){//如2505520会出现二百五十万 零 五百二十 零 的情况，此处的if是将末尾的零消去（从后往前解析）
            System.out.println("四位数末尾为0");
            littleBuffer.deleteCharAt(0);
        }
        stringBuffer.append(littleBuffer.reverse());//因为是从最低位进行解析，所以要反转

    }

    /**
     * 十到20之间的数转换规则不大一样，如一十三应写作十三，所以单独列一个情况，其他的按上面算
     * @param num
     * @return
     */
    public static void littleThan20(int num) {
        int i = num%10;
        stringBuffer.append("十");
        stringBuffer.append(chineseBit(i));
    }





    /**
     *
     * @param i 输入的某位数
     * @return 返回这个数的汉字
     */
    public static String chineseBit(int i){
        String ibit = "零";

        switch (i) {
            case 0:
                ibit = "零";
                break;
            case 1:
                ibit = "一";
                break;
            case 2:
                ibit = "二";
                break;
            case 3:
                ibit = "三";
                break;
            case 4:
                ibit = "四";
                break;
            case 5:
                ibit = "五";
                break;
            case 6:
                ibit = "六";
                break;
            case 7:
                ibit = "七";
                break;
            case 8:
                ibit = "八";
                break;
            case 9:
                ibit = "九";
                break;

            default:
                break;
        }
        return ibit;
    }

    /**
     * 得到进制位的汉字表示
     * @param tens 传入的整数是表示这是第几位
     * @return 返回进制汉字
     */
    public static String chineseName(int tens){
        String name = null;
        switch (tens) {
            case 0:
                name = "";
                break;
            case 1:
                name = "十";
                break;
            case 2:
                name = "百";
                break;
            case 3:
                name = "千";
                break;


            default:
                break;
        }
        return name;
    }

    public static String transDouble(double myDouble){
        String dbString = Double.toString(myDouble);
        String xiaoShu = dbString.replaceAll(".*\\u002E", "");
        String zhengShu = dbString.replaceAll("\\u002E.*","" );
        System.out.println("整数"+zhengShu);
        System.out.println("小数"+xiaoShu);
        String intPartion = transInt(Long.parseLong(zhengShu));
        String disPartion = "";
        for (int i = 0; i < xiaoShu.length(); i++) {
            disPartion+=chineseBit(xiaoShu.charAt(i)-48);
        }
        return intPartion+"点"+disPartion;
    }

}

class NumberToChinese {
    /**
     * @param
     */
    public static int change(int n) {
        char[] s = {'壹', '贰', '叁', '肆', '伍', '陸', '柒', '捌', '玖'};
        char[] t = {'十', '佰', '仟', '万', '十', '百', '千', '亿', '十', '百', '千'};
        StringBuilder sb = new StringBuilder();
        if (n == 0) {
            System.out.println("0/n零");
            return 1;
        }
        if (n < 0) {
            sb.append("负");
        }
        String str = String.valueOf(Math.abs(n));
        int length = str.length();
        boolean flag = false;
        for (int i = 0; i < length; i++) {
            if (length > 10) {
                if (i == (length - 10)) {
                    sb.append("亿");
                }
            } else if (length > 5) {
                if (i == (length - 5)) {
                    sb.append("万");
                }
            }
            if (str.charAt(i) == '0') {
                if (sb.charAt(sb.length() - 1) == '零')
                    continue;
                sb.append("零");
            } else {
                if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '万'
                        && length != 5 && !flag) {
                    sb.deleteCharAt(sb.length() - 1);
                    flag = true;
                }
                sb.append(s[str.charAt(i) - '1']);
                if (length > 1 && i != length - 1) {
                    sb.append(t[length - i - 2]);
                }
            }
        }
        if (sb.charAt(sb.length() - 1) == '零') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if (sb.indexOf("壹十") != -1) {
            sb.deleteCharAt(0);
        }
        System.out.println(n + "/n" + sb);
        return 1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        change(Integer.MAX_VALUE);
        change(2123456789);
        change(123456789);
        change(11200001);
        change(1234567);
        change(120001);
        change(31509);
        change(9090);
        change(123);
        change(20);
        change(1);
        change(0);
        change(Integer.MIN_VALUE + 1);
        change(-2123456789);
        change(-123456789);
        change(-11200001);
        change(-1234567);
        change(-1010001);
        change(-330509);
        change(-9090);
        change(-123);
        change(-20);
        change(-1);
    }

}