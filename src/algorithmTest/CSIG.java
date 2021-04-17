package algorithmTest;

import java.util.Optional;

public class CSIG {
    public static void main(String[] args) {
        int[] a={12,2,0,-5,0};//target=0
        int[] b=nums(a);
        for (int i = 0; i < a.length; i++) {
            System.out.println(b[i]);
        }
    }
    public static int[] nums(int[] a){
        int  j=a.length-1;
        int i=0;
        while (i<j){
            while (a[i]<=0){
                i++;
            }

            while (a[j]>0){
                j--;
            }
            if(i<j){
                int temp = a[i];
                a[i]=a[j];
                a[j]=temp;
                i++;
                j--;
            }


        }
        i=0;
        while (i<j){
            while (a[i]<0){
                i++;
            }

            while (a[j]>=0){
                j--;
            }
            if (i<j){
                int temp = a[i];
                a[i]=a[j];
                a[j]=temp;
                i++;
                j--;
            }


        }
        return a;
    }
}
