package algorithmTest;

import sun.security.provider.MD2;
import sun.security.provider.MD5;

import java.security.MessageDigestSpi;
import java.util.Stack;

public class BitDance {
    /*
    对于字符串A、B，其中A与B字符串中内部字符不重复，如果A字符串中所有字符都在B中完全出现，且字符串A中的字符顺序与B中字符顺序一致，
    那么就认为A是字符串B的子集.现在给定任意字符串B以及其子集字符串A的长度，求出B的子集字符串A的可能情况数量.
    示例：
    给定字符串B=“word"，B字符串的子字符串A长度为2，那么A的可能性为: wo,wr,wd,or,od,rd—共6种可能。

备注: 0<len(B)<=52 & & len(A)<=len(B)，如果不在该范围内则输出0,如果输入的字符串存在重复字符则输出0。
     */
    public static void main(String[] args) {
        String s = "1.123";
        System.out.println(Double.parseDouble(s));
    }
    /*
    翻转01字符串
     */

    /*
    跳伞跟随问题  并查集 1->2, 跟随1的都跟随2
     */
    /*
    二维数组判断最大矩形大小
     */
    /*
    将长网站映射成短网址

    public String shorten(String longUrl, int urlLength) {
        if (urlLength < 0 || urlLength > 6) {
            throw new IllegalArgumentException("the length of url must be between 0 and 6");
        }
        String md5Hex = DigestUtils.md5Hex(longUrl);
        // 6 digit binary can indicate 62 letter & number from 0-9a-zA-Z
        int binaryLength = urlLength * 6;
        long binaryLengthFixer = Long.valueOf(StringUtils.repeat("1", binaryLength), BINARY);
        for (int i = 0; i < 4; i++) {
            String subString = StringUtils.substring(md5Hex, i * 8, (i + 1) * 8);
            subString = Long.toBinaryString(Long.valueOf(subString, 16) & binaryLengthFixer);
            subString = StringUtils.leftPad(subString, binaryLength, "0");
            StringBuilder sbBuilder = new StringBuilder();
            for (int j = 0; j < urlLength; j++) {
                String subString2 = StringUtils.substring(subString, j * 6, (j + 1) * 6);
                int charIndex = Integer.valueOf(subString2, BINARY) & NUMBER_61;
                sbBuilder.append(DIGITS[charIndex]);
            }
            String shortUrl = sbBuilder.toString();
            if (lookupLong(shortUrl) != null) {
                continue;
            } else {
                return shortUrl;
            }
        }
        // if all 4 possibilities are already exists
        return null;
    }*/
}