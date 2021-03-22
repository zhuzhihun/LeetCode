package array;

public class Code73 {
    public void setZeroes(int[][] matrix) {
        int[] a = new int[matrix.length];
        int[] b = new int[matrix[0].length];
        //0所在行与列都设置为0
        //使用  O(mn) 的额外空间  即判断所有元素位置是否为0
        for(int i=0;i<matrix.length;i++){
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]==0){
                    a[i]=1;
                    b[j]=1;
                }
            }
        }

        for (int i = 0; i < a.length; i++) {

            if (a[i]==1){
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j]=0;
                }
            }

        }
        for (int i = 0; i < b.length; i++) {
            if (b[i]==1){
                for (int j = 0; j < matrix.length; j++) {
                    matrix[j][i]=0;
                }
            }
        }

    }
}
