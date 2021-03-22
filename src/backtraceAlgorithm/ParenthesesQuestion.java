package backtraceAlgorithm;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ParenthesesQuestion {
    /*20. 有效的括号
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。
     */
    public boolean ParenthesesToIsValid(String s) {
        char[] chars = s.toCharArray();
        Deque stack = new LinkedList();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]=='('||chars[i]=='['||chars[i]=='{'){
                stack.addLast(chars[i]);
            }
            else if(chars[i]==')'||chars[i]==']'||chars[i]=='}'){
                if (stack.isEmpty()||change(chars[i])!= (char) stack.peekLast()){
                    return false;
                }
                stack.removeLast();
            }
        }
        if (stack.isEmpty()){
            return true;
        }
        return false;
    }

    private char change(char aChar) {
        if (aChar==')'){
           return '(';
        }else if (aChar==']'){
            return '[';
        }else
            return '{';
    }
    //20. 有效的括号
    public boolean isValid(String s) {
        int size = s.length();
        char[] statk = new char[size];
        //表示索引
        int index = -1;

        for(int i=0;i<size;i++){
            char c = s.charAt(i);
            switch(c){
                case '(':statk[++index] = ')';break;
                //条件     赋值
                case '[':statk[++index] = ']';break;
                case '{':statk[++index] = '}';break;
                default: if(index<0||statk[index]!=c) return false; else index--;

            }
        }
        return index==-1;

    }
    /*22括号生成
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     */
    //1、一个「合法」括号组合的左括号数量一定等于右括号数量，
    //2、对于一个「合法」的括号字符串组合 p，必然对于任何 0 <= i < len(p) 都有：子串 p[0..i] 中左括号的数量都大于或等于右括号的数量。
    public List<String> generateParenthesis(int n) {
        //n个括号对
        List<String> res = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        generateParenthesisDFS(res,stringBuffer,n,n);
        return res;
    }

    private void generateParenthesisDFS(List<String> res, StringBuffer stringBuffer, int left, int right) {
        if (right<left){
            //若 右括号可用次数小于左括号少用次数 表明不匹对
            return;
        }else if (right==0&&left==0){
            //当左右可用次数都变为0时,保存结果
            res.add(stringBuffer.toString());
            return;
        }else if (right<0||left<0){
            //错误的结果
            return;
        }
        stringBuffer.append('(');
        generateParenthesisDFS(res, stringBuffer, left-1, right);
        stringBuffer.deleteCharAt(stringBuffer.length()-1);

        stringBuffer.append(')');
        generateParenthesisDFS(res, stringBuffer, left, right-1);
        stringBuffer.deleteCharAt(stringBuffer.length()-1);

    }
}
