package Array;//n 名士兵站成一排。每个士兵都有一个 独一无二 的评分 rating 。
//
//每 3 个士兵可以组成一个作战单位，分组规则如下：
//
//从队伍中选出下标分别为 i、j、k 的 3 名士兵，他们的评分分别为 rating[i]、rating[j]、rating[k]
//作战单位需满足： rating[i] < rating[j] < rating[k] 或者 rating[i] > rating[j] > rating[k] ，其中  0 <= i < j < k < n
//请你返回按上述条件可以组建的作战单位数量。每个士兵都可以是多个作战单位的一部分。
//
// 

public class Code1395 {
    public static int numTeams(int[] rating){
        int len=rating.length;
        if(len<3){
            return 0;
        }
        int count=0;
        for (int i = 0; i <len-2 ; i++) {
            for (int j = i+1; j <len-1 ; j++) {
                for (int k = j+1; k <len ; k++) {
                    if (rating[i]<rating[j]&&rating[j]<rating[k]){
                        System.out.println("("+rating[i]+","+rating[j]+","+rating[k]+")");
                        count++;
                    }
                    if(rating[i]>rating[j]&&rating[j]>rating[k]){
                        System.out.println("("+rating[i]+","+rating[j]+","+rating[k]+")");
                        count++;

                    }
                }

            }

        }

        return count;
    }

    public static void main(String[] args) {
        int[] rating = {1,2,3,5};
        int a=numTeams(rating);
        System.out.println(a);
    }
}
