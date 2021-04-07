package algorithmTest;

import java.util.Scanner;

/*
* 男女用餐问题
* */
public class MFEatFood {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int T = Integer.parseInt(s.nextLine());
        //数组组数
        while (s.hasNext()){
            int N = Integer.parseInt(s.nextLine());
            String str = s.nextLine();
            int table[] = new int[N];
            for(int i=0;i<N;i++){
                //str.indexOf();
                table[i]=Integer.parseInt(String.valueOf(str.charAt(i)));
            }
            int M = Integer.parseInt(s.nextLine());
            String MF = s.nextLine();
            char people[] = new char[M];
            for(int i=0;i<M;i++){
                char c = MF.charAt(i);
                people[i]= c;
            }
            //处理
            for(int i=0;i<M;i++){
                int q=-1;
                boolean flag=true;
                if(people[i]=='M'){//男

                    for(int j=0;j<N;j++){
                        if(table[j]==2)
                            continue;
                        if(table[j]==1){
                            table[j]=2;
                            System.out.println(j+1);
                            flag=false;
                            break;
                        }
                        if(table[j]==0&&q==-1){
                            q=j;
                        }
                    }

                    if(q!=-1&&flag){
                        table[q]=1;
                        System.out.println(q+1);
                    }

                }else{//女

                    for(int j=0;j<N;j++){

                        if(table[j]==2)
                            continue;

                        if(table[j]==0){
                            table[j]=1;
                            System.out.println(j+1);
                            flag=false;
                            break;
                        }
                        if(table[j]==1&&q==-1){
                            q=j;
                        }
                    }
                    if(q!=-1&&flag){
                        table[q]+=1;
                        System.out.println(q+1);
                    }

                }

            }

            //输出

            //下一组测试用例
        }

    }
}