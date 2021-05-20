package algorithmTest;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Text {
    public static void main(String[] args) throws IOException {
        Text text = new Text();
        //先调用大文件 生成小文件
        text.getBigFileAndChangeToSmallFile("","","","","","");
        //在对小文件进行比较
        List<String> list= text.compareSmallFile("","");
        for(String s:list){
            System.out.println(s);
        }
    }
    public void getBigFileAndChangeToSmallFile(String file,String file1,String file2,String file3,String file4,String file5) throws IOException {
        //读取一个文件
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        //并将其划分成5个小文件
        BufferedWriter bufferedWriter1 = new BufferedWriter(new FileWriter(file1,true));
        BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(file2,true));
        BufferedWriter bufferedWriter3 = new BufferedWriter(new FileWriter(file3,true));
        BufferedWriter bufferedWriter4 = new BufferedWriter(new FileWriter(file4,true));
        BufferedWriter bufferedWriter5 = new BufferedWriter(new FileWriter(file5,true));

        String online ="";
        while ( (online = bufferedReader.readLine())!=null){
            //String readLine = bufferedReader.readLine();
            int hash = toHash(online);
            switch (hash){
                case 0:  bufferedWriter1.write(online);bufferedWriter1.write("\r\n");break;
                case 1:  bufferedWriter2.write(online);bufferedWriter2.write("\r\n");break;
                case 2:  bufferedWriter3.write(online);bufferedWriter3.write("\r\n");break;
                case 3:  bufferedWriter4.write(online);bufferedWriter4.write("\r\n");break;
                default: bufferedWriter5.write(online);bufferedWriter5.write("\r\n");
            }
        }
        bufferedWriter1.close();
        bufferedWriter2.close();
        bufferedWriter3.close();
        bufferedWriter4.close();
        bufferedWriter5.close();
        bufferedReader.close();
    }
    public int toHash(String s){
        int arraySize = 5;//分成5个不同的小文件
        int hashCode = 0;
        for (int i = 0; i < s.length(); i++) {
            int letterValue = s.charAt(i)-96;//将获取的字符串转化为数字：如 a对应的为97 ，则97-96=1
            hashCode = ((hashCode<<5)+letterValue)%arraySize;//防止溢出，对每部结果都进行取摸运算
        }
        return hashCode;
    }
    public List<String> compareSmallFile(String path1,String path2) throws IOException {
        List<String> list = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        String line="";
        BufferedReader bufferedReader = null;
        for (int i = 1; i < 6; i++) {//需要对比的文件个数
            bufferedReader = new BufferedReader(new FileReader(path1));
            while ((line = bufferedReader.readLine())!=null){
                set.add(line);//将第一个文件中的数据都存放到set中，再跟第二个文件进行逐一比对
            }
            bufferedReader = new BufferedReader(new FileReader(path2));
            while ((line = bufferedReader.readLine())!=null){
                if (set.contains(line))
                    list.add(line);
            }
        }
        bufferedReader.close();
        return list;
    }
}
