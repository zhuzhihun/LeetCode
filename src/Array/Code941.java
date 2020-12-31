package Array;

class Code941 {
    public boolean validMountainArray(int[] A) {
        int len=A.length;

        boolean flag =true;
        boolean flags =true;
        if (len<3){
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (flag==true){
                if(A[i]>A[i-1]){
                    flag= true;
                }else if(A[i]==A[i-1]){
                    return false;
                }else{flag= false;}
            }else {
                if (A[i]<A[i-1]){
                    flag= false;
                }else if(A[i]==A[i-1]){
                    return false;
                }else{
                    return false;
                }
            }

        }
        if (A[len-1]>=A[len-2]){
            return false;
        }
        if (A[0]>=A[1]){
            return false;
        }
        return flags;
    }
}