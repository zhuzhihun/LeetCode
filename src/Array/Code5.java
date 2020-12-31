package Array;

public class Code5 {
    public static String longestPalindrome(String s){
        int len = s.length();
        if (len<2){//只有一个字符
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        //dp[i][j]表示s[i,j]是否是回文串
        boolean[][] dp = new boolean[len][len];//构建一张len*len的表，默认为false
        char[] charArray = s.toCharArray();//将String转变为数组
        for (int i=0;i<len;i++){
            dp[i][i]=true;
        }
        for (int j=1;j<len;j++){
            for (int i = 0; i < j; i++) {
                if (charArray[i]!=charArray[j]){
                    dp[i][j]=false;
                }
                else {
                    if (j-i<3){
                        dp[i][j]=true;
                    }else {
                        dp[i][j]=dp[i+1][j-1];
                    }
                }
                //只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j-i+1>maxLen){
                    maxLen = j-i+1;
                    begin=i;
                }
            }
        }

        for (int i = 0; i <len ; i++) {
            for (int j = 0; j <len ; j++) {
                System.out.print(dp[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        return s.substring(begin,begin+maxLen);
    }

    public static void main(String[] args) {
        String s = "asgunajaskag";
        String as = longestPalindrome(s);
        System.out.println(as.toString());
    }
}
