package Array;
//使用二分方法解决问题
/*
珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。

珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  

珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。

返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。

 */
public class Code875 {
    /*
    1.暴力法
    利用Int i=1开始  遍历整个piles 判断是否能够在H小时内吃完
    寻找到的第一个数即为 所求的最小值

    2.二分方法
    从mid开始
     */
    public int minEatingSpeed(int[] piles, int H) {
        int right = getMax(piles);
        int left = 1;
        while (left<right){
            int mid =left+(right-left)/2;
            if (canFinish(piles,mid,H)){
                right = mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
    public boolean canFinish(int[] piles,int mid,int H){
        int hh = 0;
        for (int i = 0; i < piles.length; i++) {
            hh+=piles[i]/mid;
            int yu = piles[i]%mid;
            if (yu>0){
                hh++;
            }
        }
        return hh<=H;
    }
    public int getMax(int[] piles){
        int n=piles.length;
        if (n==0) return 0;
        int max = piles[0];
        //if (n==1) return max;
        for (int i=1;i<n;i++){
            if (piles[i]>max){
                max = piles[i];
            }
        }
        return max;
    }
    /*
传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
传送带上的第 i 个包裹的重量为 weights[i]。每一天，
我们都会按给出重量的顺序往传送带上装载包裹。
我们装载的重量不会超过船的最大运载重量。
返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     */
    public int shipWithinDays(int[] weights, int D) {
        int left = getMax(weights);
        int right= getSum(weights);
        while (left<right){
            int mid = left+(right-left)/2;
            if (canShip(weights,D,mid)){
                right=mid;
            }else {
                left=mid+1;
            }
        }
        return left;
    }
    public boolean canShip(int[] weight ,int D,int mid){
        int sum=0;
        int meici=0;
        for (int i = 0; i < weight.length; i++) {
            if (meici+weight[i]>mid){//表明现在还可以加
                sum++;
                meici=0;
                if (sum > D) {
                    return false;
                }
            }
            meici+=weight[i];
        }
        if (meici>0)
            sum++;
        return sum<=D;
    }
    public int getSum(int[] weights){
        int sum=0;
        for (int i = 0; i < weights.length; i++) {
            sum+=weights[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] weights = {1,2,3,4,5,6,7,8,9,10};
        Code875 code875 = new Code875();
        int i = code875.shipWithinDays(weights,5);
        System.out.println(i);
    }
}
